window.document.addEventListener('DOMContentLoaded', init, false);

function init() {
    d3.select('body').append('h3').text('1. Cluster Layout');
    var canvas1 = createCanvas(500, 500);
    var group1 = createGroup(50, 50, canvas1);
    var cluster = createCluster(400, 400);
    readFromJsonFileForCluster("../../data/tree.json", group1, cluster, drawCluster);

    d3.select('body').append('h3').text('2. Pack Layout');
    var width = 650;
    var height = 600;
    var canvas2 = createCanvas(width, height + 200);
    var group2 = createGroup(50, 50, canvas2);
    var pack = createPack(width, height);
    readFromJsonFileForPack("../../data/tree.json", group2, pack);

    d3.select('body').append('h3').text('3. Bubble Layout');
    var width = 650;
    var height = 600;
    var canvas3 = createCanvas(width, height + 200);
    var group3 = createGroup(50, 50, canvas3);
    var bubble = createPack(width, height);
    readFromJsonFileForBubble("../../data/bubble.json", group3, bubble);
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

function createCluster(width, height) {
    return d3.cluster()
        .size([width, height]);
}

function readFromJsonFileForCluster(filePath, group, cluster, callback) {
    d3.json(filePath, function(error, data){
        if(error) {
            throw error;
        }

        callback(data, group, cluster);
    });
}

function drawCluster(data, group, cluster) {
    var nodes = d3.hierarchy(data, (d) => d.children);
    nodes = cluster(nodes);

    var links = createLinks(group, nodes);
    var node = createNode(group, nodes);
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

function createPack(width, height) {
    return d3.pack()
        .size([width, height - 50])
        .padding(10);
}

function readFromJsonFileForPack(filePath, group, pack) {
    d3.json(filePath, (error, data) => {
        if(error) {
            throw error;
        }

        var nodes = d3.hierarchy(data);
        nodes = pack(nodes);
        
        var node = createPackNode(group, nodes);
    });
}

function createPackNode(group, nodes) {
    var node = group.selectAll('.node')
            .data(nodes.descendants())
            .enter()
                .append('g')
                .attr('class', 'node')
                .attr('transform', (d) => `translate(${d.y},${d.x})`);
    
    node.append('circle')
        .attr('r', (d) => d.r)
        .attr('fill', 'steelblue')
        .attr('opacity', 0.25)
        .attr('stroke', '#ADADAD')
        .attr('stroke-width', "2");
    
    node.append('text')
        .attr('fill', 'white')
        .attr('font-size', 35)
        .style('text-anchor', 'middle')
        .text((d) => d.children ? '' : d.data.name);

    return node;
}

function readFromJsonFileForBubble(filePath, group, bubble) {
    d3.json(filePath, (error, data) => {
        if(error) {
            throw error;
        }

        var nodes = d3.hierarchy(data);
        nodes = bubble(nodes);
        
        var node = createBubbleNode(group, nodes);
    });
}

function createBubbleNode(group, nodes) {
    var node = group.selectAll('.node')
            .data(nodes.descendants())
            .enter()
                .append('g')
                .attr('class', 'node')
                .attr('transform', (d) => `translate(${d.y},${d.x})`);
    
    node.append('circle')
        .attr('r', (d) => d.r)
        .attr('fill', (d) => d.children ? '#fff' : 'steelblue')
        .attr('opacity', 0.25)
        .attr('stroke', (d) => d.children ? '#fff' : '#ADADAD')
        .attr('stroke-width', "2");
    
    node.append('text')
        .attr('fill', 'black')
        .attr('font-size', 20)
        .style('text-anchor', 'middle')
        .text((d) => d.children ? '' : d.data.name);

    return node;
}