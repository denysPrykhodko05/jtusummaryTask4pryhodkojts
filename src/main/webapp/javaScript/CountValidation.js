function countValidation(form, count) {
    var reg = /^\d{1,7}$/;
    var x = document.forms[form][count].value;
    if(!reg.exec(x)){
        alert("incorrect input found count");
    }

    if (x<=0){
        alert("Amount must be more then 0");
    }
    if (x>1000000) {
        alert("Amount must be less then 1000000");
    }
}