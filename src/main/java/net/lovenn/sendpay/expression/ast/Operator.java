package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

public class Operator extends SelNode {

    public Operator(TokenKind kind, SelNode... children) {
        super(kind, children);
    }
}
