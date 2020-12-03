package net.lovenn.expression.util;

import net.lovenn.expression.handler.VariableConverter;
import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueConverter {

    private static final Pattern VARIABLE_REGEX = Pattern.compile("^@([a-zA-Z]+)(\\[(\\d+)(,(\\d+))?])?$");

    private static final Pattern BOOLEAN_REGEX = Pattern.compile("^(true|false)$");

    public static Object realValue(String value, SelExecuteContext context) throws SelExecuteException {
        SelExpression selExpression = context.getSelExpression();
        Matcher variableMatcher = VARIABLE_REGEX.matcher(value);
        if (variableMatcher.find()) {
            String variableName = variableMatcher.group(1);
            VariableConverter variableConverter = selExpression.getVariableConverter(variableName);
            if (variableConverter != null) {
                return variableConverter.handle(value, context);
            }
            Object realValue = context.get(variableName);
            if (realValue == null) {
                throw new SelExecuteException("Can not get real value for " + value + ".");
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
