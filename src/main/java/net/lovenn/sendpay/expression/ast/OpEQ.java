package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

public class OpEQ extends Operator {

    public OpEQ(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.EQ, opl, opr);
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
        return handleEQOperation(lv, rv);
    }

    private Boolean handleEQOperation(Object lv, Object rv) {
        if(lv.equals(rv)) {
            return true;
        }
        return false;
    }
}
