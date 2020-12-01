package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpLT extends Operator {

    public OpLT(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.LT, opl, opr);
    }
}
