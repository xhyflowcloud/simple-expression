package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpMultiply extends Operator {

    public OpMultiply(Node opl, Node opr) {
        super(TokenKind.STAR, opl, opr);
    }
}
