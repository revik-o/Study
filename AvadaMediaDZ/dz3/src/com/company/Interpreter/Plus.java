package com.company.Interpreter;

public class Plus implements Expression {

    Expression left;
    Expression right;

    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpreter(Expression context) {
        return left.interpreter(context) + right.interpreter(context);
    }

}
