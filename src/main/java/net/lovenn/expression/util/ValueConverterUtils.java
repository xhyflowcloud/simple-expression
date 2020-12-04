package net.lovenn.expression.util;

import net.lovenn.expression.converter.ValueConverter;
import net.lovenn.expression.sel.SelConvertException;
import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueConverterUtils {

    private static final Pattern INTEGER_REGEX = Pattern.compile("^\\d+$");

    private static final Pattern VARIABLE_REGEX = Pattern.compile("^@([a-zA-Z]+)(\\[(\\d+)(,(\\d+))?])?$");

    private static final Pattern BOOLEAN_REGEX = Pattern.compile("^(true|false)$");

    public static boolean isInteger(Object value) {
        if (value instanceof Integer) {
            return true;
        }
        if (value instanceof String) {
            return INTEGER_REGEX.matcher((String) value).find();
        }
        return false;
    }

    public static Integer convertInteger(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof String) {
            return Integer.parseInt((String) value);
        }
        throw new SelConvertException("Can not convert " + value.getClass().getName() + " to Integer");
    }

    public static boolean isBoolean(Object value) {
        if (value instanceof Boolean) {
            return true;
        }
        if (value instanceof String) {
            return BOOLEAN_REGEX.matcher((String) value).find();
        }
        return false;
    }

    public static Boolean convertBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }

        throw new SelConvertException("Can not convert " + value.getClass().getName() + " to Boolean");

    }

    public static Object realValue(String value, SelExecuteContext context) {
        SelExpression selExpression = context.getSelExpression();
        Matcher valueMatcher = VARIABLE_REGEX.matcher(value);
        if (valueMatcher.find()) {
            String variableName = valueMatcher.group(1);
            ValueConverter valueConverter = selExpression.getValueConverter(variableName);
            if (valueConverter != null) {
                return valueConverter.convert(value, context);
            }
            Object realValue = context.get(variableName);
            if (realValue == null) {
                throw new SelConvertException("Can not get real value for " + value + ".");
            }
            return realValue;
        }
        Matcher booleanMatcher = BOOLEAN_REGEX.matcher(value);
        if (booleanMatcher.find()) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }

    public static void main(String[] args) {
        Matcher matcher = VARIABLE_REGEX.matcher("@sendpay[1,1]");
        System.out.println(matcher.find());
    }
}
