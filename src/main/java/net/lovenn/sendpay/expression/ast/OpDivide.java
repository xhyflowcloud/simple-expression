package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpDivide extends Operator {

    public OpDivide(SelNode opl, SelNode opr) {
        super(TokenKind.DIV, opl, opr);
    }
}
