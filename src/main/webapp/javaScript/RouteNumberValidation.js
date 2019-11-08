function isCorrectNumber() {
    var reg = /^\d{1,}$/;
    var number = document.getElementById('number').value;
    if(!reg.exec(number)){
        alert("Incorrect route number");
        return false;
    }
}