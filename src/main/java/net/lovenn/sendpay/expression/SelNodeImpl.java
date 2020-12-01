package net.lovenn.sendpay.expression;

import net.lovenn.sendpay.expression.ast.Operand;

public class SelNodeImpl implements SelNode {

    private TokenKind kind;

    private SelNode parent;

    private SelNode[] children;

    public SelNodeImpl(TokenKind kind, SelNodeImpl... children) {
        this.kind = kind;
        for (SelNodeImpl selNodeImpl : children) {
            selNodeImpl.parent = this;
        }
        this.children = children;
    }

    protected SelNode[] getChildren() {
        return this.children;
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        return null;
    }

    @Override
    public TokenKind tokenKind() {
        return this.kind;
    }

    @Override
    public SelNode parent() {
        return this.parent;
    }
}
