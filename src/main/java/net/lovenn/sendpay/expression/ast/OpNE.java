package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

public class OpNE extends Operator {

    public OpNE(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.NE, opl, opr);
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
        return handleNEOperation(lv, rv);
    }

    private Boolean handleNEOperation(Object lv, Object rv) {
        if(!lv.equals(rv)) {
            return true;
        }
        return false;
    }
}
