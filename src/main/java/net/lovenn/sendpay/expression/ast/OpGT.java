package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpGT extends Operator {

    public OpGT(Node opl, Node opr) {
        super(TokenKind.GT, opl, opr);
    }
}
