package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNodeImpl;
import net.lovenn.expression.sel.TokenKind;

public class OpMultiply extends Operator {

    public OpMultiply(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.STAR, opl, opr);
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        throw new SelExecuteException("Not support operation.");
    }
}
