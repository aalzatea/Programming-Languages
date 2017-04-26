//window.onload = changeTextStyle;
window.document.addEventListener('DOMContentLoaded', init, false);
//window.addEventListener('load', changeTextStyle, false);
//document.attachEvent("onreadystatechange", changeTextStyle);//For IE
//window.attachEvent("onload", changeTextStyle);//For IE
//Alternative: https://github.com/jfriend00/docReady

var svg;
var circle;
var rectangle;
var line;

function init() {
    changeTextStyle();
    addAttributeToAnElement('test', 'test-value');

    svg = createCanvas();
    circle = createCircle(250, 250, 50);
    rectangle = createRectangle(100, 50);
    line = createLine(0, 100, 400, 400);
}

function changeTextStyle() {
    d3.select('body')
        .append('p')
        .style('color', 'red')
        .text("Hi, What's up?");
}

function addAttributeToAnElement(attrName, attrValue) {
    d3.select('body')
        .append('p')
        .attr(attrName, attrValue)
        .text("Hi, What's up?");
}

function createCanvas() {
    return d3.select('body')
        .append('svg')
        .attr('width', 500)
        .attr('height', 500);
}

function createCircle(posX, posY, radius) {
    return svg.append('circle')
        .attr('cx', posX)
        .attr('cy', posY)
        .attr('r', radius)
        .attr('fill', 'red');
}

function createRectangle(width, height) {
    return svg.append('rect')
        .attr('width', width)
        .attr('height', height);
}

function createLine(posX1, posY1, posX2, posY2) {
    return svg.append('line')
        .attr('x1', posX1)
        .attr('y1', posY1)
        .attr('x2', posX2)
        .attr('y2', posY2)
        .attr('stroke', 'green')
        .attr('stroke-width', 10);
}