function isCorrectStationName(form, input) {
    var reg = /^\D{1,}$/;
    var name = document.forms[form][input].value;
    if (!reg.exec(name)){
        alert("Incorrect name");
        return false;
    }
}