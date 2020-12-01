package net.lovenn.sendpay.expression;

public class SelNode {

    private TokenKind kind;

    private SelNode parent;

    private SelNode[] children;

    public SelNode(TokenKind kind, SelNode... children) {
        this.kind = kind;
        for (SelNode selNode : children) {
            selNode.parent = this;
        }
        this.children = children;
    }

    public Object getValue() {
        return new Object();
    }
}
