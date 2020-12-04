package net.lovenn.expression.sel;

import net.lovenn.expression.converter.ValueConverter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SelExpressionImpl implements SelExpression {

    private ConcurrentHashMap<String, ValueConverter> valueConverterDict = new ConcurrentHashMap<String, ValueConverter>();

    private final String expressionString;

    private final SelNode ast;

    SelExpressionImpl(String expressionString, SelNode ast, List<ValueConverter> valueConverterList) {
        this.expressionString = expressionString;
        this.ast = ast;
        if (valueConverterList != null) {
            for (ValueConverter valueConverter : valueConverterList) {
                valueConverterDict.put(valueConverter.getValueName(), valueConverter);
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
        try {
            return ast.getValue(context);
        } catch (SelExecuteException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            throw new SelExecuteException(e);
        }
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
    public ValueConverter getValueConverter(String variableName) {
        return valueConverterDict.get(variableName);
    }
}
