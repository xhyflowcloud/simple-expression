package net.lovenn.sendpay.expression;

import net.lovenn.sendpay.expression.ast.*;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InternalSelExpressionParser {

    // The expression being parsed
    private String expressionString = "";

    private final Stack<TokenKind> parens = new Stack<TokenKind>();

    // The token stream constructed from that expression string
    private List<Token> tokenStream = Collections.emptyList();

    // length of a populated token stream
    private int tokenStreamLength;

    // Current location in the token stream when processing tokens
    private int tokenStreamPointer;

    private ThreadLocal<ParserContext> context = new ThreadLocal<ParserContext>();

    public SelExpression doParseExpression(String expressionString, ParserContext context) throws ParseException {

        try {
            Tokenizer tokenizer = new Tokenizer(expressionString);
            this.tokenStream = tokenizer.process();
            this.tokenStreamLength = this.tokenStream.size();
            this.context.set(context);
        } catch (Exception e) {
            //log
            throw new ParseException(e.getMessage());
        } finally {
            this.context.remove();
        }

        return null;
    }

    private Node eatExpression() {
        Node node = eatLogicalOrExpression();
        return node;
    }

    private Node eatLogicalOrExpression() {
        Node lexpr = eatLogicalAndExpression();
        while (peekToken(TokenKind.SYMBOLIC_OR)) {
            Token token = takeToken();
            Node rexpr = eatLogicalAndExpression();
            checkOperands(token, lexpr, rexpr);
            lexpr = new OpOr(lexpr, rexpr);
        }
        return lexpr;
    }

    private Node eatLogicalAndExpression() {
        Node lexpr = eatRelationalExpression();
        while (peekToken(TokenKind.SYMBOLIC_AND)) {
            Token token = takeToken();
            Node rexpr = eatRelationalExpression();
            checkOperands(token, lexpr, rexpr);
            lexpr = new OpOr(lexpr, rexpr);
        }
        return lexpr;
    }

    private Node eatRelationalExpression() {
        Node lexpr = eatSumExpression();
        while (peekToken(TokenKind.GT, TokenKind.GE, TokenKind.LT, TokenKind.LE, TokenKind.EQ, TokenKind.NE)) {
            Token token = takeToken();
            Node rexpr = eatSumExpression();
            checkOperands(token, lexpr, rexpr);
            if(token.kind == TokenKind.GT) {
                lexpr = new OpGT(lexpr, rexpr);
            }
            if(token.kind == TokenKind.GE) {
                lexpr = new OpGE(lexpr, rexpr);
            }
            if(token.kind == TokenKind.LT) {
                lexpr = new OpLT(lexpr, rexpr);
            }
            if(token.kind == TokenKind.LE) {
                lexpr = new OpLE(lexpr, rexpr);
            }
            if(token.kind == TokenKind.EQ) {
                lexpr = new OpEQ(lexpr, rexpr);
            }
            if(token.kind == TokenKind.NE) {
                lexpr = new OpNE(lexpr, rexpr);
            }
        }
        return lexpr;
    }

    private Node eatSumExpression() {
        Node lexpr = eatProductExpression();
        while (peekToken(TokenKind.PLUS, TokenKind.MINUS)) {
            Token token = takeToken();
            Node rexpr = eatProductExpression();
            checkOperands(token, lexpr, rexpr);
            if(token.kind == TokenKind.PLUS) {
                lexpr = new OpPlus(lexpr, rexpr);
            }
            if(token.kind == TokenKind.MINUS) {
                lexpr = new OpMinus(lexpr, rexpr);
            }
        }
        return lexpr;

    }

    private Node eatProductExpression() {
        Node lexpr = eatUnaryExpression();
        while (peekToken(TokenKind.STAR, TokenKind.DIV)) {
            Token token = takeToken();
            Node rexpr = eatUnaryExpression();
            checkOperands(token, lexpr, rexpr);
            if(token.kind == TokenKind.STAR) {
                lexpr = new OpMultiply(lexpr, rexpr);
            }
            if(token.kind == TokenKind.DIV) {
                lexpr = new OpDivide(lexpr, rexpr);
            }
        }
        return lexpr;
    }

    private Node eatUnaryExpression() {
        if(peekToken(TokenKind.NOT)) {
            Token t = takeToken();
            Node expr = eatUnaryExpression();
            checkOperand(t, expr);
            return new OpNot(expr);
        }
        return eatPrimaryExpression();
    }

    private Node eatPrimaryExpression() {
        Token token = takeToken();
        if(token.kind == TokenKind.LPAREN) {
            parens.push(TokenKind.LPAREN);
            return eatPrimaryExpression();
        }
        if(token.kind == TokenKind.RPAREN) {
            if(parens.empty()) {
                throw new IllegalStateException("express parse error");
            }
            parens.pop();
            return null;
        }
        return createNode(token);
    }

    private boolean peekToken(TokenKind... kinds) {
        Token token = peekToken();
        if(token == null) {
            return false;
        }
        for (TokenKind kind: kinds) {
            if(token.kind == kind) {
                return true;
            }
        }
        return false;
    }

    private Token peekToken() {
        if (this.tokenStreamPointer >= this.tokenStreamLength) {
            return null;
        }
        return this.tokenStream.get(this.tokenStreamPointer);
    }

    private Token takeToken() {
        if (this.tokenStreamPointer >= this.tokenStreamLength) {
            throw new IllegalStateException("No token");
        }
        return this.tokenStream.get(this.tokenStreamPointer++);
    }

    private Token nextToken() {
        if (this.tokenStreamPointer >= this.tokenStreamLength) {
            return null;
        }
        return this.tokenStream.get(this.tokenStreamPointer++);
    }

    private void checkOperands(Token token, Node left, Node right) {
        checkOperand(token, left);
        checkOperand(token, right);
    }

    private void checkOperand(Token token, Node node) {
        if(node == null) {
            throw new IllegalStateException("operand can not null!");
        }
    }

    private Node createNode(Token token) {
        Node node;
        if((node = variableNode(token)) != null) {
            return node;
        }
        return new Operand(token.kind, token.data);
    }

    private Node variableNode(Token token) {
        Matcher matcher;
        while ((matcher = VAR_REGEX.matcher(token.data)).find()) {
            if(matcher.groupCount() == 1) {
                String value = context.get().findValue(matcher.group(1));
                return new Operand(TokenKind.IDENTIFIER, value);
            }
            if(matcher.groupCount() == 4) {
                int start = Integer.parseInt(matcher.group(3));
                int end = Integer.parseInt(matcher.group(4));
                String value = context.get().findValue(matcher.group(1));
                if(value.length() >= end) {
                    return new Operand(token.kind, value.substring(start, end));
                } else {
                    return new Operand(token.kind, null);
                }
            }
            throw new IllegalStateException("express parse error!");
        }
        return null;
    }

    private static final Pattern VAR_REGEX = Pattern.compile("^@([0-9a-zA-Z]+)(\\[(\\d+),(\\d+)])?$");
}
