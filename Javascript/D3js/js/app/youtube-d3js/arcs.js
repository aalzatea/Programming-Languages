window.document.addEventListener('DOMContentLoaded', init, false);

var canvas;

function init() {
    canvas = createCanvas(500, 500);
    var group = createGroup(100, 100);
    var arc = createArc(100, 1);

    group.append('path')
        .attr('d', arc);
}

function createCanvas(width, height) {
    return d3.select('body')
        .append('svg')
        .attr('width', width)
        .attr('height', height);
}

function createGroup(xPos, yPos) {
    return canvas.append('g')
            .attr('transform', `translate(${xPos}, ${yPos})`);
}

function createArc(radius, minusRadians) {
    var circleRadians = Math.PI * 2;//This is equal to 360 degrees

    return d3.arc()
            .innerRadius(radius - 20)
            .outerRadius(radius)
            .startAngle(0)
            .endAngle(circleRadians - minusRadians);
}