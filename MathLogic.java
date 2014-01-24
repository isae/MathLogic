import ru.ifmo.ctddev.isaev.*;
import ru.ifmo.ctddev.isaev.exception.IncorrectProofException;
import ru.ifmo.ctddev.isaev.exception.LexingException;
import ru.ifmo.ctddev.isaev.exception.ParsingException;
import ru.ifmo.ctddev.isaev.exception.ProofGeneratingException;
import ru.ifmo.ctddev.isaev.parser.LogicParser;

import java.io.*;

import static ru.ifmo.ctddev.isaev.General.in;
import static ru.ifmo.ctddev.isaev.General.out;

/**
 * Created with IntelliJ IDEA.
 * User: Xottab
 * Date: 05.11.13
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class MathLogic {
    private static Homework work;

    public static void main(String[] args) {
        switch (args[0]) {
            case "1": {
                General.setParser(new LogicParser());
                work = new Checker1();
                break;
            }
            case "2": {
                General.setParser(new LogicParser());
                work = new Deduct2();
                break;
            }
            case "3": {
                General.setParser(new LogicParser());
                work = new Proof3();
                break;
            }
            case "4": {
                break;
            }
            case "5": {
                break;
            }
            case "6": {
                break;
            }
            default:
                break;
        }
        try {
            in = new BufferedReader(new FileReader(args[1]));
            String[] arr = args[1].split("\\.");
            out = new PrintWriter(new FileWriter(arr[0] + ".out"));
            work.doSomething();
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        } catch (IOException | LexingException | ParsingException | IncorrectProofException | ProofGeneratingException e) {
            e.printStackTrace();
        }
    }
}

