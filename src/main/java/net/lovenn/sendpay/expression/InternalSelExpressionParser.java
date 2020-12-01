package net.lovenn.sendpay.expression;

import net.lovenn.sendpay.expression.ast.*;

import java.util.Collections;
import java.util.List;

public class InternalSelExpressionParser {

    // The token stream constructed from that expression string
    private List<Token> tokenStream = Collections.emptyList();

    // length of a populated token stream
    private int tokenStreamLength;

    // Current location in the token stream when processing tokens
    private int tokenStreamPointer;

    private ThreadLocal<SelParserContext> context = new ThreadLocal<SelParserContext>();

    public SelSelExpressionImpl doParseExpression(String expressionString, SelParserContext context) throws SelParseException {

        try {
            Tokenizer tokenizer = new Tokenizer(expressionString);
            this.tokenStream = tokenizer.process();
            this.tokenStreamLength = this.tokenStream.size();
            this.context.set(context);
            SelNodeImpl expressSelNodeImpl = this.eatExpression();
            Token t = this.peekToken();
            if (t != null) {
                throw new SelParseException("express parse error!");
            }
            return new SelSelExpressionImpl(expressionString, expressSelNodeImpl);
        } catch (Exception e) {
            //log
            throw new SelParseException(e.getMessage());
        } finally {
            this.context.remove();
        }
    }

    private SelNodeImpl eatExpression() {
        SelNodeImpl selNodeImpl = eatLogicalExpression();
        return selNodeImpl;
    }

    private SelNodeImpl eatLogicalExpression() {
        SelNodeImpl lrexpr = eatRelationalExpression();
        while (isPeekToken(TokenKind.SYMBOLIC_AND, TokenKind.SYMBOLIC_OR)) {
            Token token = takeToken();
            SelNodeImpl rrexpr = eatRelationalExpression();
            checkOperands(token, lrexpr, rrexpr);
            if (token.kind == TokenKind.SYMBOLIC_AND) {
                lrexpr = new OpAnd(lrexpr, rrexpr);
            } else if (token.kind == TokenKind.SYMBOLIC_OR) {
                lrexpr = new OpOr(lrexpr, rrexpr);
            }
        }
        return lrexpr;
    }

    private SelNodeImpl eatRelationalExpression() {
        SelNodeImpl lsexpr = eatSumExpression();
        while (isPeekToken(TokenKind.GT, TokenKind.GE, TokenKind.LT, TokenKind.LE, TokenKind.EQ, TokenKind.NE)) {
            Token token = takeToken();
            SelNodeImpl rsexpr = eatSumExpression();
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

    private SelNodeImpl eatSumExpression() {
        SelNodeImpl lpexpr = eatProductExpression();
        while (isPeekToken(TokenKind.PLUS, TokenKind.MINUS)) {
            Token token = takeToken();
            SelNodeImpl rpexpr = eatProductExpression();
            checkOperands(token, lpexpr, rpexpr);
            if (token.kind == TokenKind.PLUS) {
                lpexpr = new OpPlus(lpexpr, rpexpr);
            } else if (token.kind == TokenKind.MINUS) {
                lpexpr = new OpMinus(lpexpr, rpexpr);
            }
        }
        return lpexpr;
    }

    private SelNodeImpl eatProductExpression() {
        SelNodeImpl luexpr = eatUnaryExpression();
        while (isPeekToken(TokenKind.STAR, TokenKind.DIV)) {
            Token token = takeToken();
            SelNodeImpl ruexpr = eatUnaryExpression();
            checkOperands(token, luexpr, ruexpr);
            if (token.kind == TokenKind.STAR) {
                luexpr = new OpMultiply(luexpr, ruexpr);
            } else if (token.kind == TokenKind.DIV) {
                luexpr = new OpDivide(luexpr, ruexpr);
            }
        }
        return luexpr;
    }

    private SelNodeImpl eatUnaryExpression() {
        if (isPeekToken(TokenKind.NOT)) {
            Token t = takeToken();
            SelNodeImpl expr = eatUnaryExpression();
            checkOperand(t, expr);
            return new OpNot(expr);
        }
        return eatPrimaryExpression();
    }

    private SelNodeImpl eatPrimaryExpression() {
        Token token = takeToken();
        if (token.kind == TokenKind.LPAREN) {
            SelNodeImpl cexpr = eatExpression();
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

    private void checkOperands(Token token, SelNodeImpl left, SelNodeImpl right) {
        checkOperand(token, left);
        checkOperand(token, right);
    }

    private void checkOperand(Token token, SelNodeImpl selNodeImpl) {
        if (selNodeImpl == null) {
            throw new IllegalStateException("operand can not null!");
        }
    }
}
