package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpLT extends Operator {

    public OpLT(SelNode opl, SelNode opr) {
        super(TokenKind.LT, opl, opr);
    }
}
