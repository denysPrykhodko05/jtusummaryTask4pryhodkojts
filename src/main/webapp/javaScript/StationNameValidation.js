function isCorrectStationName() {
    var reg = /^\D{1,}$/;
    var name = document.getElementById('station_name').value;
    if (!reg.exec(name)){
        alert("Incorrect name");
        return false;
    }
}