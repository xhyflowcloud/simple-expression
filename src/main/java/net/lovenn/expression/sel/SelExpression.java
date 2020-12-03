package net.lovenn.expression.sel;

import net.lovenn.expression.handler.VariableConverter;

public interface SelExpression extends SelNode {

    String getExpressionString();

    VariableConverter getVariableConverter(String variableName);
}
