window.document.addEventListener('DOMContentLoaded', init, false);

var svg;
var bars;
var dataArray = [5, 20, 40 , 50, 60];
var width = 500;
var height = 500;

var widthScale = d3.scaleLinear()
                    .domain([0,  60])
                    .range( [0, width]);

var color = d3.scaleLinear()
                .domain([0, 60])
                .range(['red', 'blue'])

function init() {
    svg = createCanvas();
    bars = createBarsGraphic();
}

function createCanvas() {
    return d3.select('body')
        .append('svg')
        .attr('width', width)
        .attr('height', height);
}

function createBarsGraphic() {
    return svg.selectAll('rect')
        .data(dataArray)
        .enter()
            .append('rect')
            .attr('width', function(d) {
                return widthScale(d);
            })
            .attr('height', 50)
            .attr('fill', function(d) { return color(d); })
            .attr('y', function(d, i) {
                return i * 100;
            });
}