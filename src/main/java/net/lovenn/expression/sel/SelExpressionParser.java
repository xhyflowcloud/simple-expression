package net.lovenn.expression.sel;

import net.lovenn.expression.converter.ValueConverter;

import java.util.concurrent.CopyOnWriteArrayList;

public class SelExpressionParser {

    private CopyOnWriteArrayList<ValueConverter> valueConverters = new CopyOnWriteArrayList<ValueConverter>();

    public SelExpression getExpression(String expressString, SelParserContext context) throws SelParseException {
        InternalSelExpressionParser internalSelExpressionParser = new InternalSelExpressionParser();
        SelExpression selExpression = internalSelExpressionParser.doParseExpression(expressString.replaceAll("\\s+", ""), context, valueConverters);
        return selExpression;
    }

    public void registerVariableConverter(ValueConverter valueConverter) {
        valueConverters.add(valueConverter);
    }
}
