package ru.ifmo.ctddev.isaev.parser;

import ru.ifmo.ctddev.isaev.exception.ParsingException;
import ru.ifmo.ctddev.isaev.structure.arithmetics.*;
import ru.ifmo.ctddev.isaev.structure.predicate.Predicate;
import ru.ifmo.ctddev.isaev.structure.predicate.Term;

import java.util.ArrayList;
import java.util.List;

import static ru.ifmo.ctddev.isaev.General.isLowercaseVariable;
import static ru.ifmo.ctddev.isaev.General.isUppercaseVariable;

/**
 * User: Xottab
 * Date: 22.01.14
 */
public class ArithmeticParser extends PredicateParser {


    @Override
    protected Predicate predicate() throws ParsingException {
        Predicate result;
        if (isUppercaseVariable(tokens[position])) {
            result = new Predicate(tokens[position]);
            position++;
            if (position < tokens.length && tokens[position].equals(Lexeme.LEFT_P.s)) {
                position++;
                List<Term> arguments = new ArrayList<>(3);
                arguments.add(term());
                while (tokens[position].equals(Lexeme.COMMA.s)) {
                    position++;
                    arguments.add(term());
                }
                position++;
                result.setArguments(arguments.toArray(new Term[arguments.size()]));
            }
            return result;
        } else {
            Term term = term();
            if (tokens[position].equals(Lexeme.EQ.s)) {
                position++;
                Term term2 = term();
                return new Equals(term, term2);
            }
        }
        throw new ParsingException("unexpected symbol");
    }

    @Override
    protected Term term() throws ParsingException {
        Term term = sum();
        if (position < tokens.length && tokens[position].equals(Lexeme.PLUS.s)) {
            position++;
            term = new Plus(term, term());
        }
        while (tokens[position].equals(Lexeme.COMMA.s)) {
            term = new Prime(term);
            position++;
        }
        return term;
    }

    protected Term sum() throws ParsingException {
        Term term = mul();
        if (position < tokens.length && tokens[position].equals(Lexeme.MUL.s)) {
            position++;
            return new Mul(term, sum());
        }
        return term;
    }

    protected Term mul() throws ParsingException {
        Term result = null;
        boolean f = false;
        if (tokens[position].equals(Lexeme.LEFT_P.s)) {
            position++;
            result = term();
        } else if (isLowercaseVariable(tokens[position])) {
            result = new Term(tokens[position]);
            position++;
            f = true;
            if (position < tokens.length && tokens[position].equals(Lexeme.LEFT_P.s)) {
                position++;
                List<Term> arguments = new ArrayList<>(3);
                arguments.add(term());
                while (tokens[position].equals(Lexeme.COMMA.s)) {
                    position++;
                    arguments.add(term());
                }
                result.setArguments(arguments.toArray(new Term[arguments.size()]));
                position++;
            }
        } else if (tokens[position].equals(Lexeme.ZERO.s)) {

            f = false;
            result = new Zero();
        }

        if (!f) position++;

        if (result == null) {
            throw new ParsingException("cannot parse dat crap");
        }
        return result;


    }
}
