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
    public void setTokenKind(TokenKind tokenKind) {

    }

    @Override
    public TokenKind getTokenKind() {
        return null;
    }

    @Override
    public void setParent(SelNode parent) {

    }

    @Override
    public SelNode getParent() {
        return null;
    }

    @Override
    public void setChildren(SelNode... children) {

    }

    @Override
    public SelNode[] getChildren() {
        return null;
    }
}
