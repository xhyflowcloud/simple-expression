package net.lovenn.sendpay.expression;

public interface SelNode {

    /**
     * 获取值
     */
    Object getValue(SelExecuteContext context) throws SelExecuteException;

    /**
     * 获取token类型
     */
    TokenKind tokenKind();

    /**
     * 获取父节点
     */
    SelNode parent();
}
