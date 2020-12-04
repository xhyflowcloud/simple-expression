package net.lovenn.expression.converter;

import net.lovenn.expression.sel.SelConvertException;
import net.lovenn.expression.sel.SelExecuteContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendpayConverter implements ValueConverter {
    private static final Pattern SENDPAY_REGEX = Pattern.compile("^@sendpay\\[(\\d+)(,(\\d+))?]$");

    @Override
    public String getValueName() {
        return "sendpay";
    }

    @Override
    public Object convert(String value, SelExecuteContext context) {
        Matcher matcher = SENDPAY_REGEX.matcher(value);
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
                throw new SelConvertException("Range is illegal!");
            }

            String sendpay = (String) context.get("sendpay");
            if (sendpay == null) {
                throw new SelConvertException("Can not find variable[sendpay].");
            }

            if (sendpay.length() < s) {
                throw new SelConvertException("sendpay too short!");
            }
            return sendpay.substring(s - 1, e);
        }
        throw new SelConvertException("Can not apply converter[" + this.getClass().getSimpleName() + "] for value[" + value + "]");
    }
}
