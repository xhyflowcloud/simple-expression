package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpEQ extends Operator {

    public OpEQ(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.EQ, opl, opr);
    }
}
