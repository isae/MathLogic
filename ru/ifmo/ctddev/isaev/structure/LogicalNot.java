package ru.ifmo.ctddev.isaev.structure;

import ru.ifmo.ctddev.isaev.parser.Lexeme;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 05.11.13
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class LogicalNot extends LogicalUnary {
    public LogicalNot(Expression operand) {
        super(operand);
        this.token = Lexeme.NOT;
    }

    @Override
    public boolean match(Expression other) {
        return hasSameType(other)
                && ((LogicalNot) other).operand.match(operand);
    }

    @Override
    public boolean hasSameType(Expression other) {
        return other instanceof LogicalNot;
    }
    @Override
    public Expression substitute(HashMap<String, Expression> variables) {
        return new LogicalNot(operand.substitute(variables));
    }
}