package net.lovenn.sendpay.expression;

public class SelExpression implements Expression {

    private final String expression;

    private final SelNode ast;

    private final ParserContext parserContext;

    public SelExpression(String expression, SelNode ast, ParserContext parserContext) {
        this.expression = expression;
        this.ast = ast;
        this.parserContext = parserContext;
    }

    @Override
    public String getExpressionString() {
        return null;
    }


}
