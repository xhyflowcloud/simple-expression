package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

public class Operator extends SelNodeImpl {

    public Operator(TokenKind kind, SelNodeImpl... children) {
        super(kind, children);
    }
}
