function validateForm(form, from, to) {
    var regExp = /^\D{1,}$/;
    var x = document.forms[form][from].value;
    var y = document.forms[form][to].value;
    if (!regExp.exec(x)) {
        alert("Incorrect input: " + from);
        return false;
    }
    if (!regExp.exec(y)) {
        alert("Incorrect input: " + to);
        return false;
    }
}