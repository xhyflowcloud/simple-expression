package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNode;
import net.lovenn.expression.sel.TokenKind;
import net.lovenn.expression.util.ValueConverterUtils;

public class OpGE extends Operator {

    public OpGE(SelNode opl, SelNode opr) {
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
        if (ValueConverterUtils.isInteger(lv) && ValueConverterUtils.isInteger(rv)) {
            return ValueConverterUtils.convertInteger(lv) >= ValueConverterUtils.convertInteger(rv);
        }
        throw new SelExecuteException("Not support operation.");
    }
}
