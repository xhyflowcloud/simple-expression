package net.lovenn.expression.handler;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;

public interface VariableConverter {

    /**
     * 变量名
     */
    String getVariableName();

    /**
     * 处理
     */
    Object handle(String variable, SelExecuteContext context) throws SelExecuteException;
}
