package net.lovenn.expression.sel;

public class SelExpressionParser {

    public SelExpression getExpression(String expressString, SelParserContext context) throws SelParseException {
        InternalSelExpressionParser internalSelExpressionParser = new InternalSelExpressionParser();
        return internalSelExpressionParser.doParseExpression(expressString.replaceAll("\\s+", ""), context);
    }
}
