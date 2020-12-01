package net.lovenn.sendpay.expression;

public class SelParseException extends Exception {

    public SelParseException() {
    }

    public SelParseException(String message) {
        super(message);
    }

    public SelParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelParseException(Throwable cause) {
        super(cause);
    }

    public SelParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
