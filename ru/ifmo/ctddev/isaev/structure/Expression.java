package ru.ifmo.ctddev.isaev.structure;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 05.11.13
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public interface Expression {
    boolean match(Expression other);
    boolean matchAxiomScheme(Expression expr, HashMap<Integer,Expression> known);
    Expression substitute(HashMap<String, Expression> variables);
    boolean hasSameType(Expression other);
    StringBuilder asString();
}