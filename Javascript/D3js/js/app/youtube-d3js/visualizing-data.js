window.document.addEventListener('DOMContentLoaded', init, false);

var svg;
var dataArray = [20, 40 ,50];
var bars;

function init() {
    svg = createCanvas();
    bars = createBarsGraphic();
}

function createCanvas() {
    return d3.select('body')
        .append('svg')
        .attr('width', 500)
        .attr('height', 500);
}

function createBarsGraphic() {
    return svg.selectAll('rect')
        .data(dataArray)
        .enter()
            .append('rect')
            .attr('width', function(d) {
                return d * 10;
            })
            .attr('height', 50)
            .attr('y', function(d, i) {
                return i * 100;
            });
}