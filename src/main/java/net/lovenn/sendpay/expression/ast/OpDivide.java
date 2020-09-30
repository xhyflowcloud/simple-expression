package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpDivide extends Operator {

    public OpDivide(Node opl, Node opr) {
        super(TokenKind.DIV, opl, opr);
    }
}
