package net.lovenn.sendpay.parse;

public enum  Notation {
    NODE(""),
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")"),
    PLUS("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    VARIABLE("@"),
    LEFT_RANG_BRACKET("["),
    RIGHT_LEFT_RANG_BRACKET("]"),
    TO("-"),
    GREATER(">"),
    LESS("<"),
    EQUAL("="),
    AND("&&"),
    OR("||"),
    NOT("!"),
    ;

    String value;

    Notation(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(Notation.valueOf("LEFT_BRACKET"));
    }
}
