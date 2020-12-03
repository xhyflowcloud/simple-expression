package net.lovenn.expression.sel;

import net.lovenn.expression.handler.VariableConverter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SelExpressionImpl implements SelExpression {

    private ConcurrentHashMap<String, VariableConverter> handlerDict = new ConcurrentHashMap<String, VariableConverter>();

    private final String expressionString;

    private final SelNode ast;

    SelExpressionImpl(String expressionString, SelNode ast, List<VariableConverter> variableConverterList) {
        this.expressionString = expressionString;
        this.ast = ast;
        if(variableConverterList != null) {
            for (VariableConverter variableConverter : variableConverterList) {
                handlerDict.put(variableConverter.getVariableName(), variableConverter);
            }
        }
    }

    @Override
    public String getExpressionString() {
        return expressionString;
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        context.setSelExpression(this);
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

    @Override
    public VariableConverter getVariableConverter(String variableName) {
        return handlerDict.get(variableName);
    }
}
