package net.lovenn.sendpay.expression;

import net.lovenn.sendpay.expression.ast.*;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
            SelNode expressSelNode = this.eatExpression();
            Token t = this.peekToken();
            if (t != null) {
                throw new ParseException("express parse error!");
            }
            return new SelExpression(this.expressionString, expressSelNode, context);
        } catch (Exception e) {
            //log
            throw new ParseException(e.getMessage());
        } finally {
            this.context.remove();
        }
    }

    private SelNode eatExpression() {
        SelNode selNode = eatLogicalExpression();
        return selNode;
    }

    private SelNode eatLogicalExpression() {
        SelNode lrexpr = eatRelationalExpression();
        while (isPeekToken(TokenKind.SYMBOLIC_AND, TokenKind.SYMBOLIC_OR)) {
            Token token = takeToken();
            SelNode rrexpr = eatRelationalExpression();
            checkOperands(token, lrexpr, rrexpr);
            if (token.kind == TokenKind.SYMBOLIC_AND) {
                lrexpr = new OpOr(lrexpr, rrexpr);
            } else if (token.kind == TokenKind.SYMBOLIC_OR) {
                lrexpr = new OpAnd(lrexpr, rrexpr);
            }
        }
        return lrexpr;
    }

    private SelNode eatRelationalExpression() {
        SelNode lsexpr = eatSumExpression();
        while (isPeekToken(TokenKind.GT, TokenKind.GE, TokenKind.LT, TokenKind.LE, TokenKind.EQ, TokenKind.NE)) {
            Token token = takeToken();
            SelNode rsexpr = eatSumExpression();
            checkOperands(token, lsexpr, rsexpr);
            if (token.kind == TokenKind.GT) {
                lsexpr = new OpGT(lsexpr, rsexpr);
            } else if (token.kind == TokenKind.GE) {
                lsexpr = new OpGE(lsexpr, rsexpr);
            } else if (token.kind == TokenKind.LT) {
                lsexpr = new OpLT(lsexpr, rsexpr);
            } else if (token.kind == TokenKind.LE) {
                lsexpr = new OpLE(lsexpr, rsexpr);
            } else if (token.kind == TokenKind.EQ) {
                lsexpr = new OpEQ(lsexpr, rsexpr);
            } else if (token.kind == TokenKind.NE) {
                lsexpr = new OpNE(lsexpr, rsexpr);
            }
        }
        return lsexpr;
    }

    private SelNode eatSumExpression() {
        SelNode lpexpr = eatProductExpression();
        while (isPeekToken(TokenKind.PLUS, TokenKind.MINUS)) {
            Token token = takeToken();
            SelNode rpexpr = eatProductExpression();
            checkOperands(token, lpexpr, rpexpr);
            if (token.kind == TokenKind.PLUS) {
                lpexpr = new OpPlus(lpexpr, rpexpr);
            } else if (token.kind == TokenKind.MINUS) {
                lpexpr = new OpMinus(lpexpr, rpexpr);
            }
        }
        return lpexpr;
    }

    private SelNode eatProductExpression() {
        SelNode luexpr = eatUnaryExpression();
        while (isPeekToken(TokenKind.STAR, TokenKind.DIV)) {
            Token token = takeToken();
            SelNode ruexpr = eatUnaryExpression();
            checkOperands(token, luexpr, ruexpr);
            if (token.kind == TokenKind.STAR) {
                luexpr = new OpMultiply(luexpr, ruexpr);
            } else if (token.kind == TokenKind.DIV) {
                luexpr = new OpDivide(luexpr, ruexpr);
            }
        }
        return luexpr;
    }

    private SelNode eatUnaryExpression() {
        if (isPeekToken(TokenKind.NOT)) {
            Token t = takeToken();
            SelNode expr = eatUnaryExpression();
            checkOperand(t, expr);
            return new OpNot(expr);
        }
        return eatPrimaryExpression();
    }

    private SelNode eatPrimaryExpression() {
        Token token = takeToken();
        if (token.kind == TokenKind.LPAREN) {
            parens.push(TokenKind.LPAREN);
            SelNode cexpr = eatExpression();
            Token rpToken = takeToken();
            if (rpToken.kind != TokenKind.RPAREN) {
                throw new IllegalStateException("express parse error!");
            }
            return cexpr;
        }
        return new Operand(token.kind, token.data);
    }

    private boolean isPeekToken(TokenKind... kinds) {
        Token token = peekToken();
        if (token == null) {
            return false;
        }
        for (TokenKind kind : kinds) {
            if (token.kind == kind) {
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

    private void checkOperands(Token token, SelNode left, SelNode right) {
        checkOperand(token, left);
        checkOperand(token, right);
    }

    private void checkOperand(Token token, SelNode selNode) {
        if (selNode == null) {
            throw new IllegalStateException("operand can not null!");
        }
    }
}
