package net.lovenn.expression.sel;

import net.lovenn.expression.handler.VariableHandler;

import java.util.concurrent.CopyOnWriteArrayList;

public class SelExpressionParser {

    private CopyOnWriteArrayList<VariableHandler> variableHandlers = new CopyOnWriteArrayList<VariableHandler>();

    public SelExpression getExpression(String expressString, SelParserContext context) throws SelParseException {
        InternalSelExpressionParser internalSelExpressionParser = new InternalSelExpressionParser();
        SelExpression selExpression = internalSelExpressionParser.doParseExpression(expressString.replaceAll("\\s+", ""), context, variableHandlers);
        return selExpression;
    }

    public void registerVariableHandler(VariableHandler variableHandler) {
        variableHandlers.add(variableHandler);
    }
}
