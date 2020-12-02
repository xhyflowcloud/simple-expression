package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

public class OpMinus extends Operator {

    public OpMinus(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.MINUS, opl, opr);
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
        return handleMinusOperation(lv, rv);
    }

    private Integer handleMinusOperation(Object lv, Object rv) throws SelExecuteException {
        if (isInteger(lv) && isInteger(rv)) {
            return parseInteger(lv) - parseInteger(rv);
        }
        throw new SelExecuteException("Not support operation.");
    }
}
