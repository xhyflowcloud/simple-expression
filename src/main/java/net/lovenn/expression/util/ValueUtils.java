package net.lovenn.expression.util;

import java.util.regex.Pattern;

public class ValueUtils {

    private static final Pattern BLANK_REGEX = Pattern.compile("^\\s+$");

    public static boolean isBlank(String str) {
        if(str == null) {
            return true;
        }
        return BLANK_REGEX.matcher(str).find();
    }
}
