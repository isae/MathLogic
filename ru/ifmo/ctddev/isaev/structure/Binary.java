package ru.ifmo.ctddev.isaev.structure;

import ru.ifmo.ctddev.isaev.exception.ProofGeneratingException;
import ru.ifmo.ctddev.isaev.parser.Lexeme;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 05.11.13
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public abstract class Binary extends AbstractExpression {
    public Expression left;
    public Expression right;
    public Lexeme token;

    protected Binary(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean matchAxiomScheme(Expression expr, HashMap<Integer, Expression> known) {
        return hasSameType(expr)
                && left.matchAxiomScheme(((Binary) expr).left, known)
                && right.matchAxiomScheme(((Binary) expr).right, known);
    }

    @Override
    public StringBuilder asString() {
        return left.asString().append(token.token).append(right.asString()).append(Lexeme.RIGHT_P.token).insert(0, Lexeme.LEFT_P.token);
    }

    @Override
    public HashMap<String, Variable> getVars() {
        HashMap<String, Variable> h = left.getVars();
        h.putAll(right.getVars());
        return h;
    }

    @Override
    public StringBuilder asJavaExpr() {
        return new StringBuilder("new ").append(getClass().getSimpleName()).append("(").append(left.asJavaExpr()).append(",").append(right.asJavaExpr()).append(")");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Binary)) return false;

        Binary that = (Binary) o;

        if (!left.equals(that.left)) return false;
        if (!right.equals(that.right)) return false;
        if (token != that.token) return false;

        return true;
    }

    @Override
    public List<Expression> getParticularProof(List<? extends Expression> hypos) throws ProofGeneratingException {
        List<Expression> result = left.getParticularProof(hypos);
        result.addAll(right.getParticularProof(hypos));
        return result;
    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        result = 31 * result + token.hashCode();
        return result;
    }
}
