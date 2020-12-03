package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNode;
import net.lovenn.expression.sel.SelNodeImpl;
import net.lovenn.expression.sel.TokenKind;

import java.util.regex.Pattern;

public class Operator extends SelNodeImpl {
    private static final Pattern INTEGER_REGEX = Pattern.compile("^\\d+$");

    private static final Pattern BOOLEAN_REGEX = Pattern.compile("^(true|false)$");

    public Operator(TokenKind kind, SelNode... children) {
        super(kind, children);
    }

    boolean isInteger(Object value) {
        if (value instanceof Integer) {
            return true;
        }
        if (value instanceof String) {
            return INTEGER_REGEX.matcher((String) value).find();
        }
        return false;
    }

    Integer parseInteger(Object value) throws SelExecuteException {
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof String) {
            return Integer.parseInt((String) value);
        }
        throw new SelExecuteException("Not support operation.");
    }

    boolean isBoolean(Object value) {
        if(value instanceof Boolean) {
            return true;
        }
        if(value instanceof String) {
            return BOOLEAN_REGEX.matcher((String)value).find();
        }
        return false;
    }

    Boolean parseBoolean(Object value) throws SelExecuteException {
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }
        throw new SelExecuteException("Not support operation.");
    }

    protected void handleOperandNumberError() throws SelExecuteException {
        int number = this.getChildren() == null ? 0 : this.getChildren().length;
        throw new SelExecuteException("Operator[" + this.getTokenKind().name() + "]'s operand number error, actual number is " + number + ".");
    }
}
