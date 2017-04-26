window.document.addEventListener('DOMContentLoaded', init, false);

var data = [10, 50, 80];
var color = d3.scaleOrdinal()
                .range(['red', 'blue', 'orange']);

function init() {
    var canvas1 = createCanvas(500, 500);
    d3.select('body').append('br');
    var canvas2 = createCanvas(500, 500);

    var group1 = createGroup(200, 200, canvas1);
    var group2 = createGroup(200, 200, canvas2);

    var arc1 = createArc(200, 100);
    var arc2 = createArc(200, 0);

    var pie = createPie();

    var arcs1 = createArcs(group1, 'arc', pie);
    var arcs2 = createArcs(group2, 'arc', pie);

    arcs1.append('path')
        .attr('d', arc1)
        .attr('fill', (d) => color(d.data));

    arcs2.append('path')
        .attr('d', arc2)
        .attr('fill', (d) => color(d.data));

    arcs1.append('text')
        .attr('transform', (d) => `translate(${arc1.centroid(d)})`)
        .attr('text-anchor', 'middle')
        .attr('font-size', '1.5em')
        .attr('fill', 'white')
        .text((d) => d.data);
    
    arcs2.append('text')
        .attr('transform', (d) => `translate(${arc2.centroid(d)})`)
        .attr('text-anchor', 'middle')
        .attr('font-size', '1.5em')
        .text((d) => d.data);
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

function createArc(radius, innerRadius) {
    return d3.arc()
            .innerRadius(innerRadius)
            .outerRadius(radius);
}

function createPie() {
    return d3.pie().value((d) => d);
}

function createArcs(group, cssClass, pie) {
    return group.selectAll(`.${cssClass}`)
            .data(pie(data))
            .enter()
                .append('g')
                .attr('class', cssClass);
}