package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpAnd extends Operator {

    public OpAnd(SelNode opl, SelNode opr) {
        super(TokenKind.SYMBOLIC_OR, opl, opr);
    }
}
