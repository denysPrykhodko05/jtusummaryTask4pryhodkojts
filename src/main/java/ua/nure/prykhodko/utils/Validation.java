package ua.nure.prykhodko.utils;

public class Validation {
    private static final String LOGIN_REGEX = "^\\w{4,16}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])\\w{8,16}$";
    private static final String EMAIL_REGEX = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{5,}[.]{1}+[a-z]{3}";
    private static final String COUNT_REGEX="^\\d{1,7}$";

    public static boolean isCorrectLogin(String login){
        return login.matches(LOGIN_REGEX);
    }

    public static boolean isCorrectPassword(String pass){
        return pass.matches(PASSWORD_REGEX);
    }

    public static boolean isCorrectEmail(String email){
        return email.matches(EMAIL_REGEX);
    }

    public static boolean fundCountValidation(String amount){
        return amount.matches(COUNT_REGEX);
    }

}
