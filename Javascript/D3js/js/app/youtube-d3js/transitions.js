window.document.addEventListener('DOMContentLoaded', init, false);

var svg;
var width = 500;
var height = 500;

function init() {
    svg = createCanvas();
    var circle = createCircle(50, 50, 25);
    setCircleTransition(circle, 150, 200, 2, 1)
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

function setCircleTransition(circle, xPos, yPos, durationSec, delaySec) {
    circle.transition()
        .duration(durationSec * 1000)//This value is in milliseconds
        .delay(delaySec * 1000)//This value is in milliseconds
        .attr('cx', xPos)
        .transition()
        .attr('cy', yPos)
        .transition()
        .attr('cx', (xPos - 100))
        .transition()
        .attr('cy', (yPos - 150))
        .on('end', function() { //This is the same "each" function on d3 three version [actions: end, start and interruption]
            d3.select(this).attr('fill', 'orange');
        });
}