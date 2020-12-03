package net.lovenn.expression.sel;

import net.lovenn.expression.handler.VariableConverter;

import java.util.concurrent.CopyOnWriteArrayList;

public class SelExpressionParser {

    private CopyOnWriteArrayList<VariableConverter> variableConverters = new CopyOnWriteArrayList<VariableConverter>();

    public SelExpression getExpression(String expressString, SelParserContext context) throws SelParseException {
        InternalSelExpressionParser internalSelExpressionParser = new InternalSelExpressionParser();
        SelExpression selExpression = internalSelExpressionParser.doParseExpression(expressString.replaceAll("\\s+", ""), context, variableConverters);
        return selExpression;
    }

    public void registerVariableHandler(VariableConverter variableConverter) {
        variableConverters.add(variableConverter);
    }
}
