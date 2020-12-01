package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpMultiply extends Operator {

    public OpMultiply(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.STAR, opl, opr);
    }
}
