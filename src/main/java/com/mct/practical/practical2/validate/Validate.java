package com.mct.practical.practical2.validate;

import java.util.regex.Pattern;

public class Validate {

    public static boolean isValidCode(String field) {
        String pattern = "[A-Z]{2}[0-9]{4}";
        return match(pattern, field);
    }

    public static boolean isValidName(String field) {
        String pattern = "[a-zA-Z0-9\\s]{1,255}";
        return match(pattern, field);
    }

    public static boolean isValidPrice(String field) {
        try {
            return Float.parseFloat(field) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDescription(String field) {
        return field != null && !field.trim().isEmpty();
    }

    public static int getValidPage(String field, int def) {
        String pattern = "[0-9]{1,}";
        return field != null && match(pattern, field) ? Integer.parseInt(field) : def;
    }

    private static boolean match(String pattern, String field) {
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(field).matches();
    }
}
