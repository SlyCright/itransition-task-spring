
function refresh() {
    setTimeout("window.location.href = '/admin';", 0 );
    return false;
}

function selectAll() {
    var all = document.getElementById('selectAll');
    var checkboxes = document.getElementsByName('raz');
    for (var checkbox of checkboxes) {
        checkbox.checked = all.checked;
    }
}

function getSelectedUsers() {
    var checkbox = document.getElementsByName("raz");
    var Row = document.getElementsByName("userRow");
    var IDs = [];
    for(var i=0; i<checkbox.length; i++){
        var Cells = Row[i].getElementsByTagName("td");
        if(checkbox[i].checked) {
            IDs.push(Cells[1].innerText);
        }
    }
    return JSON.stringify(IDs);
}

function sendUsers(url) {
    var users = getSelectedUsers();
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(users);
    if (xhr.status != 200) {
        alert("Success!");
    } else {
        alert( xhr.responseText );
    }
    refresh();
}

function randomSum(min, max) {
    document.querySelector('.sum').innerHTML = (Math.floor(Math.random() * (max - min + 1) ) + min);
}

