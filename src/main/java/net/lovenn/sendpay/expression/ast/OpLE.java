package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpLE extends Operator {

    public OpLE(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.LE, opl, opr);
    }
}
