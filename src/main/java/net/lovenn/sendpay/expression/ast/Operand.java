package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelExecuteContext;
import net.lovenn.sendpay.expression.SelExecuteException;
import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

public class Operand extends SelNodeImpl {
    private String value;

    public Operand(TokenKind kind, String value) {
        super(kind);
        this.value = value;
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        return this.value;
    }
}
