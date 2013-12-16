package ru.ifmo.ctddev.isaev.helpers;

import ru.ifmo.ctddev.isaev.structure.Expression;
import ru.ifmo.ctddev.isaev.structure.LogicalNot;
import ru.ifmo.ctddev.isaev.structure.LogicalThen;
import ru.ifmo.ctddev.isaev.structure.Variable;

import static ru.ifmo.ctddev.isaev.General.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 18.11.13
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public enum InsaneHardcodedContrapositionRule {

    //O_o
    R_1("((A->B)->((A->!(B))->!(A)))"),
    R_2("(((A->B)->((A->!(B))->!(A)))->((A->B)->((A->B)->((A->!(B))->!(A)))))"),
    R_3("((A->B)->((A->B)->((A->!(B))->!(A))))"),
    R_4("(((A->B)->((A->!(B))->!(A)))->(!(B)->((A->B)->((A->!(B))->!(A)))))"),
    R_5("((((A->B)->((A->!(B))->!(A)))->(!(B)->((A->B)->((A->!(B))->!(A)))))->((A->B)->(((A->B)->((A->!(B))->!(A)))->(!(B)->((A->B)->((A->!(B))->!(A)))))))"),
    R_6("((A->B)->(((A->B)->((A->!(B))->!(A)))->(!(B)->((A->B)->((A->!(B))->!(A))))))"),
    R_7("(((A->B)->((A->B)->((A->!(B))->!(A))))->(((A->B)->(((A->B)->((A->!(B))->!(A)))->(!(B)->((A->B)->((A->!(B))->!(A))))))->((A->B)->(!(B)->((A->B)->((A->!(B))->!(A)))))))"),
    R_8("(((A->B)->(((A->B)->((A->!(B))->!(A)))->(!(B)->((A->B)->((A->!(B))->!(A))))))->((A->B)->(!(B)->((A->B)->((A->!(B))->!(A))))))"),
    R_9("((A->B)->(!(B)->((A->B)->((A->!(B))->!(A)))))"),
    R_10("((A->B)->((A->B)->(A->B)))"),
    R_11("((A->B)->(((A->B)->(A->B))->(A->B)))"),
    R_12("(((A->B)->((A->B)->(A->B)))->(((A->B)->(((A->B)->(A->B))->(A->B)))->((A->B)->(A->B))))"),
    R_13("(((A->B)->(((A->B)->(A->B))->(A->B)))->((A->B)->(A->B)))"),
    R_14("((A->B)->(A->B))"),
    R_15("((A->B)->(!(B)->(A->B)))"),
    R_16("(((A->B)->(!(B)->(A->B)))->((A->B)->((A->B)->(!(B)->(A->B)))))"),
    R_17("((A->B)->((A->B)->(!(B)->(A->B))))"),
    R_18("(((A->B)->(A->B))->(((A->B)->((A->B)->(!(B)->(A->B))))->((A->B)->(!(B)->(A->B)))))"),
    R_19("(((A->B)->((A->B)->(!(B)->(A->B))))->((A->B)->(!(B)->(A->B))))"),
    R_20("((A->B)->(!(B)->(A->B)))"),
    R_21("((!(B)->(A->B))->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A)))))"),
    R_22("(((!(B)->(A->B))->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A)))))->((A->B)->((!(B)->(A->B))->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A)))))))"),
    R_23("((A->B)->((!(B)->(A->B))->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A))))))"),
    R_24("(((A->B)->(!(B)->(A->B)))->(((A->B)->((!(B)->(A->B))->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A))))))->((A->B)->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A)))))))"),
    R_25("(((A->B)->((!(B)->(A->B))->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A))))))->((A->B)->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A))))))"),
    R_26("((A->B)->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A)))))"),
    R_27("(((A->B)->(!(B)->((A->B)->((A->!(B))->!(A)))))->(((A->B)->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A)))))->((A->B)->(!(B)->((A->!(B))->!(A))))))"),
    R_28("(((A->B)->((!(B)->((A->B)->((A->!(B))->!(A))))->(!(B)->((A->!(B))->!(A)))))->((A->B)->(!(B)->((A->!(B))->!(A)))))"),
    R_29("((A->B)->(!(B)->((A->!(B))->!(A))))"),
    R_30("(!(B)->(A->!(B)))"),
    R_31("((!(B)->(A->!(B)))->((A->B)->(!(B)->(A->!(B)))))"),
    R_32("((A->B)->(!(B)->(A->!(B))))"),
    R_33("((!(B)->(A->!(B)))->(!(B)->(!(B)->(A->!(B)))))"),
    R_34("(((!(B)->(A->!(B)))->(!(B)->(!(B)->(A->!(B)))))->((A->B)->((!(B)->(A->!(B)))->(!(B)->(!(B)->(A->!(B)))))))"),
    R_35("((A->B)->((!(B)->(A->!(B)))->(!(B)->(!(B)->(A->!(B))))))"),
    R_36("(((A->B)->(!(B)->(A->!(B))))->(((A->B)->((!(B)->(A->!(B)))->(!(B)->(!(B)->(A->!(B))))))->((A->B)->(!(B)->(!(B)->(A->!(B)))))))"),
    R_37("(((A->B)->((!(B)->(A->!(B)))->(!(B)->(!(B)->(A->!(B))))))->((A->B)->(!(B)->(!(B)->(A->!(B))))))"),
    R_38("((A->B)->(!(B)->(!(B)->(A->!(B)))))"),
    R_39("(!(B)->(!(B)->!(B)))"),
    R_40("((!(B)->(!(B)->!(B)))->((A->B)->(!(B)->(!(B)->!(B)))))"),
    R_41("((A->B)->(!(B)->(!(B)->!(B))))"),
    R_42("(!(B)->((!(B)->!(B))->!(B)))"),
    R_43("((!(B)->((!(B)->!(B))->!(B)))->((A->B)->(!(B)->((!(B)->!(B))->!(B)))))"),
    R_44("((A->B)->(!(B)->((!(B)->!(B))->!(B))))"),
    R_45("((!(B)->(!(B)->!(B)))->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B))))"),
    R_46("(((!(B)->(!(B)->!(B)))->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B))))->((A->B)->((!(B)->(!(B)->!(B)))->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B))))))"),
    R_47("((A->B)->((!(B)->(!(B)->!(B)))->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B)))))"),
    R_48("(((A->B)->(!(B)->(!(B)->!(B))))->(((A->B)->((!(B)->(!(B)->!(B)))->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B)))))->((A->B)->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B))))))"),
    R_49("(((A->B)->((!(B)->(!(B)->!(B)))->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B)))))->((A->B)->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B)))))"),
    R_50("((A->B)->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B))))"),
    R_51("(((A->B)->(!(B)->((!(B)->!(B))->!(B))))->(((A->B)->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B))))->((A->B)->(!(B)->!(B)))))"),
    R_52("(((A->B)->((!(B)->((!(B)->!(B))->!(B)))->(!(B)->!(B))))->((A->B)->(!(B)->!(B))))"),
    R_53("((A->B)->(!(B)->!(B)))"),
    R_54("((!(B)->!(B))->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B)))))"),
    R_55("(((!(B)->!(B))->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B)))))->((A->B)->((!(B)->!(B))->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B)))))))"),
    R_56("((A->B)->((!(B)->!(B))->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B))))))"),
    R_57("(((A->B)->(!(B)->!(B)))->(((A->B)->((!(B)->!(B))->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B))))))->((A->B)->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B)))))))"),
    R_58("(((A->B)->((!(B)->!(B))->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B))))))->((A->B)->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B))))))"),
    R_59("((A->B)->((!(B)->(!(B)->(A->!(B))))->(!(B)->(A->!(B)))))"),
    R_60("(!(B)->(A->!(B)))"),
    R_61("((!(B)->(A->!(B)))->((A->B)->(!(B)->(A->!(B)))))"),
    R_62("((A->B)->(!(B)->(A->!(B))))"),
    R_63("((!(B)->(A->!(B)))->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))"),
    R_64("(((!(B)->(A->!(B)))->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))->((A->B)->((!(B)->(A->!(B)))->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))))"),
    R_65("((A->B)->((!(B)->(A->!(B)))->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A)))))"),
    R_66("(((A->B)->(!(B)->(A->!(B))))->(((A->B)->((!(B)->(A->!(B)))->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A)))))->((A->B)->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))))"),
    R_67("(((A->B)->((!(B)->(A->!(B)))->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A)))))->((A->B)->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A)))))"),
    R_68("((A->B)->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))"),
    R_69("(((A->B)->(!(B)->(A->!(B))))->(((A->B)->((!(B)->(A->!(B)))->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A)))))->((A->B)->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))))"),
    R_70("(((A->B)->((!(B)->(A->!(B)))->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A)))))->((A->B)->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A)))))"),
    R_71("((A->B)->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))"),
    R_72("(((A->B)->(!(B)->((A->!(B))->!(A))))->(((A->B)->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))->((A->B)->(!(B)->!(A)))))"),
    R_73("(((A->B)->((!(B)->((A->!(B))->!(A)))->(!(B)->!(A))))->((A->B)->(!(B)->!(A))))"),
    R_74("((A->B)->(!(B)->!(A)))");


    private String s;


    private InsaneHardcodedContrapositionRule(String s) {
        this.s = s;
    }

    public Expression replace(Expression e, Expression e1) {
        return parse(s.replace("A", "(" + e.toString() + ")").replace("B", "(" + e1.toString() + ")"));
    }

    public String replace(String e, String e1) {
        return s.replace("A", "(" + e + ")").replace("B", "(" + e1 + ")");
    }
}
