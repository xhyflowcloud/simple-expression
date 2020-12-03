package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNode;
import net.lovenn.expression.sel.TokenKind;

public class OpMinus extends Operator {

    public OpMinus(SelNode opl, SelNode opr) {
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
