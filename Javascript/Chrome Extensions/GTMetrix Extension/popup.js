function createForm(input) {
    var form = document.createElement('form');
    form.action = 'http://gtmetrix.com/analyze.html?bm';
    form.method = 'post';
    form.appendChild(input);

    return form;
};

function createInput(tab) {
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'url';
    input.value = tab.url;
    
    return input;
};

function checkPageButtonEvent() {
    chrome.tabs.getSelected(null, function(tab) {
        var input = createInput(tab);
        var form = createForm(input);
        
        document.body.appendChild(form);
        form.submit();
    });
};

function checkPage() {
    var checkPageButton = document.getElementById('checkPage');
    checkPageButton.addEventListener('click', checkPageButtonEvent, false);
}

document.addEventListener('DOMContentLoaded', checkPage, false);