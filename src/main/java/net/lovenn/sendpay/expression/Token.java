package net.lovenn.sendpay.expression;

public class Token {

    TokenKind kind;

    String data;

    public Token(TokenKind kind, String data) {
        this.kind = kind;
        this.data = data;
    }

    public Token(TokenKind kind, char[] data) {
        this.kind = kind;
        this.data = new String(data);
    }
}
