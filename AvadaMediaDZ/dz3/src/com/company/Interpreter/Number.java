package com.company.Interpreter;

public class Number implements Expression {

    int number;

    public Number(int number) {
        this.number = number;
    }

    @Override
    public int interpreter(Expression context) {
        return number;
    }

}