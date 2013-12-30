/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.sys;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.SMPLException;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.syntax.ASTException;
import smpl.syntax.ASTNode;
import smpl.syntax.SMPLLexer;
import smpl.syntax.SMPLParser;

/**
 * 
 */
public class Repl {

    private static Logger sLogger = Logger.getLogger(Repl.class
	    .getName());

    private static SMPLEnvironment sGlobalInterpreterEnvironment;
    private static SMPLEvaluator sCurrentInterpreterEvaluator;

    private static int sInterpreterFlags = Flags.None;

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(final String[] args) {
	// Defualt evaluator is the SMPLEvaluator
	Repl.sCurrentInterpreterEvaluator = new SMPLEvaluator();
	Repl.sGlobalInterpreterEnvironment = Repl.sCurrentInterpreterEvaluator
		.getGlobalEnvironment();

	System.out.println("SMPL Lexer Evaluator");
	System.out.println("Enter #exit# to exit the interpreter.");
	System.out.println("Enter #begin# to start multiline input.");
	System.out.println("Enter #end# to end multiline input.");

	final Scanner scanner = new Scanner(System.in);
	boolean parseMore = true;
	boolean skipNextParse = true;

	while (parseMore) {
	    boolean inMultilineEntry = false;
	    final StringBuilder programBuilder = new StringBuilder();

	    skipNextParse = false;
	    do {
		if (inMultilineEntry)
		    System.out.print("SMPL--> ");
		else
		    System.out.print("SMPL> ");
		final String line = scanner.nextLine().trim();

		if (!Repl.serviceFlags(line))
		    if ("#begin#".equals(line)) {
			inMultilineEntry = true;
			System.out.println("SMPL--> Multiline input started.");
		    } else if ("#exit#".equals(line)) {
			parseMore = false;
			inMultilineEntry = false;
		    } else if ("#end#".equals(line)) {
			System.out.println("SMPL--> Multiline input ended.");
			inMultilineEntry = false;
		    } else
			programBuilder.append(line + " \n ");
		else
		    skipNextParse = true;
	    } while (inMultilineEntry);

	    if (!skipNextParse) {
		// convert String into InputStream
		final InputStream is = new ByteArrayInputStream(programBuilder
			.toString().getBytes());

		Repl.parseEvalShow(is);

		if (Repl.isFlagSet(Flags.LexStream)) {

		    // convert String into InputStream
		    final InputStream is2 = new ByteArrayInputStream(
			    programBuilder.toString().getBytes());

		    //LexerTestMain.lexShow(is2, false);
		}

	    }
	}
	scanner.close();

	System.out.println("Parser Interpreter Exitting");
    }

    public static void parseEvalShow(final InputStream is) {
	final SMPLLexer lexer = new SMPLLexer(is);
	final SMPLParser parser = new SMPLParser(lexer);

	try {
	    final ASTNode<?> result = (ASTNode<?>) parser.parse().value;

	    if (Repl.isFlagSet(Flags.ParseTree)) {
		System.out.println("SMPL Parse Tree-> ");
		System.out.println(result.getTreeRepresentation());
	    }

	    final SMPLValue<?> evalutedResult = result.visit(
		    Repl.sCurrentInterpreterEvaluator,
		    Repl.sGlobalInterpreterEnvironment);

	    System.out.println("SMPL Result-> ");
	    System.out.println(evalutedResult.toString());

	} catch (final SMPLException e) {
	    Repl.sLogger.log(Level.SEVERE, e.getMessage());
	    if (Repl.isFlagSet(Flags.Debug))
		Repl.sLogger.log(Level.SEVERE, null, e);
	} catch (final ASTException e) {
	    Repl.sLogger.log(Level.SEVERE, e.getMessage());
	    if (Repl.isFlagSet(Flags.Debug))
		Repl.sLogger.log(Level.SEVERE, null, e);
	} catch (final Exception e) {
	    Repl.sLogger.log(Level.SEVERE, e.getMessage());
	    if (Repl.isFlagSet(Flags.Debug))
		Repl.sLogger.log(Level.SEVERE, null, e);
	}
    }

    private static boolean serviceFlags(final String line) {
	if ("#debug#".equals(line)) {
	    Repl.setFlag(Flags.Debug);
	    System.out.println("SMPL--> Debug output started.");
	    return true;
	}
	if ("#nodebug#".equals(line)) {
	    Repl.clearFlag(Flags.Debug);
	    System.out.println("SMPL--> Debug output ended.");
	    return true;
	}
	if ("#parsetree#".equals(line)) {
	    Repl.setFlag(Flags.ParseTree);
	    System.out.println("SMPL--> Parse Tree output started.");
	    return true;
	}
	if ("#noparsetree#".equals(line)) {
	    Repl.clearFlag(Flags.ParseTree);
	    System.out.println("SMPL--> Parse Tree output ended.");
	    return true;
	}
	if ("#lexstream#".equals(line)) {
	    Repl.setFlag(Flags.LexStream);
	    System.out.println("SMPL--> Lexical Steam output started.");
	    return true;
	}
	if ("#nolexstream#".equals(line)) {
	    Repl.clearFlag(Flags.LexStream);
	    System.out.println("SMPL--> Lexical Stream output ended.");
	    return true;
	}

	return false;
    }

    private static boolean isFlagSet(final int flag) {
	return (Repl.sInterpreterFlags & flag) == flag;
    }

    private static void setFlag(final int flag) {
	Repl.sInterpreterFlags |= flag;
    }

    private static void clearFlag(final int flag) {
	Repl.sInterpreterFlags &= ~(flag);
    }

    private static interface Flags {
	int None = 0;
	int Debug = 1;
	int ParseTree = 1;
	int LexStream = 1;
    }
}
