package net.lovenn.sendpay.expression.ast;

import net.lovenn.sendpay.expression.SelExecuteContext;
import net.lovenn.sendpay.expression.SelExecuteException;
import net.lovenn.sendpay.expression.SelNodeImpl;
import net.lovenn.sendpay.expression.TokenKind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operand extends SelNodeImpl {

    private static final Pattern SENDPAY_REGEX = Pattern.compile("^@sendpay\\[(\\d+)(,(\\d+))?]$");

    private String value;

    public Operand(TokenKind kind, String value) {
        super(kind);
        this.value = value;
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        if(value == null) {
            throw new SelExecuteException("Operand can not null!");
        }
        if(value.startsWith("@")) {
            return handleVariable(value, context);
        }
        if(value.equals("true") || value.equals("false")) {
            return Boolean.parseBoolean(value);
        }
        return this.value;
    }

    private Object handleVariable(String value, SelExecuteContext context) throws SelExecuteException {
        Matcher matcher = SENDPAY_REGEX.matcher(value);
        if(matcher.find()) {
            int s,e;
            if(matcher.group(3) == null) {
                s = Integer.parseInt(matcher.group(1));
                e = s;
            } else {
                s = Integer.parseInt(matcher.group(1));
                e = Integer.parseInt(matcher.group(3));
            }
            if(s > e) {
                int t = s;
                s = e;
                e = t;
            }
            if(s < 1) {
                throw new SelExecuteException("Range is illegal");
            }

            String sendpay = (String) context.get("sendpay");
            if(sendpay == null) {
                throw new SelExecuteException("Can not find variable[sendpay]");
            }

            if(sendpay.length() < s) {
                throw new SelExecuteException("sendpay too short");
            }
            return sendpay.substring(s - 1, e);
        }

        Object var = context.get(value.substring(1));
        if(var == null) {
            throw new SelExecuteException("Can not find variable["+value+"]");
        }
        return var;
    }
}
