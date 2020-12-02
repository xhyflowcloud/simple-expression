package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelExecuteContext;
import net.lovenn.sendpay.expression.SelExecuteException;
import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpDivide extends Operator {

    public OpDivide(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.DIV, opl, opr);
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        throw new SelExecuteException("Not support operation.");
    }
}
