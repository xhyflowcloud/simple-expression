package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;
import net.lovenn.expression.sel.SelNode;
import net.lovenn.expression.sel.TokenKind;

public class OpNE extends Operator {

    public OpNE(SelNode opl, SelNode opr) {
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
        if (!lv.equals(rv)) {
            return true;
        }
        return false;
    }
}
