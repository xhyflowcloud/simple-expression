package net.lovenn.sendpay.expression;

public interface SelNode {

    /**
     * 获取值
     */
    Object getValue(SelExecuteContext context) throws SelExecuteException;

    /**
     * 设置tokenKind
     */
    void setTokenKind(TokenKind tokenKind);

    /**
     * 获取token类型
     */
    TokenKind getTokenKind();

    /**
     * 设置父节点
     */
    void setParent(SelNode parent);

    /**
     * 获取父节点
     */
    SelNode getParent();

    /**
     * 设置孩子节点
     */
    void setChildren(SelNode... children);

    /**
     * 获取孩子节点
     */
    SelNode[] getChildren();
}
