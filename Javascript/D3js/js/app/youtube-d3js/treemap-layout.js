window.document.addEventListener('DOMContentLoaded', init, false);

var canvas;

function init() {
    canvas = createCanvas(960, 570);
    readFromJsonFile('../../data/treemap.json')
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

        paintTreeMap(data);
    });
}

function paintTreeMap(data) {
    const color = createColor();
    const treemap = createTreemap();
    var root = getData(data);
    
    treemap(root);

    const cell = createCell(root);
    putRects(cell, color);
    putClipPath(cell);
    putText(cell);
    
    cell.append("title")
        .text((d) => `${d.data.id}\n${d3.format(",d")(d.value)}`);
}

function createColor() {
    return d3.scaleOrdinal()
             .range(d3.schemeCategory20.map(
                 (color) => d3.interpolateRgb(color, "#fff")(0.2)
             ));
}

function createTreemap() {
    return d3.treemap()
      .tile(d3.treemapResquarify)
      .size([canvas.attr('width'), canvas.attr('height')])
      .paddingInner(1)
      .round(true);
}

function getData(data) {
    return d3.hierarchy(data)
             .eachBefore((d) => d.data.id = (d.parent ? d.parent.data.id + "." : "") + d.data.name)
             .sum((d) => d.size)
             .sort((a, b) => b.height - a.height || b.value - a.value);
}

function createCell(data) {
    return canvas.selectAll("g")
                 .data(data.leaves())
                 .enter()
                    .append("g")
                    .attr("transform", (d) => `translate(${d.x0},${d.y0})`);
}

function putRects(cell, color) {
    cell.append("rect")
        .attr("id", (d) => d.data.id)
        .attr("width", (d) => d.x1 - d.x0)
        .attr("height", (d) => d.y1 - d.y0)
        .attr("fill", (d) => color(d.parent.data.id));
}

function putClipPath(cell) {
    cell.append("clipPath")
            .attr("id", (d) => `clip-${d.data.id}`)
        .append("use")
            .attr("xlink:href", (d) => `#${d.data.id}`);
}

function putText(cell) {
    cell.append("text")
        .attr("clip-path", (d) => `url(#clip-${d.data.id})`)
        .selectAll("tspan")
            .data((d) => d.data.name.split(/(?=[A-Z][^A-Z])/g))
            .enter()
                .append("tspan")
                .attr("x", 4)
                .attr("y", (d, i) => 13 + i * 10)
                .text((d) => d);
}