package net.lovenn.sendpay;

import com.alibaba.fastjson.JSON;
import net.lovenn.sendpay.expression.InternalSelExpressionParser;
import net.lovenn.sendpay.expression.ParseException;
import net.lovenn.sendpay.expression.Tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationTest {
    private static final Pattern VAR_REGEX = Pattern.compile("^@([0-9a-zA-Z]+)(\\[(\\d+),(\\d+)])?$");

    public static void main(String[] args) {
        testTokenizer();
        //testVarRegex();
        //testEatExpression();
    }


    private static void testTokenizer() {
        {
            String express = "(1 + 2) * 4 + 5 * 5";
            Tokenizer tokenizer = new Tokenizer(express);
            System.out.println(JSON.toJSONString(tokenizer.process()));;
        }

        {
            String express = "(sendpay[1,2] + 2) * 4 + 5 * 5";
            Tokenizer tokenizer = new Tokenizer(express);
            System.out.println(JSON.toJSONString(tokenizer.process()));;
        }
    }

    private static void testVarRegex() {
        {
            String express = "@sendpay[8,9]";
            Matcher matcher = VAR_REGEX.matcher(express);

//            while (matcher.find()) {
//                System.out.println(1);
//            }
            matcher.find();
            System.out.println(matcher.group());
        }
    }

    private static void testEatExpression() {
        {
            try {
                String express = "(1 + 2) * 4 + 5 * 5";
                InternalSelExpressionParser parser = new InternalSelExpressionParser();
                parser.doParseExpression(express, null);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
