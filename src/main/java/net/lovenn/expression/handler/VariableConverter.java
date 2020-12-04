package net.lovenn.expression.handler;

import net.lovenn.expression.sel.SelExecuteContext;

public interface VariableConverter {

    /**
     * 变量名
     */
    String getVariableName();

    /**
     * 处理
     */
    Object convert(String variable, SelExecuteContext context);
}
