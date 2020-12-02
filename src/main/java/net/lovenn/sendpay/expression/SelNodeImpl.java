package net.lovenn.sendpay.expression;

public class SelNodeImpl implements SelNode {

    private TokenKind kind;

    private SelNode parent;

    private SelNode[] children;

    public SelNodeImpl(TokenKind kind, SelNode... children) {
        this.setTokenKind(kind);
        for (SelNode selNode : children) {
            selNode.setParent(this);
        }
        this.setChildren(children);
    }

    @Override
    public Object getValue(SelExecuteContext context) throws SelExecuteException {
        return null;
    }

    @Override
    public void setTokenKind(TokenKind tokenKind) {
        this.kind = tokenKind;
    }

    @Override
    public TokenKind getTokenKind() {
        return this.kind;
    }

    @Override
    public void setParent(SelNode node) {
        this.parent = node;
    }

    @Override
    public SelNode getParent() {
        return this.parent;
    }

    @Override
    public void setChildren(SelNode... children) {
        this.children = children;
    }

    @Override
    public SelNode[] getChildren() {
        return this.children;
    }
}
