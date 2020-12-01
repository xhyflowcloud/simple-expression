package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpPlus extends Operator {

    public OpPlus(SelNode opl, SelNode opr) {
        super(TokenKind.PLUS, opl, opr);
    }
}
