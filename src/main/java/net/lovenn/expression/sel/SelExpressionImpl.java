package net.lovenn.expression.sel;

import net.lovenn.expression.handler.VariableHandler;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SelExpressionImpl implements SelExpression {

    private ConcurrentHashMap<String, VariableHandler> handlerDict = new ConcurrentHashMap<String, VariableHandler>();

    private final String expressionString;

    private final SelNode ast;

    SelExpressionImpl(String expressionString, SelNode ast, List<VariableHandler> variableHandlerList) {
        this.expressionString = expressionString;
        this.ast = ast;
        if(variableHandlerList != null) {
            for (VariableHandler variableHandler: variableHandlerList) {
                handlerDict.put(variableHandler.getVariableName(), variableHandler);
            }
        }
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
