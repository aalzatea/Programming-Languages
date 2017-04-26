package com.hugeinc.test.persistence;

import com.hugeinc.test.models.Attendee;
import com.hugeinc.test.models.Location;
import com.hugeinc.test.models.Run;
import com.hugeinc.test.persistence.randomizers.*;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.randomizers.SentenceRandomizer;
import io.github.benas.randombeans.randomizers.StreetRandomizer;
import io.github.benas.randombeans.randomizers.range.DateRangeRandomizer;
import io.github.benas.randombeans.randomizers.range.DoubleRangeRandomizer;
import io.github.benas.randombeans.randomizers.range.IntegerRangeRandomizer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by aalzate on 10/21/16.
 */
public class RandomData {

    private EnhancedRandom enhancedRandom;

    private EnhancedRandomBuilder enhancedRandomBuilder;

    public RandomData() {
        configRandomData();
        enhancedRandom = getEnhancedRandom();
    }

    public RandomData(List<RandomField<?, ?>> randomFields) {
        configRandomData();
        putRandomizers(randomFields);
        enhancedRandom = getEnhancedRandom();
    }

    private EnhancedRandom getEnhancedRandom() {
        return enhancedRandomBuilder.build();
    }

    private void configRandomData() {
        enhancedRandomBuilder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder();
        enhancedRandomBuilder
                .minCollectionSize(1)
//                .maxCollectionSize(10)
                .minStringLength(5)
                .maxStringLength(20)
                .maxObjectPoolSize(50)
                .charset(Charset.forName("UTF-8"))
                .dateRange(LocalDate.now(), LocalDate.now().plusDays(1))
                .timeRange(LocalTime.of(0, 0, 0), LocalTime.of(23, 59, 59))
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(true)
                .seed(123L);
    }

    private void putRandomizers(List<RandomField<?, ?>> randomFields) {
        if(randomFields == null || randomFields.isEmpty()) {
            return;
        }

        randomFields.forEach(randomField -> {
            enhancedRandomBuilder.randomize(
                FieldDefinitionBuilder
                        .field()
                        .named(randomField.getFieldName())
                        .ofType(randomField.getFieldType())
                        .inClass(randomField.getTargetClass())
                        .get(),
                randomField.getRandomizer()
            );
        });
    }

    public <T> T generateRandomData(Class<T> type) {
        return enhancedRandom.nextObject(type);
    }

    public <T> T generateRandomData(Class<T> type, String... excludeFields) {
        return enhancedRandom.nextObject(type, excludeFields);
    }

    public <T> Collection<T> generateRandomDataCollection(Class<T> type, int amount) {
        return enhancedRandom.objects(type, amount, new String[]{})
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public <T> Collection<T> generateRandomDataCollection(Class<T> type, int amount, String... excludeFields) {
        return enhancedRandom.objects(type, amount, excludeFields)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static <T, TC> RandomField<T, TC> getRandomField(String fieldName, Class<T> fieldType, Class<TC> targetClass, Randomizer<T> randomizer) {
        RandomField<T, TC> randomField = new RandomField<>();
        randomField.setFieldName(fieldName);
        randomField.setFieldType(fieldType);
        randomField.setTargetClass(targetClass);
        randomField.setRandomizer(randomizer);

        return randomField;
    }

    public static List<RandomField<?, ?>> getRandomizers() {
        List<RandomField<?, ?>> randomizers = new LinkedList<>();
        randomizers.addAll(getRunRandomizers());
        randomizers.addAll(getLocationRandomizers(null));
        randomizers.addAll(getAttendeeRandomizers(null));

        return randomizers;
    }

    public static List<RandomField<?, ?>> getRunRandomizers() {
        Instant localDateTimeMin = LocalDateTime.now().plusDays(3).toInstant(ZoneOffset.UTC);
        Instant localDateTimeMax = LocalDateTime.now().plusDays(10).toInstant(ZoneOffset.UTC);

        return Arrays.asList(
                getRandomField("id", String.class, Run.class, new UUIDRandomizer()),
                getRandomField("startingAddress", String.class, Run.class, new StreetRandomizer()),
                getRandomField("hostUserId", String.class, Run.class, new UUIDRandomizer()),
                getRandomField("startDate", Date.class, Run.class, new DateRangeRandomizer(Date.from(localDateTimeMin), Date.from(localDateTimeMax))),
                getRandomField("distance", Double.class, Run.class, DoubleRangeRandomizer.aNewDoubleRangeRandomizer(1d, 42d)),
                getRandomField("maxRunners", Integer.class, Run.class, new IntegerRangeRandomizer(1, 12)),
                getRandomField("title", String.class, Run.class, new TitleRandomizer()),
                getRandomField("message", String.class, Run.class, new MessageRandomizer()),
                getRandomField("description", String.class, Run.class, new SentenceRandomizer())
        );
    }

    public static List<RandomField<?, ?>> getLocationRandomizers(String runId) {
        return Arrays.asList(
                getRandomField("runId", String.class, Location.class, new UUIDRandomizer(runId)),
                getRandomField("position", Integer.class, Location.class, new IntegerRangeRandomizer(1, 10)),
                getRandomField("latitude", BigDecimal.class, Location.class, new LatitudeRandomizer(33.7844894, 34.3371594)),
                getRandomField("longitude", BigDecimal.class, Location.class, new LongitudeRandomizer(-118.516144, -118.160461))
        );
    }

    public static List<RandomField<?, ?>> getAttendeeRandomizers(String runId) {
        return Arrays.asList(
                getRandomField("runId", String.class, Attendee.class, new UUIDRandomizer(runId)),
                getRandomField("userId", String.class, Attendee.class, new UUIDRandomizer())
        );
    }

    public void writeResultsToFile(Collection<String> queries) throws IOException {
        String file = "run_dummy_queries.txt";

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))
        ) {
            writer.write("START TRANSACTION;\n");
            queries.forEach(query -> {
                try {
                    writer.write(query + "\n");
                } catch (IOException e) {
                }
            });
            writer.write("COMMIT;");
        } catch(IOException ioe) {

        }
    }

    public static void main(String... args) throws IOException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println(new Date());
//        RandomData randomData = new RandomData(getRunRandomizers());
//        Collection<Run> runs = randomData.generateRandomDataCollection(Run.class, 1, "route", "attendees", "latitude", "longitude");
//
//        List<String> allQueries = new LinkedList<>();
//        allQueries.addAll(generateRunQueries(runs));
//        allQueries.addAll(generateLocationQueries(runs, 3));
//        allQueries.addAll(generateAttendeeQueries(runs, 3));
//
//        randomData.writeResultsToFile(allQueries);

//        allQueries.forEach(System.out::println);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//        try {
//            String json = objectMapper.writeValueAsString(runs);
//            System.out.println(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

    private static List<String> generateRunQueries(Collection<Run> runs) {
        String insertStatement = "INSERT INTO run (" +
                "id," +
                "starting_address," +
                "host_user," +
                "created_date," +
                "modified_date," +
                "start_date," +
                "distance," +
                "distance_measurement_unit," +
                "pace," +
                "feature_run," +
                "flagged," +
                "public," +
                "max_runners," +
                "status," +
                "title," +
                "message," +
                "description" +
                ")";

        return runs.stream()
                .map(run -> {
                    String insertValues = " VALUES (" +
                            "'" + run.getId() + "'," +
                            "'" + run.getStartingAddress() + "'," +
                            "'" + run.getHostUserId() + "'," +
                            "'" + convertDateToString(run.getCreatedDate()) + "'," +
                            "'" + convertDateToString(run.getModifiedDate()) + "'," +
                            "'" + convertDateToString(run.getStartDate()) + "'," +
                            run.getDistance() + "," +
                            "'" + run.getDistanceMeasurementUnit().name() + "'," +
                            "'" + run.getPace().name() + "'," +
                            (run.isFeature() ? 1 : 0) + "," +
                            (run.isFlagged() ? 1 : 0) + "," +
                            (run.isPublicRun() ? 1 : 0) + "," +
                            run.getMaxRunners() + "," +
                            "'" + run.getRunStatus().getValue() + "'," +
                            "'" + run.getTitle() + "'," +
                            "'" + run.getMessage() + "'," +
                            "'" + run.getDescription() + "'" +
                           ");";

                    return insertStatement + insertValues;
                })
                .collect(Collectors.toList());
    }

    private static List<String> generateLocationQueries(Collection<Run> runs, int amount) {
        List<String> locationQueries = new LinkedList<>();

        runs
            .forEach(run -> {
                RandomData randomData = new RandomData(getLocationRandomizers(run.getId()));
                Collection<Location> locations = randomData.generateRandomDataCollection(Location.class, amount);
                String insertStatement = "INSERT INTO location (" +
                        "run_id," +
                        "position," +
                        "latitude," +
                        "longitude" +
                        ")";

                Map<Integer, String> positions = new HashMap<Integer, String>();

                List<String> inserts = locations.stream()
                        .map(location -> {
                            if(positions.get(location.getPosition()) != null)
                                return null;

                            String insertValues = " VALUES (" +
                                    "'" + location.getRunId() + "'," +
                                    location.getPosition() + "," +
                                    location.getLatitude() + "," +
                                    location.getLongitude() +
                                   ");";

                            positions.put(location.getPosition(), location.getRunId());

                            return insertStatement + insertValues;
                        })
                        .filter(location -> location != null)
                        .collect(Collectors.toList());

                locationQueries.addAll(inserts);
            });

        return locationQueries;
    }

    private static List<String> generateAttendeeQueries(Collection<Run> runs, int amount) {
        List<String> attendeeQueries = new LinkedList<>();

        runs
            .forEach(run -> {
                RandomData randomData = new RandomData(getAttendeeRandomizers(run.getId()));
                Collection<Attendee> attendees = randomData.generateRandomDataCollection(Attendee.class, amount);
                String insertStatement = "INSERT INTO attendee (" +
                        "run_id," +
                        "user_id" +
                        ")";

                List<String> inserts = attendees.stream()
                        .map(attendee -> {
                            String insertValues = " VALUES (" +
                                    "'" + attendee.getRunId() + "'," +
                                    "'" + attendee.getUserId() + "'" +
                                    ");";

                            return insertStatement + insertValues;
                        })
                        .collect(Collectors.toList());

                attendeeQueries.addAll(inserts);
            });

        return attendeeQueries;
    }

    private static String convertDateToString(Date date) {
        String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateInstance();
        simpleDateFormat.applyPattern(dateFormatPattern);

        return simpleDateFormat.format(date);
    }
}
