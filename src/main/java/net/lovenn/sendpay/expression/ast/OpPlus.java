package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpPlus extends Operator {

    public OpPlus(Node opl, Node opr) {
        super(TokenKind.PLUS, opl, opr);
    }
}
