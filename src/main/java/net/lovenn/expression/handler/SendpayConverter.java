package net.lovenn.expression.handler;

import net.lovenn.expression.sel.SelExecuteContext;
import net.lovenn.expression.sel.SelExecuteException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendpayConverter implements VariableConverter {
    private static final Pattern SENDPAY_REGEX = Pattern.compile("^@sendpay\\[(\\d+)(,(\\d+))?]$");

    @Override
    public String getVariableName() {
        return "sendpay";
    }

    @Override
    public Object handle(String variable, SelExecuteContext context) throws SelExecuteException {
        Matcher matcher = SENDPAY_REGEX.matcher(variable);
        if (matcher.find()) {
            int s, e;
            if (matcher.group(3) == null) {
                s = Integer.parseInt(matcher.group(1));
                e = s;
            } else {
                s = Integer.parseInt(matcher.group(1));
                e = Integer.parseInt(matcher.group(3));
            }
            if (s > e) {
                int t = s;
                s = e;
                e = t;
            }
            if (s < 1) {
                throw new SelExecuteException("Range is illegal!");
            }

            String sendpay = (String) context.get("sendpay");
            if (sendpay == null) {
                throw new SelExecuteException("Can not find variable[sendpay]");
            }

            if (sendpay.length() < s) {
                throw new SelExecuteException("sendpay too short");
            }
            return sendpay.substring(s - 1, e);
        }
        throw new SelExecuteException("Can not apply handler[" + this.getClass().getSimpleName() + "] for variable[" + variable + "]");
    }
}
