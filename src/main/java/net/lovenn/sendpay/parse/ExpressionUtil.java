package net.lovenn.sendpay.parse;

import java.util.Stack;

public class ExpressionUtil {

    public static Expression parse(String expressionText) {

        //空白字符直接返回
        if (StringUtils.isBlank(expressionText)) {
            return null;
        }

        //预处理表达式文本
        //除去空白字符
        expressionText = expressionText.replaceAll("\\s+", "");
        Expression expression = new Expression();
        for (int i = 0; i < expressionText.length(); i++) {
            if(expressionText.charAt(i) == '(') {

            }
        }
        return null;
    }
}
