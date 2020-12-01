package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class Operand extends SelNode {
    private String value;

    public Operand(TokenKind kind, String value) {
        super(kind);
        this.value = value;
    }
}
