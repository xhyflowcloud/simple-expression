package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpGT extends Operator {

    public OpGT(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.GT, opl, opr);
    }
}
