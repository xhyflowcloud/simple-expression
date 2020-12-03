package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.*;

public class OpGE extends Operator {

    public OpGE(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.GE, opl, opr);
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
        return handleGEOperation(lv, rv);
    }

    private Boolean handleGEOperation(Object lv, Object rv) throws SelExecuteException {
        if (isInteger(lv) && isInteger(rv)) {
            return parseInteger(lv) >= parseInteger(rv);
        }
        throw new SelExecuteException("Not support operation.");
    }
}
