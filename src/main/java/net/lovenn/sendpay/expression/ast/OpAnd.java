package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

public class OpAnd extends Operator {

    public OpAnd(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.SYMBOLIC_AND, opl, opr);
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
      return null;
    }
}
