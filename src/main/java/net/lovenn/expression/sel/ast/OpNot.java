package net.lovenn.expression.sel.ast;

import net.lovenn.expression.sel.*;
import net.lovenn.expression.util.ValueConverterUtils;

public class OpNot extends Operator {

    public OpNot(SelNode op) {
        super(TokenKind.NOT, op);
    }

    @SuppressWarnings("all")
    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        SelNode[] selNodes = getChildren();
        if (selNodes == null || selNodes.length != 1) {
            this.handleOperandNumberError();
        }
        Object lv = selNodes[0].getValue(context);
        return handleNotOperation(lv);
    }

    private Boolean handleNotOperation(Object value) throws SelExecuteException {
        if(ValueConverterUtils.isBoolean(value)) {
            return !ValueConverterUtils.convertBoolean(value);
        }
        throw new SelExecuteException("Not support operation.");
    }
}
