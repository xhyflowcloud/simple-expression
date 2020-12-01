package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpMinus extends Operator {

    public OpMinus(SelNode opl, SelNode opr) {
        super(TokenKind.MINUS, opl, opr);
    }
}
