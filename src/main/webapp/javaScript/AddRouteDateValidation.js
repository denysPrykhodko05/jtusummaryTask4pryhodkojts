var today = new Date();
var date = today.getDate();
var month = today.getMonth() + 1;
var year = today.getFullYear();
var dateD = document.querySelector('#departDate');
var dateA = document.querySelector('#arriveDate');
if (month < 10)
    month = "0" + month;

if (date < 10)
    date = "0" + date;

var dateT = year + "-" + month + "-" + date;
dateD.setAttribute('value', dateT.toString());
dateA.setAttribute('value', dateT.toString());
