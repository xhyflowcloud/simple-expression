package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNodeImpl;
import net.lovenn.expression.sel.TokenKind;
import net.lovenn.expression.util.ValueConverter;

public class Operand extends SelNodeImpl {

    private String value;

    public Operand(TokenKind kind, String value) {
        super(kind);
        this.value = value;
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        return ValueConverter.realValue(value, context);
    }
}
