package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.*;

import java.util.regex.Pattern;

public class OpPlus extends Operator {

    private static final Pattern DIGIT_REGEX = Pattern.compile("^\\d+$");

    public OpPlus(SelNode opl, SelNode opr) {
        super(TokenKind.PLUS, opl, opr);
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        SelNode[] selNodes = getChildren();
        if(selNodes == null || selNodes.length != 2) {
            throw new SelExecuteException("Express error!");
        }
        int base = 0;
        for (SelNode node: selNodes) {
            Object value = node.getValue(context);
            if(!isDigit((String) value)) {
                throw new SelExecuteException("Not support type["+value.getClass().getName()+"]!");
            }
            base += Integer.parseInt((String) value);
        }
        return base;
    }

    private boolean isDigit(String value) {
        return DIGIT_REGEX.matcher(value).find();
    }
}
