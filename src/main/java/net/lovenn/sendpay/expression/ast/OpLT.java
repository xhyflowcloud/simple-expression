package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpLT extends Operator {

    public OpLT(Node opl, Node opr) {
        super(TokenKind.LT, opl, opr);
    }
}
