package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpNE extends Operator {

    public OpNE(Node opl, Node opr) {
        super(TokenKind.NE, opl, opr);
    }
}
