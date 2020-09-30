package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class OpMinus extends Operator {

    public OpMinus(Node opl, Node opr) {
        super(TokenKind.MINUS, opl, opr);
    }
}
