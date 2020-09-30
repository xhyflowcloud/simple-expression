package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.Node;
import net.lovenn.sendpay.expression.TokenKind;

public class Operand extends Node {
    private String value;

    public Operand(TokenKind kind, String value) {
        super(kind);
        this.value = value;
    }
}
