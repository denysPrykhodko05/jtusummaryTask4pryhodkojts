function validateForm(form,login, password) {
    var regLogin=/^\w{3,16}$/;
    var regPassword = /^(?=.*[A-Z])(?=.*[0-9])\w{8,16}$/;
    var x = document.forms[form][login].value;
    if (!regLogin.exec(x)) {
        alert("Incorrect login: " + x);
        return false;
    }
    var y = document.forms[form][password].value;
    if (!regPassword.exec(y)) {
        alert("Incorrect password");
        return false;
    }
}