package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class OpOr extends Operator {

    public OpOr(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.SYMBOLIC_OR, opl, opr);
    }
}
