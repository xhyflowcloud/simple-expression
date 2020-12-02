package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelExecuteContext;
import net.lovenn.sendpay.expression.SelExecuteException;
import net.lovenn.sendpay.expression.SelNode;
import net.lovenn.sendpay.expression.TokenKind;

public class OpPlus extends Operator {

    public OpPlus(SelNode opl, SelNode opr) {
        super(TokenKind.PLUS, opl, opr);
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
        return handlePlusOperation(lv, rv);
    }

    private Integer handlePlusOperation(Object lv, Object rv) throws SelExecuteException {
        if (isInteger(lv) && isInteger(rv)) {
            return parseInteger(lv) + parseInteger(rv);
        }
        throw new SelExecuteException("Not support operation.");
    }
}
