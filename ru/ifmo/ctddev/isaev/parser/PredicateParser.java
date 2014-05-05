package ru.ifmo.ctddev.isaev.parser;

import ru.ifmo.ctddev.isaev.exception.ParsingException;
import ru.ifmo.ctddev.isaev.structure.Expression;
import ru.ifmo.ctddev.isaev.structure.logic.Not;
import ru.ifmo.ctddev.isaev.structure.logic.Variable;
import ru.ifmo.ctddev.isaev.structure.predicate.Exists;
import ru.ifmo.ctddev.isaev.structure.predicate.ForAll;
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
public class PredicateParser extends LogicParser {

    protected int lookBack;

    @Override
    protected Expression unary() throws ParsingException {
        Expression result;
        if (tokens[position].equals(Lexeme.NOT.s)) {
            position++;
            result = new Not(unary());
            return result;
        }
        if (tokens[position].equals(Lexeme.FOR_ALL.s)) {
            position++;
            Term var = null;
            if (isLowercaseVariable(tokens[position])) {
                var = term();
            } else {
                throw new ParsingException("something wrong, you tried to parse " + tokens[position] + " as variable");
            }
            result = new ForAll(var, unary());
            return result;
        }
        if (tokens[position].equals(Lexeme.EXISTS.s)) {
            position++;
            Term var;
            if (isLowercaseVariable(tokens[position])) {
                var = term();
            } else {
                throw new ParsingException("something wrong, you tried to parse " + tokens[position] + " as variable");
            }
            result = new Exists(var, unary());
            return result;
        }
        if (tokens[position].equals(Lexeme.LEFT_P.s)) {
            try {
                lookBack = position;
                Predicate p = predicate();
                return p;
            } catch (ParsingException e) {
                position = lookBack;
                position++;
                result = expr();
                if (!tokens[position].equals(Lexeme.RIGHT_P.s)) {
                    StringBuilder sb = new StringBuilder();
                    for (String s : tokens) {
                        sb.append(s);
                    }
                    throw new ParsingException("you have unclosed brackets in expression " + sb.toString());
                } else {
                    position++;
                }
                return result;
            }
        }
        return predicate();
    }

    protected Predicate predicate() throws ParsingException {
        if (isUppercaseVariable(tokens[position])) {
            Predicate result;
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
            } else {
                result = new Variable(tokens[position - 1]);
            }
            return result;
        } else throw new ParsingException("you tried to parse predicate but first symbol is not uppercase latin");
    }

    protected Term term() throws ParsingException {
        Term result;
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
        } else
            throw new ParsingException("cannot parse term without name, incorrect invocation");

        if (!f) position++;
        return result;
    }

}
