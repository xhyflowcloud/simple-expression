package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpNE extends Operator {

    public OpNE(SelNode opl, SelNode opr) {
        super(TokenKind.NE, opl, opr);
    }
}
