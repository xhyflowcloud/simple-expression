package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpMultiply extends Operator {

    public OpMultiply(SelNode opl, SelNode opr) {
        super(TokenKind.STAR, opl, opr);
    }
}
