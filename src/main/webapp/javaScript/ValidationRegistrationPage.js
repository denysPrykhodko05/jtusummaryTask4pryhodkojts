
function validateForm(form, login, password, co_password) {
    var regLogin=/^\w{3,16}$/;
    var regPassword = /^(?=.*[A-Z])(?=.*[0-9])\w{8,16}$/;
    var login = document.forms[form][login].value;
    if (!regLogin.exec(login)) {
        alert("Incorrect login: " + login);
        return false;
    }
    var password = document.forms[form][password].value;
    if (!regPassword.exec(password)) {
        alert("Incorrect password");
        return false;
    }

    var co_password = document.forms[form][co_password].value;
    if(password != co_password){
        alert("Incorrect confirm password");
        return false;
    }
}