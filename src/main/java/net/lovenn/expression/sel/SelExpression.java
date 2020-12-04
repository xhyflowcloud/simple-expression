package net.lovenn.expression.sel;

import net.lovenn.expression.handler.ValueConverter;

public interface SelExpression extends SelNode {

    String getExpressionString();

    ValueConverter getValueConverter(String valueName);
}
