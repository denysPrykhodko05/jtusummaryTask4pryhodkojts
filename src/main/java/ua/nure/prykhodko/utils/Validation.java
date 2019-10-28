package ua.nure.prykhodko.utils;

public class Validation {
    private static final String LOGIN_REGEX = "^\\w{4,16}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])\\w{8,16}$";

    public static boolean isCorrectLogin(String login){
        return login.matches(LOGIN_REGEX);
    }

    public static boolean isCorrectPassword(String pass){
        return pass.matches(PASSWORD_REGEX);
    }

}
