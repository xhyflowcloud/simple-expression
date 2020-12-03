package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNode;
import net.lovenn.expression.sel.TokenKind;

public class OpAnd extends Operator {

    public OpAnd(SelNode opl, SelNode opr) {
        super(TokenKind.SYMBOLIC_AND, opl, opr);
    }

    @SuppressWarnings("all")
    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        SelNode[] selNodes = getChildren();
        if (selNodes == null || selNodes.length != 2) {
            this.handleOperandNumberError();
        }
        Object lv = selNodes[0].getValue(context);
        Object rv = selNodes[1].getValue(context);
        return handleAndOperation(lv, rv);
    }

    private Boolean handleAndOperation(Object lv, Object rv) throws SelExecuteException {
        if (isBoolean(lv) && isBoolean(rv)) {
            return parseBoolean(lv) && parseBoolean(rv);
        }
        throw new SelExecuteException("Not support operation.");
    }
}
