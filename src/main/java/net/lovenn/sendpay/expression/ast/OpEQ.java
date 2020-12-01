package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpEQ extends Operator {

    public OpEQ(SelNode opl, SelNode opr) {
        super(TokenKind.EQ, opl, opr);
    }
}
