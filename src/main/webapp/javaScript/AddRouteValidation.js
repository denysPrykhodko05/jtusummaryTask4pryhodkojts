function routeValidation(form,station1, station2){
    var stationNameReg= /^\D{1,}$/;

    var startStation = document.forms[form][station1].value;
    var finalStation = document.forms[form][station2].value;

    if (!stationNameReg.exec(startStation)) {
        alert('Incorrect start station');
        return false;
    }

    if (!stationNameReg.exec(finalStation)){
        alert('Incorrect final station');
        return false;
    }
    return true;
}