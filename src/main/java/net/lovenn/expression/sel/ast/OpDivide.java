package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNodeImpl;
import net.lovenn.expression.sel.TokenKind;

public class OpDivide extends Operator {

    public OpDivide(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.DIV, opl, opr);
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        throw new SelExecuteException("Not support operation.");
    }
}
