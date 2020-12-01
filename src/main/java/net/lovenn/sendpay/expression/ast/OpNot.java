package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpNot extends Operator {

    public OpNot(SelNode op) {
        super(TokenKind.NOT, op);
    }
}
