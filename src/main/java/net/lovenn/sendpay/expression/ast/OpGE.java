package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpGE extends Operator {

    public OpGE(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.GE, opl, opr);
    }
}
