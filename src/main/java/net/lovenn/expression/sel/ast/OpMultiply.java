package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.*;

public class OpMultiply extends Operator {

    public OpMultiply(SelNode opl, SelNode opr) {
        super(TokenKind.STAR, opl, opr);
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        throw new SelExecuteException("Not support operation.");
    }
}
