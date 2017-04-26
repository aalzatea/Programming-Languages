window.document.addEventListener('DOMContentLoaded', init, false);

var svg;
var bars;
var dataArray = [10, 20];
//var dataArray = [10];
var width = 500;
var height = 500;

function init() {
    svg = createCanvas();
    var circle1 = createCircle(50, 50, 25);
    //var circle2 = createCircle(50, 110, 25);
    var circles = createCirclesWithEnter(50, 170, 25);
    //var circles = createCirclesWithExit(50, 170, 25);
}

function createCanvas() {
    return d3.select('body')
        .append('svg')
        .attr('width', width)
        .attr('height', height);
}

function createCircle(xPos, yPos, radius) {
    return svg.append('circle')
            .attr('cx', xPos)
            .attr('cy', yPos)
            .attr('r', radius);
}

//Cuando hay mas datos que circulos o la cantidad de datos es igual a la cantidad de circulos
function createCirclesWithEnter(xPos, yPos, radius) {
    return svg.selectAll('circle')
            .data(dataArray)
            .attr('fill', 'red')
            .enter()
                .append('circle')
                .attr('cx', xPos)
                .attr('cy', yPos)
                .attr('fill', 'green')
                .attr('r', radius);
}

//Cuando la cantidad de circulos es mayor a la cantidad de datos
function createCirclesWithExit(xPos, yPos, radius) {
    return svg.selectAll('circle')
            .data(dataArray)
            .attr('fill', 'green')
            .exit()
                .attr('fill', 'blue');
}