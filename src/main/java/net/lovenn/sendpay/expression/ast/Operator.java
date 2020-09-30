package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

public class Operator extends Node {

    public Operator(TokenKind kind, Node... children) {
        super(kind, children);
    }
}
