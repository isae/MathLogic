package ru.ifmo.ctddev.isaev;

import ru.ifmo.ctddev.isaev.hardcodedRules.AThenA;
import ru.ifmo.ctddev.isaev.parser.ArithmeticParser;
import ru.ifmo.ctddev.isaev.parser.Lexer;
import ru.ifmo.ctddev.isaev.parser.LogicParser;
import ru.ifmo.ctddev.isaev.parser.Parser;
import ru.ifmo.ctddev.isaev.structure.Expression;
import ru.ifmo.ctddev.isaev.structure.logic.Then;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Xottab
 * Date: 30.11.13
 */
public class General {
    public static BufferedReader in;
    public static PrintWriter out;
    protected static Lexer lexer = new Lexer();
    private static Parser parser = new LogicParser();
    private static int currentMode;

    public static void setParser(Parser parser) {
        General.parser = parser;
    }

    public static Expression parse(String s) {
        Expression expression = null;
        try {
            expression = parseExcept(s);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("string " + s + " looks kinda crappy, cant parse this");
            System.exit(1);
        }
        return expression;
    }

    public static Expression parseExcept(String s) throws Exception {
        Expression expression;
        String[] lexems = lexer.lex(s);
        parser.s = s;
        if (parser instanceof ArithmeticParser) {
            expression = parser.parse(s);
        } else {
            expression = parser.parse(lexems);
        }/*
        if (parser instanceof PredicateParser) {
            expression.setQuantifiers(new HashSet<>());
        }*/

        return expression;
    }

    public static List<Expression> proofAThenA(Expression alpha) {
        List<Expression> result = new ArrayList<>();
        for (AThenA e : AThenA.values()) {
            result.add(e.replace(alpha));
        }
        return result;
    }

    public static void addToMps(Map<Expression, ArrayList<Expression>> mps, Expression e) {
        if (e instanceof Then) {
            if (!mps.containsKey(((Then) e).getRight())) {
                mps.put(((Then) e).getRight(), new ArrayList<Expression>(3));
            }
            List<Expression> exprs = mps.get(((Then) e).getRight());
            exprs.add(((Then) e).getLeft());
        }
    }

    public static boolean isLowercaseVariable(String temp) {
        return Character.isLowerCase(temp.charAt(0)) && looksLikeSomething(temp);
    }

    private static boolean looksLikeSomething(String temp) {
        if (temp.length() > 1) {
            try {
                Integer.parseInt(temp.substring(1, temp.length()));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUppercaseVariable(String temp) {
        return Character.isUpperCase(temp.charAt(0)) && looksLikeSomething(temp);
    }

    public static boolean isVariable(String temp) {
        return Character.isLetter(temp.charAt(0)) && looksLikeSomething(temp);
    }

    public static void exitWithMessage(String s) {
        out.println(s);
        out.close();
        System.exit(1);
    }
}
