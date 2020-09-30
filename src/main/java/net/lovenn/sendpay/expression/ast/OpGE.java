package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpGE extends Operator {

    public OpGE(Node opl, Node opr) {
        super(TokenKind.GE, opl, opr);
    }
}
