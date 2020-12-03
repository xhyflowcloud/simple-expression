package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.*;

public class OpOr extends Operator {

    public OpOr(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.SYMBOLIC_OR, opl, opr);
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
        return handleOrOperation(lv, rv);
    }

    private Boolean handleOrOperation(Object lv, Object rv) throws SelExecuteException {
        if(isBoolean(lv) && isBoolean(rv)) {
            return parseBoolean(lv) || parseBoolean(rv);
        }
        throw new SelExecuteException("Not support operation.");
    }
}