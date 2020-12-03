package net.lovenn.expression.handler;

import net.lovenn.expression.sel.SelExecuteContext;

public interface VariableHandler {

    /**
     * 变量名
     */
    String getVariableName();

    /**
     * 处理
     */
    Object handle(String variable, SelExecuteContext context);
}
