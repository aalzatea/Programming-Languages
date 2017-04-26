window.document.addEventListener('DOMContentLoaded', init, false);

var canvas1;
var canvas2;

function init() {
    canvas1 = createCanvas(310, 310);
    var diagonal = createDiagonal(10, 10, 300, 300);
    createPath(canvas1, diagonal);

    d3.select('body').append('br');

    canvas2 = createCanvas(500, 500);
    var group = createGroup(50, 50, canvas2);
    var tree = createTree(400, 400);
    readFromJsonFile("../../data/tree.json", group, tree);
}

function createCanvas(width, height) {
    return d3.select('body')
        .append('svg')
        .attr('width', width)
        .attr('height', height);
}

function createGroup(xPos, yPos, canvas) {
    return canvas.append('g')
            .attr('transform', `translate(${xPos}, ${yPos})`);
}

function createDiagonal(startXPoint, startYPoint, targetXPoint, targetYPoint) {
    var d = {
        source: {x: startXPoint, y: startYPoint},
        target: {x: targetXPoint, y: targetYPoint}
    };

    return "M" + d.source.y + "," + d.source.x
      + "C" + (d.source.y + d.target.y) / 2 + "," + d.source.x
      + " " + (d.source.y + d.target.y) / 2 + "," + d.target.x
      + " " + d.target.y + "," + d.target.x;
}

function createPath(canvas, diagonal) {
    canvas.append('path')
        .attr('fill', 'none')
        .attr('stroke', 'black')
        .attr('d', diagonal);
}

function createTree(width, height) {
    return d3.tree()
        .size([width, height]);
}

function readFromJsonFile(filePath, group, tree) {
    d3.json(filePath, function(error, data){
        if(error) {
            throw error;
        }

        var nodes = d3.hierarchy(data, (d) => d.children);
        nodes = tree(nodes);

        var links = createLinks(group, nodes);
        var node = createNode(group, nodes);
    });
}

function createLinks(group, nodes) {
    return group.selectAll('.link')
        .data(nodes.descendants().slice(1))
        .enter()
            .append('path')
            .attr('class', 'link')
            .attr('fill', 'none')
            .attr('stroke', '#ADADAD')
            .attr('d', (d) => {
                return "M" + d.y + "," + d.x
                     + "C" + (d.y + d.parent.y) / 2 + "," + d.x
                     + " " + (d.y + d.parent.y) / 2 + "," + d.parent.x
                     + " " + d.parent.y + "," + d.parent.x;
            });
}

function createNode(group, nodes) {
    var node = group.selectAll('.node')
            .data(nodes.descendants())
            .enter()
                .append('g')
                .attr('class', 'node')
                .attr('transform', (d) => `translate(${d.y},${d.x})`);

    putNodeShape(node);
    putNodeText(node);

    return node;
}

function putNodeShape(node) {
    node.append('circle')
        .attr('r', 5)
        .attr('fill', 'steelblue');
}

function putNodeText(node) {
    node.append('text')
        .attr('dy', '.35em')
        .attr('y', (d) => d.children ? -20 : 20)
        .style('text-anchor', 'middle')
        .text((d) => `${d.data.name}${d.data.size ? ', ' + d.data.size : ''}`);
}