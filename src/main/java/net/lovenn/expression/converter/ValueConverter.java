package net.lovenn.expression.converter;

import net.lovenn.expression.sel.SelExecuteContext;

public interface ValueConverter {

    /**
     * 变量名
     */
    String getValueName();

    /**
     * 处理
     */
    Object convert(String value, SelExecuteContext context);
}
