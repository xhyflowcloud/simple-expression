package net.lovenn.sendpay.expression;

public class SelSelExpressionImpl implements SelExpression {

    private final String expressionString;

    private final SelNode ast;

    public SelSelExpressionImpl(String expressionString, SelNode ast) {
        this.expressionString = expressionString;
        this.ast = ast;
    }

    @Override
    public String getExpressionString() {
        return expressionString;
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        return ast.getValue(context);
    }

    @Override
    public TokenKind tokenKind() {
        return null;
    }

    @Override
    public SelNode parent() {
        return null;
    }
}
