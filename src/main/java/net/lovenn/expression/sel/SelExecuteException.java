package net.lovenn.expression.sel;

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
}
