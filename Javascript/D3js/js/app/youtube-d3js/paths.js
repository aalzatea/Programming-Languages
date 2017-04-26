window.document.addEventListener('DOMContentLoaded', init, false);

var canvas;
var data = [
    {x: 10, y: 20},
    {x: 40, y: 60},
    {x: 50, y: 70}
];

function init() {
    canvas = createCanvas(500, 500);
    var group = createGroup(100, 100);
    var line = createLine();
    createPath(group, line);
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

function createLine() {
    return d3.line()
        .x(function(d) {
            return d.x;
        })
        .y(function(d) {
            return d.y;
        });
}

function createPath(group, line) {
    group.selectAll('path')
        .data([data])
        .enter()
            .append('path')
            .attr('d', line)
            .attr('fill', 'none')
            .attr('stroke', '#000')
            .attr('stroke-width', 10);
}