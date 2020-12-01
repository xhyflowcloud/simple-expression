package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpOr extends Operator {

    public OpOr(SelNode opl, SelNode opr) {
        super(TokenKind.SYMBOLIC_AND, opl, opr);
    }
}
