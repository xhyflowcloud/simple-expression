package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpNot extends Operator {

    public OpNot(SelNodeImpl op) {
        super(TokenKind.NOT, op);
    }
}
