package net.lovenn.sendpay.expression;

import net.lovenn.sendpay.parse.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {
    // If this gets changed, it must remain sorted...
    private static final String[] ALTERNATIVE_OPERATOR_NAMES =
            {"DIV", "EQ", "GE", "GT", "LE", "LT", "MOD", "NE", "NOT"};

    private static final byte[] FLAGS = new byte[256];

    private static final byte IS_DIGIT = 0x01;

    private static final byte IS_HEXDIGIT = 0x02;

    private static final byte IS_ALPHA = 0x04;

    static {
        for (int ch = '0'; ch <= '9'; ch++) {
            FLAGS[ch] |= IS_DIGIT | IS_HEXDIGIT;
        }
        for (int ch = 'A'; ch <= 'F'; ch++) {
            FLAGS[ch] |= IS_HEXDIGIT;
        }
        for (int ch = 'a'; ch <= 'f'; ch++) {
            FLAGS[ch] |= IS_HEXDIGIT;
        }
        for (int ch = 'A'; ch <= 'Z'; ch++) {
            FLAGS[ch] |= IS_ALPHA;
        }
        for (int ch = 'a'; ch <= 'z'; ch++) {
            FLAGS[ch] |= IS_ALPHA;
        }
    }


    private final String expressionString;

    private final char[] charsToProcess;

    private int pos = 0;

    private int max;

    private List<Token> tokens = new ArrayList<Token>();

    public Tokenizer(String expressionString) {
        if (StringUtils.isBlank(expressionString)) {
            throw new IllegalArgumentException("expressionString can not blank!");
        }
        this.expressionString = expressionString;
        this.charsToProcess = expressionString.replaceAll("\\s+", "").toCharArray();
        this.max = charsToProcess.length;
    }

    public synchronized List<Token> process() {
        while (this.pos < this.max) {
            char ch = this.charsToProcess[this.pos];
            if (isIdentifier(ch)) {
                lexIdentifier();
            } else {
                switch (ch) {
                    case '+':
                        pushCharToken(TokenKind.PLUS);
                        break;
                    case '-':
                        pushCharToken(TokenKind.MINUS);
                        break;
                    case '*':
                        pushCharToken(TokenKind.STAR);
                        break;
                    case '/':
                        pushCharToken(TokenKind.DIV);
                        break;
                    case '(':
                        pushCharToken(TokenKind.LPAREN);
                        break;
                    case ')':
                        pushCharToken(TokenKind.RPAREN);
                        break;
                    case '>':
                        if (isTwoCharToken(TokenKind.GE)) {
                            pushTwoCharToken(TokenKind.GE);
                        } else {
                            pushCharToken(TokenKind.GT);
                        }
                        break;
                    case '<':
                        if (isTwoCharToken(TokenKind.LE)) {
                            pushTwoCharToken(TokenKind.LE);
                        } else {
                            pushCharToken(TokenKind.LT);
                        }
                        break;
                    case '=':
                        if (isTwoCharToken(TokenKind.EQ)) {
                            pushTwoCharToken(TokenKind.EQ);
                        } else {
                            throw new IllegalArgumentException("token parse error!");
                        }
                        break;
                    case '!':
                        if (isTwoCharToken(TokenKind.NE)) {
                            pushTwoCharToken(TokenKind.NE);
                        } else {
                            pushCharToken(TokenKind.NOT);
                        }
                        break;
                    case '&':
                        if (isTwoCharToken(TokenKind.SYMBOLIC_AND)) {
                            pushTwoCharToken(TokenKind.SYMBOLIC_AND);
                        } else {
                            throw new IllegalArgumentException("token parse error!");
                        }
                        break;
                    case '|':
                        if (isTwoCharToken(TokenKind.SYMBOLIC_OR)) {
                            pushTwoCharToken(TokenKind.SYMBOLIC_OR);
                        } else {
                            pushCharToken(TokenKind.LT);
                        }
                        break;
                    default: {
                        throw new IllegalArgumentException("token parse error!");
                    }
                }
            }
        }
        return this.tokens;
    }

    private void lexIdentifier() {
        int start = this.pos;
        do {
            this.pos++;
        }
        while (this.pos < this.max && isIdentifier(this.charsToProcess[this.pos]));
        char[] subarray = subarray(start, this.pos);

        // Check if this is the alternative (textual) representation of an operator (see
        // alternativeOperatorNames)
//        if ((this.pos - start) == 2 || (this.pos - start) == 3) {
//            String asString = new String(subarray).toUpperCase();
//            int idx = Arrays.binarySearch(ALTERNATIVE_OPERATOR_NAMES, asString);
//            if (idx >= 0) {
//                this.tokens.add(new Token(TokenKind.valueOf(asString), subarray));
//                return;
//            }
//        }
        this.tokens.add(new Token(TokenKind.IDENTIFIER, subarray));
    }

    private void pushCharToken(TokenKind kind) {
        this.pos++;
        this.tokens.add(new Token(kind, kind.tokenChars));
    }

    private void pushTwoCharToken(TokenKind kind) {
        this.pos++;
        this.pos++;
        this.tokens.add(new Token(kind, kind.tokenChars));
    }

    private boolean isTwoCharToken(TokenKind kind) {
        if (this.pos + 1 >= this.max) {
            return false;
        }
        return kind.tokenChars.length == 2
                && this.charsToProcess[this.pos] == kind.tokenChars[0]
                && this.charsToProcess[this.pos + 1] == kind.tokenChars[1];

    }

    private char[] subarray(int start, int end) {
        return Arrays.copyOfRange(this.charsToProcess, start, end);
    }

    private boolean isDigit(char ch) {
        if (ch > 255) {
            return false;
        }
        return (FLAGS[ch] & IS_DIGIT) != 0;
    }

    private boolean isAlphabetic(char ch) {
        if (ch > 255) {
            return false;
        }
        return (FLAGS[ch] & IS_ALPHA) != 0;
    }

    // ID: ('a'..'z'|'A'..'Z'|'_'|'$') ('a'..'z'|'A'..'Z'|'_'|'$'|'0'..'9'|DOT_ESCAPED)*;
    private boolean isIdentifier(char ch) {
        return isAlphabetic(ch) || isDigit(ch) || ch == ',' || ch == '[' || ch == ']' || ch == '@';
    }
}
