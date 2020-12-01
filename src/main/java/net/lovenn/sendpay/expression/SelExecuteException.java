package net.lovenn.sendpay.expression;

public class SelExecuteException extends Exception {

    public SelExecuteException() {
    }

    public SelExecuteException(String message) {
        super(message);
    }

    public SelExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelExecuteException(Throwable cause) {
        super(cause);
    }

    public SelExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
