package ru.ifmo.ctddev.isaev;

import ru.ifmo.ctddev.isaev.exception.IncorrectProofException;
import ru.ifmo.ctddev.isaev.exception.LexingException;
import ru.ifmo.ctddev.isaev.exception.ParsingException;
import ru.ifmo.ctddev.isaev.hardcodedRules.AxiomScheme;
import ru.ifmo.ctddev.isaev.structure.Expression;
import ru.ifmo.ctddev.isaev.structure.logic.NumExpr;
import ru.ifmo.ctddev.isaev.structure.logic.Then;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static ru.ifmo.ctddev.isaev.General.*;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 10.11.13
 * Time: 17:39
 * To change this template use File | Settings | File Templates.
 */
public class Deduct2 extends Homework {
    public PrintWriter stat;
    private List<Expression> hypos = new ArrayList<>();
    private Map<String, Expression> proofed = new HashMap<>();
    private Expression alpha;
    private Map<String, Expression> map = new HashMap<>();
    private Map<Expression, ArrayList<Expression>> mps = new HashMap<>();

    public Deduct2() {
        try {
            stat = new PrintWriter("stat.out");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Deduct2(List<Expression> hypos, Expression alpha) {

        this.hypos = hypos;
        this.hypos.add(alpha);
    }

    public void setHypos(List<Expression> hypos) {
        this.hypos = new ArrayList<>(hypos);
        this.alpha = this.hypos.remove(hypos.size() - 1);
    }

    public void setProofed(List<Expression> proofed) {
        this.proofed = new HashMap<>();
        for (Expression e : proofed) {
            this.proofed.put(e.toString(), e);
        }
    }

    public List<Expression> move1HypoToProof(List<Expression> proof) throws IncorrectProofException {
        List<Expression> result = new ArrayList<>();
        mps.clear();
        for (Expression e : proofed.values()) {
            result.add(e);
            result.add(new Then(e, new Then(alpha, e)));
            Expression temp = new Then(alpha, e);
            result.add(temp);
            addToMps(mps, e);
            addToMps(mps, temp);
        }

        for (Expression expr : proof) {
            boolean f = false;
            for (Expression e : hypos) {
                if (e.treeEquals(expr)) {
                    f = true;
                    break;
                }
            }

            if (!f) {
                for (AxiomScheme scheme : AxiomScheme.values()) {
                    if (scheme.match(expr)) {
                        f = true;
                        break;
                    }
                }
            }
            if (f) {
                result.add(expr);
                result.add(new Then(expr, new Then(alpha, expr)));
                Expression temp = new Then(alpha, expr);
                result.add(temp);
            }
            if (!f && expr.treeEquals(alpha)) {
                result.addAll(proofAThenA(alpha));
                f = true;
            }
            if (proofed.containsKey(expr.toString())) {
                f = true;
            }
            if (!f) {
                if (mps.get(expr) != null) {
                    for (Expression e : mps.get(expr)) {
                        if (proofed.get(e.toString()) != null) {
                            map.put("1", alpha);
                            map.put("2", e);
                            map.put("3", expr);
                            result.add(
                                    new Then(
                                            new Then(
                                                    new NumExpr(1),
                                                    new NumExpr(2)),
                                            new Then(
                                                    new Then(
                                                            new NumExpr(1),
                                                            new Then(
                                                                    new NumExpr(2),
                                                                    new NumExpr(3))),
                                                    new Then(
                                                            new NumExpr(1),
                                                            new NumExpr(3)))).substitute(map));
                            result.add(
                                    new Then(
                                            new Then(
                                                    new NumExpr(1),
                                                    new Then(
                                                            new NumExpr(2),
                                                            new NumExpr(3))),
                                            new Then(
                                                    new NumExpr(1),
                                                    new NumExpr(3))).substitute(map));
                            result.add(new Then(alpha, expr));
                            f = true;
                            break;
                        }
                    }
                }
            }

            if (!f) {
                for (Expression e : proofed.values()) {
                    out.println(e.asString());
                }
                out.println("до этого всё было ок");
                out.println("Не получилось доказать: " + expr.asString());
                throw new IncorrectProofException(expr.toString());
            } else {
                proofed.put(expr.toString(), expr);
                addToMps(mps, expr);
                addToMps(mps, new Then(alpha, expr));
            }
        }

        stat.println();
        return result;
    }

    @Override
    public void doSomething() throws IOException, ParsingException, LexingException, IncorrectProofException {
        String[] temp = in.readLine().split(Pattern.quote("|-"));
        if (temp.length > 2) {
            throw new IOException("more than one |- in first line");
        }
        String[] s = temp[0].split(",");
        for (String value : s) {
            hypos.add(parse(value));
        }
        alpha = hypos.remove(hypos.size() - 1);
        List<Expression> proof = new ArrayList<>();
        String s1 = in.readLine();
        while (s1 != null && !s1.replace("\\s+", "").isEmpty()) {
            proof.add(parse(s1));
            s1 = in.readLine();
        }
        List<Expression> newProof = move1HypoToProof(proof);
        for (Expression e : newProof) {
            out.println(e.asString());
        }
    }

    public boolean haveZeroHypos() {
        return hypos.size() == 0;
    }
}
