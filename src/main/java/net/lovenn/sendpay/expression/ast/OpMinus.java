package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

public class OpMinus extends Operator {

    public OpMinus(SelNodeImpl opl, SelNodeImpl opr) {
        super(TokenKind.MINUS, opl, opr);
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        SelNode[] selNodes = getChildren();
        if(selNodes == null || selNodes.length < 2) {
            throw new SelExecuteException("Operand number error!");
        }
        int base = ((Integer) selNodes[0].getValue(context)) * 2;
        for (SelNode node: selNodes) {
            Object value = node.getValue(context);
            if(!(value instanceof Integer)) {
                throw new SelExecuteException("Not support type["+value.getClass().getSimpleName()+"]!");
            }
            base -= (Integer) value;
        }
        return base;
    }
}
