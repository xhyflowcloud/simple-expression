package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpEQ extends Operator {

    public OpEQ(Node opl, Node opr) {
        super(TokenKind.EQ, opl, opr);
    }
}
