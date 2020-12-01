package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpGT extends Operator {

    public OpGT(SelNode opl, SelNode opr) {
        super(TokenKind.GT, opl, opr);
    }
}
