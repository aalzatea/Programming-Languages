window.document.addEventListener('DOMContentLoaded', init, false);

var canvas;

function init() {
    canvas = createCanvas(760, 700);
    readFromJsonFile('../../data/colombia.geojson')
}

function createCanvas(width, height) {
    return d3.select('body')
             .append('svg')
                .attr('width', width)
                .attr('height', height);
}

function readFromJsonFile(filePath) {
    d3.json(filePath, function(error, data){
        if(error) {
            throw error;
        }

        const width = canvas.attr('width');
        const height = canvas.attr('height');
        
        var group = canvas.selectAll('g')
                          .data(data.features)
                          .enter()
                            .append('g');

        var projection = d3.geoMercator()
                           .scale(width / 2 / Math.PI)
                           .center([-74, 4.5])
                           .translate([width / 2, height / 2]);
        
        var path = d3.geoPath()
                     .projection(projection);
        
        group.append('path')
              .attr('d', path(data))
              .attr('class', 'area')
              .attr('fill', 'steelblue');
    });
}