function handle(checkbox) {
    let elements = document.getElementsByTagName('input');
    for (let i = 0; i < elements.length; i++) {
        elements[i].checked = checkbox.checked;
    }
    document.getElementById('none').checked = true;
}

function check_pass() {
    if ((document.getElementById('password').value ==
        document.getElementById('password2').value) && document.getElementById('password').value != '') {
        document.getElementById('saveNew').disabled = false;
    } else {
        document.getElementById('saveNew').disabled = true;
    }
}

function viewOptions() {
    if (document.getElementById('selectbox').value === "")
        document.getElementById('optionsFieldset').hidden = true;
    else
        document.getElementById('optionsFieldset').hidden = false;
}

