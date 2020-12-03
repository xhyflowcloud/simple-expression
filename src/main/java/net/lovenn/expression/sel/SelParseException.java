package net.lovenn.expression.sel;

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
}
