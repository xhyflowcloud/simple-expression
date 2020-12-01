package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpLE extends Operator {

    public OpLE(SelNode opl, SelNode opr) {
        super(TokenKind.LE, opl, opr);
    }
}
