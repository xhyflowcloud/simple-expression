package net.lovenn.expression;

import net.lovenn.expression.converter.SendpayConverter;
import net.lovenn.expression.sel.*;

import java.util.regex.Pattern;

public class ApplicationTest {
    private static final Pattern VAR_REGEX = Pattern.compile("^@([0-9a-zA-Z]+)(\\[(\\d+),(\\d+)])?$");

    public static void main(String[] args) {
        //testTokenizer();
        //testVarRegex();
        testEatExpression();
    }

    private static void testEatExpression() {
        {
            try {
//                String express = "((1 + 2) * 4 > @sendpay[1,3] || @sendpay[174,191] == qwe) && @sendpay[66, 66] == 1";
                String express = "((1 + 2) + 4 > @sendpay[1,3] || @sendpay[3] == qwe) && @sendpay[4] == 1";
                SelExpressionParser parser = new SelExpressionParser();
                parser.registerVariableConverter(new SendpayConverter());
                SelExpression selExpression = parser.getExpression(express, null);
                SelExecuteContext context = new SelExecuteContext();
                context.add("sendpay", "100000");
                context.add("orderType", "1");
                System.out.println(selExpression.getValue(context));
            } catch (SelParseException e) {
                e.printStackTrace();
            } catch (SelExecuteException e) {
                e.printStackTrace();
            }
        }
    }
}
