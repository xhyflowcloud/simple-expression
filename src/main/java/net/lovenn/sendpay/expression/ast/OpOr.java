package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpOr extends Operator {

    public OpOr(Node opl, Node opr) {
        super(TokenKind.SYMBOLIC_OR, opl, opr);
    }
}
