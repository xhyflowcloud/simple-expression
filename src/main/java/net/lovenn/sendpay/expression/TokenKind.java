package net.lovenn.sendpay.expression;

public enum TokenKind {

    LPAREN("("),

    RPAREN(")"),

    COMMA(","),

    RSQUARE("]"),

    LSQUARE("["),

    DOT("."),

    PLUS("+"),

    STAR("*"),

    MINUS("-"),

    DIV("/"),

    GE(">="),

    GT(">"),

    LE("<="),

    LT("<"),

    EQ("=="),

    NE("!="),

    MOD("%"),

    NOT("!"),

    ASSIGN("="),

    BEAN_REF("@"),

    SYMBOLIC_OR("||"),

    SYMBOLIC_AND("&&"),

    IDENTIFIER;

    final char[] tokenChars;

    private final boolean hasPayload;  // is there more to this token than simply the kind


    private TokenKind(String tokenString) {
        this.tokenChars = tokenString.toCharArray();
        this.hasPayload = (this.tokenChars.length == 0);
    }

    private TokenKind() {
        this("");
    }


    @Override
    public String toString() {
        return new String(tokenChars);
    }

    public boolean hasPayload() {
        return this.hasPayload;
    }

    public int getLength() {
        return this.tokenChars.length;
    }
}