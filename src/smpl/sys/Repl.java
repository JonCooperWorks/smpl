package smpl.sys;

import smpl.semantics.SMPLValue.SMPLEvaluator;
import java.io.*;
import smpl.semantics.Visitor;
import smpl.syntax.SMPLLexer;
import smpl.syntax.SMPLParser;
import smpl.syntax.ASTProgram;
import java.util.ArrayList;

public class Repl<S, T> {

    public final String PROMPT = ">";
    protected Class<? extends Visitor<S, T>> evalClass;
    Visitor<S, T> interp;

    public Repl(Class<? extends Visitor<S, T>> vClass) {
        this.interp = null;
        evalClass = vClass;
        try {
            interp = evalClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ie) {
            System.err.println(ie.getMessage());
            System.err.println("Fatal error: Failed to instantiate "
                    + "interpreter!  Terminating...");
            System.exit(1);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        // Create a new SMPL Repl.
        Repl<?, ?> repl;
        repl = new Repl<>(SMPLEvaluator.class);
        repl.loop();
    }

    public void visitFiles(ArrayList<String> fileNames) {
        // Treat all other command line arguments as files to be read and evaluated
        FileReader freader;
        for (String file : fileNames) {
            try {
                System.out.println("Reading from: " + file + "...");
                freader = new FileReader(new File(file));
                parseVisitShow(interp, freader);
                System.out.println("Done! Press ENTER to continue");
                System.in.read();
            } catch (FileNotFoundException fnfe) {
                System.err.println(fnfe.getMessage());
                System.err.println("Skipping it");
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
    }

    /**
     * The driver loop in which the standard input is read until EOF is pressed
     * (Ctrl-D on Unix, Ctrl-Z on Windows); on each pass of the loop, that input
     * is parsed, and visited, and the result is displayed .
     */
    public void loop() {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            parseVisitShow(interp, reader);
        }
    }

    /**
     * Read a program from the given input reader, then parse it and evaluate it
     * and display the result.
     *
     * @param interp The interpreter to be used to evaluate the program
     * @param reader The input reader supplying the program
     */
    public void parseVisitShow(Visitor<S, T> interp, Reader reader) {
        SMPLParser parser;
        ASTProgram program = null;

        System.out.print(PROMPT);
        try {
            parser = new SMPLParser(new SMPLLexer(reader));
            program = (ASTProgram) parser.parse().value;
        } catch (Exception e) {
            System.out.println("Parse Error: " + e.getMessage());
        }


        if (program != null) {
            try {
                T result;
                // A null state indicates that this is the entry call to interp
                result = program.visit(interp, null);
                System.out.println("\nResult: " + result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
