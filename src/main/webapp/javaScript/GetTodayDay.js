var d = new Date();
var day = d.getDate();
var dayMax = day;
var month = d.getMonth()+1;
var monthMax = month+3;
var year = d.getFullYear();
var yearMax = year;
if (month < 10)
    month = "0" + month;

if(monthMax>12){
    monthMax = monthMax-12;
    yearMax+=1;
}

if(monthMax == 2 && dayMax >28){
    monthMax+=1;
    dayMax="01";
}

if (monthMax < 10)
    monthMax = "0" + monthMax;

if (day < 10)
    day = "0" + day;

if (dayMax < 10)
    dayMax = "0" + dayMax;

var date = year+"-"+month+"-"+day;
var dateMax = yearMax+"-"+monthMax+"-"+dayMax;
var dateH = document.querySelector('#vDate');
dateH.setAttribute('value',date.toString());
dateH.setAttribute('min',date.toString());
dateH.setAttribute('max', dateMax.toString());