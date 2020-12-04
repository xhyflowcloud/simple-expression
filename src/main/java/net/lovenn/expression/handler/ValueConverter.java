package net.lovenn.expression.handler;

import net.lovenn.expression.sel.SelExecuteContext;

public interface ValueConverter {

    /**
     * 变量名
     */
    String getValueName();

    /**
     * 处理
     */
    Object convert(String variable, SelExecuteContext context);
}
