package net.lovenn.sendpay.parse;

public class Expression {

    private Operand leftOperand;

    private Operand rightOperand;

    private Operator operator;

    public Operand execute() {
        return new Operand();
    }
}
