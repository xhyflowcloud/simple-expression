package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNode;
import net.lovenn.expression.sel.SelNodeImpl;
import net.lovenn.expression.sel.TokenKind;

public class Operator extends SelNodeImpl {

    public Operator(TokenKind kind, SelNode... children) {
        super(kind, children);
    }

    protected void handleOperandNumberError() throws SelExecuteException {
        int number = this.getChildren() == null ? 0 : this.getChildren().length;
        throw new SelExecuteException("Operator[" + this.getTokenKind().name() + "]'s operand number error, actual number is " + number + ".");
    }
}
