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

import smpl.syntax.ASTException;
import smpl.syntax.ASTNode;
import smpl.syntax.SMPLLexer;
import smpl.syntax.SMPLParser;

/**
 * 
 */
public class ParserTestMain {

    private static Logger sLogger = Logger.getLogger(ParserTestMain.class
	    .getName());

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(final String[] args) {
	System.out.println("SMPL Lexer Evaluator");
	System.out.println("Enter #stop# to mark the end of a program "
		+ "and print the parse tree.");
	System.out.println("Enter #end# to exit.");

	final Scanner scanner = new Scanner(System.in);
	boolean parseMore = true;

	while (parseMore) {
	    boolean readMoreProgramStatements = true;
	    final StringBuilder programBuilder = new StringBuilder();

	    while (readMoreProgramStatements) {
		System.out.print("SMPL> ");
		final String line = scanner.nextLine().trim();

		if ("#end#".equals(line)) {
		    parseMore = false;
		    readMoreProgramStatements = false;
		} else if ("#stop#".equals(line))
		    readMoreProgramStatements = false;
		else
		    programBuilder.append(line +" \n ");

	    }

	    // convert String into InputStream
	    final InputStream is = new ByteArrayInputStream(programBuilder
		    .toString().getBytes());

	    ParserTestMain.parseShow(is);
	}
	scanner.close();

	System.out.println("Parser Tester Exitting");
    }

    public static void parseShow(final InputStream is) {
	final SMPLLexer lexer = new SMPLLexer(is);
	final SMPLParser parser = new SMPLParser(lexer);

	try {
	    final ASTNode<?> result = (ASTNode<?>) parser.parse().value;
	    System.out.println("Parse Tree: ");
	    System.out.println(result.getTreeRepresentation());
	} catch (final ASTException e) {
	    ParserTestMain.sLogger.log(Level.SEVERE, "Could not parse.");
	    ParserTestMain.sLogger.log(Level.SEVERE, null, e);
	} catch (final Exception e) {
	    ParserTestMain.sLogger.log(Level.SEVERE, "Could not parse.");
	    ParserTestMain.sLogger.log(Level.SEVERE, null, e);
	}
    }
}
