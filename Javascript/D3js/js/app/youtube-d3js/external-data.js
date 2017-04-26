window.document.addEventListener('DOMContentLoaded', init, false);

var canvasJson;
var canvasCSV;

function init() {
    canvasJson = createCanvas(500, 200);

    d3.select('body')
        .append('br');

    canvasCSV = createCanvas(500, 200);
    readFromJsonFile("../../data/data.json");
    readFromCSVFile("../../data/data.csv");
}

function createCanvas(width, height) {
    return d3.select('body')
        .append('svg')
        .attr('width', width)
        .attr('height', height);
}

function loadJsonFile(callback) {
    var request = new XMLHttpRequest();

    request.overrideMimeType("application/json");
    request.open('GET', '../data/data.json', true);
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == "200") {
            callback(request.responseText);
        }
    };

    request.send(null);
}

//This function works if the project is deployed on a http server such as http-server
function readFromJsonFile(filePath) {
    d3.json(filePath, function(error, data){
        if(error) {
            throw error;
        }

        createBars(data, canvasJson, 'blue');
        putTextOnBars(data, canvasJson, 'white');
    });
}

function readFromCSVFile(filePath) {
    d3.csv(filePath, function(error, data){
        if(error) {
            throw error;
        }

        createBars(data, canvasCSV, 'green');
        putTextOnBars(data, canvasCSV, 'black');
    });
}

function createBars(data, canvas, barColor) {
    canvas.selectAll('rect')
            .data(data)
            .enter()
                .append('rect')
                .attr('width', function(d){
                    return d.age * 10;
                })
                .attr('height', 48)
                .attr('y', function(d, i) {
                    return i * 50;
                })
                .attr('fill', barColor);
}

function putTextOnBars(data, canvas, textColor) {
    canvas.selectAll('text')
            .data(data)
            .enter()
                .append('text')
                .attr('fill', textColor)
                .attr('y', function(d, i) {
                    return i * 50 + 24;
                })
                .text(function(d) {
                    return d.name;
                });
}