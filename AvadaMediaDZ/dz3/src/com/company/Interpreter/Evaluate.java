package com.company.Interpreter;

import java.util.Stack;

public class Evaluate implements Expression {

    Expression expression;

    public Evaluate(String s) {
        Stack<Expression> exceptions = new Stack<>();
        String s1 = new StringBuilder(s).reverse().toString();
        for (String s2 : s1.split("\\D"))
            exceptions.push(new Number(Integer.parseInt(s2)));
        for (String s2 : s.split("\\d")) {
            if (s2.equals("+"))
                exceptions.push(new Plus(exceptions.pop(), exceptions.pop()));
        }
        expression = exceptions.pop();
    }

    @Override
    public int interpreter(Expression context) {
        return expression.interpreter(context);
    }
}
