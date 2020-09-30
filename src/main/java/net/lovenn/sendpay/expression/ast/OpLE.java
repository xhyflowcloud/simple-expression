package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpLE extends Operator {

    public OpLE(Node opl, Node opr) {
        super(TokenKind.LE, opl, opr);
    }
}
