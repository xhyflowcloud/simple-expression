package net.lovenn.sendpay.expression;

public class Node {

    private TokenKind kind;

    private Node parent;

    private Node[] children;

    public Node(TokenKind kind, Node... children) {
        this.kind = kind;
        for (Node node : children) {
            node.parent = this;
        }
        this.children = children;
    }
}
