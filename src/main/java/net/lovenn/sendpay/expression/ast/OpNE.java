package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpNE extends Operator {

    public OpNE(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.NE, opl, opr);
    }
}
