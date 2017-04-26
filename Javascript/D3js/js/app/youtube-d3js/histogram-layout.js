window.document.addEventListener('DOMContentLoaded', init, false);

var canvas;

function init() {
    canvas = createCanvas(600, 550);
    readFromCSVFile('../../data/histogram.csv')
}

function createCanvas(width, height) {
    return d3.select('body')
        .append('svg')
        .attr('width', width)
        .attr('height', height);
}

function readFromCSVFile(filePath) {
    d3.csv(filePath, function(error, data){
        if(error) {
            throw error;
        }

        createHistogram(data);
    });
}

function createHistogram(data) {
    var newData = data.map((element) => parseInt(element.age));
    var bins = d3.histogram()(newData);
    var x = createXAxis(newData);
    var y = createYAxis(bins);

    var bar = canvas.selectAll('.bar')
                .data(bins)
                .enter()
                    .append('g')
                    .attr('class', 'bar')
                    .attr('transform', (d) => `translate(${x(d.x0)},${y(d.length)})`);

    bar.append('rect')
        .attr('x', 1)
        .attr('width', x(bins[0].x1) - x(bins[0].x0) - 1)
        .attr('height', (d) => 500 - y(d.length))
        .attr('fill', 'steelblue');

    bar.append('text')
        .attr('x', (d) => (x(d.x1) - x(d.x0)) / 2)
        .attr('y', 6)
        .attr('dx', `.${(x(bins[0].x1) - x(bins[0].x0) - 1) / 2}em`)
        .attr('dy', '.75em')
        .attr('fill', '#FFF')
        .attr('text-anchor', 'middle')
        .text((d) => d.length);
}

function createXAxis(data) {
    var x = d3.scaleLinear()
              .domain([0, d3.max(data)])
              .rangeRound([0, 580]);
    
    canvas.append('g')
        .attr('transform', 'translate(18, 500)')
        .call(d3.axisBottom(x));

    return x;
}

function createYAxis(data) {
    var y = d3.scaleLinear()
              .domain([0, d3.max(data, (d) => d.length)])
              .range([500, 0]);

    canvas.append('g')
        .attr('transform', 'translate(18, 0)')
        .call(d3.axisLeft(y));

    return y;
}