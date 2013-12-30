import java_cup.runtime.*;
import java.io.*;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.SMPLException;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.syntax.ASTException;
import smpl.syntax.ASTNode;
import smpl.syntax.ASTProgram;
import smpl.syntax.SMPLLexer;
import smpl.syntax.SMPLParser;

public class Repl {

	private static SMPLEnvironment globalEnv;
    private static SMPLEvaluator sCurrentInterpreterEvaluator;
    public static final String PROMPT = ">";

    public static Class<? extends SMPLEvaluator> evalClass;

    public static void main(String args[]) {
	// if provided, first command line argument is class of evaluator
	// default is Evaluator
	if (args.length == 0)
	    evalClass = SMPLEvaluator.class;
	else {
	    try {
		evalClass = (Class<? extends SMPLEvaluator>) Class.forName(args[0]);
	    } catch (ClassNotFoundException cnfe) {
		System.err.println(cnfe.getMessage());
		System.exit(1);
	    }
	}

	repl(System.in, globalEnv);
    }

    public static void repl(InputStream is, SMPLEnvironment env){
	while (true) {
	    parseEvalShow(is, env);
	}
    }

    public static void parseEvalShow(InputStream is, SMPLEnvironment env) {
	SMPLParser parser;
	ASTProgram program = null;
	SMPLEvaluator interp;
	try {
	    interp = evalClass.newInstance();

	    System.out.print(PROMPT);
	    try {
		parser = new SMPLParser(new SMPLLexer(is));
		program = (ASTProgram) parser.parse().value;
	    } catch (Exception e) {
		System.out.println("Parse Error: " + e.getMessage());
	    }

	    if (program != null)
		try {
		    SMPLValue result;
		    result = program.visit(interp, env);
		    System.out.println("\nResult: " + result);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	} catch (InstantiationException ie) {
	    System.err.println(ie.getMessage());
	    System.exit(1);
	} catch (IllegalAccessException iae) {
	    System.err.println(iae.getMessage());
	    System.exit(1);
	}
    }
}
