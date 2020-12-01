package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpGE extends Operator {

    public OpGE(SelNode opl, SelNode opr) {
        super(TokenKind.GE, opl, opr);
    }
}
