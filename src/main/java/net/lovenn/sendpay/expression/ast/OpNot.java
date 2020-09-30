package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpNot extends Operator {

    public OpNot(Node op) {
        super(TokenKind.NOT, op);
    }
}
