window.document.addEventListener('DOMContentLoaded', init, false);

var data = [5, 10, 21, 20, 35, 30, 40, 50];

function init() {
    var list = document.querySelector('#results');

    //Shows data
    createListElement(list, `Data: ${data}`);

    //Get the first element from array and remove it
    createListElement(list, `Element removed: ${data.shift()}`);

    //Sorted array descendantly
    createListElement(list, `Sorted list denscendently: ${data.sort(d3.descending)}`);

    //Sorted array descendantly
    createListElement(list, `Sorted list ascendingly: ${data.sort(d3.ascending)}`);

    createListElement(list, `Min: ${d3.min(data)}`);

    createListElement(list, `Max: ${d3.max(data)}`);

    createListElement(list, `Extent: ${d3.extent(data)}`);

    createListElement(list, `Sum all elements: ${d3.sum(data)}`);

    createListElement(list, `Mean: ${d3.mean(data)}`);

    createListElement(list, `Median: ${d3.median(data)}`);

    createListElement(list, `Shuffle: ${d3.shuffle(data)}`);
}

function createListElement(list, text) {
    var element = document.createElement('li');
    var textElement = document.createTextNode(text);

    element.appendChild(textElement);
    list.appendChild(element);
}