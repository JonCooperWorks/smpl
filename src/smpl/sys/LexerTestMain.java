/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smpl.sys;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java_cup.runtime.Symbol;
import smpl.syntax.SMPLLexer;
import smpl.syntax.sym;

/**
 * 
 */
public class LexerTestMain {

    private static Logger sLogger = Logger.getLogger(LexerTestMain.class
	    .getName());
    private static HashMap<Integer, String> sSymbolMap = new HashMap<Integer, String>();

    static {
	{
	    LexerTestMain.sSymbolMap.put(sym.KW_READ_INT, "KW_READ_INT");
	    LexerTestMain.sSymbolMap.put(sym.LIT_COLON, "LIT_COLON");
	    LexerTestMain.sSymbolMap.put(sym.KW_FALSE, "KW_FALSE");
	    LexerTestMain.sSymbolMap.put(sym.KW_PRINT, "KW_PRINT");
	    LexerTestMain.sSymbolMap.put(sym.LIT_ASSIGN, "LIT_ASSIGN");
	    LexerTestMain.sSymbolMap.put(sym.DOUBLE, "DOUBLE");
	    LexerTestMain.sSymbolMap.put(sym.INTEGER, "INTEGER");
	    LexerTestMain.sSymbolMap.put(sym.LIT_MULTIPLY, "LIT_MULTIPLY");
	    LexerTestMain.sSymbolMap.put(sym.KW_EMPTY_LIST, "KW_EMPTY_LIST");
	    LexerTestMain.sSymbolMap.put(sym.KW_CASE, "KW_CASE");
	    LexerTestMain.sSymbolMap.put(sym.LIT_RBRACKET, "LIT_RBRACKET");
	    LexerTestMain.sSymbolMap.put(sym.CHARACTER, "CHARACTER");
	    LexerTestMain.sSymbolMap.put(sym.LIT_REL_GT, "LIT_REL_GT");
	    LexerTestMain.sSymbolMap.put(sym.LIT_LOGIC_NOT, "LIT_LOGIC_NOT");
	    LexerTestMain.sSymbolMap.put(sym.LIT_LOGIC_AND, "LIT_LOGIC_AND");
	    LexerTestMain.sSymbolMap.put(sym.LIT_LBRACE, "LIT_LBRACE");
	    LexerTestMain.sSymbolMap.put(sym.LIT_MODULUS, "LIT_MODULUS");
	    LexerTestMain.sSymbolMap.put(sym.LIT_LOGIC_OR, "LIT_LOGIC_OR");
	    LexerTestMain.sSymbolMap.put(sym.LIT_ADD, "LIT_ADD");
	    LexerTestMain.sSymbolMap.put(sym.KW_LAZY, "KW_LAZY");
	    LexerTestMain.sSymbolMap.put(sym.KW_CALL, "KW_CALL");
	    LexerTestMain.sSymbolMap.put(sym.EOF, "EOF");
	    LexerTestMain.sSymbolMap.put(sym.LIT_RBRACE, "LIT_RBRACE");
	    LexerTestMain.sSymbolMap.put(sym.error, "error");
	    LexerTestMain.sSymbolMap.put(sym.LIT_MINUS, "LIT_MINUS");
	    LexerTestMain.sSymbolMap.put(sym.LIT_REL_GTEQ, "LIT_REL_GTEQ");
	    LexerTestMain.sSymbolMap.put(sym.LIT_BIT_OR, "LIT_BIT_OR");
	    LexerTestMain.sSymbolMap.put(sym.KW_TRUE, "KW_TRUE");
	    LexerTestMain.sSymbolMap.put(sym.KW_IF, "KW_IF");
	    LexerTestMain.sSymbolMap.put(sym.LIT_REL_LTEQ, "LIT_REL_LTEQ");
	    LexerTestMain.sSymbolMap.put(sym.LIT_REL_LT, "LIT_REL_LT");
	    LexerTestMain.sSymbolMap.put(sym.KW_LET, "KW_LET");
	    LexerTestMain.sSymbolMap.put(sym.LIT_COMMA, "LIT_COMMA");
	    LexerTestMain.sSymbolMap.put(sym.LIT_REL_NOT_EQ, "LIT_REL_NOT_EQ");
	    LexerTestMain.sSymbolMap.put(sym.LIT_DIVIDE, "LIT_DIVIDE");
	    LexerTestMain.sSymbolMap.put(sym.LIT_BIT_AND, "LIT_BIT_AND");
	    LexerTestMain.sSymbolMap.put(sym.KW_DEF, "KW_DEF");
	    LexerTestMain.sSymbolMap.put(sym.LIT_LBRACKET, "LIT_LBRACKET");
	    LexerTestMain.sSymbolMap.put(sym.LIT_CONCAT, "LIT_CONCAT");
	    LexerTestMain.sSymbolMap.put(sym.KW_ELSE, "KW_ELSE");
	    LexerTestMain.sSymbolMap.put(sym.LIT_BIT_COMP, "LIT_BIT_COMP");
	    LexerTestMain.sSymbolMap.put(sym.KW_READ, "KW_READ");
	    LexerTestMain.sSymbolMap.put(sym.STRING, "STRING");
	    LexerTestMain.sSymbolMap.put(sym.LIT_LPAREN, "LIT_LPAREN");
	    LexerTestMain.sSymbolMap.put(sym.LIT_SEMI_COLON, "LIT_SEMI_COLON");
	    LexerTestMain.sSymbolMap.put(sym.KW_THEN, "KW_THEN");
	    LexerTestMain.sSymbolMap.put(sym.KW_PRINT_LN, "KW_PRINT_LN");
	    LexerTestMain.sSymbolMap.put(sym.KW_PROC, "KW_PROC");
	    LexerTestMain.sSymbolMap.put(sym.LIT_REL_EQ, "LIT_REL_EQ");
	    LexerTestMain.sSymbolMap.put(sym.LIT_RPAREN, "LIT_RPAREN");
	    LexerTestMain.sSymbolMap.put(sym.IDENTIFIER, "IDENTIFIER");
	}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {

	System.out.println("SMPL Lexer Evaluator");

	final Scanner scanner = new Scanner(System.in);
	boolean lexMore = true;

	while (lexMore) {
	    System.out.print("SMPL> ");
	    final String line = scanner.nextLine();

	    if ("#end#".equals(line.trim()))
		lexMore = false;
	    else {
		// convert String into InputStream
		final InputStream is = new ByteArrayInputStream(line.getBytes());

		LexerTestMain.lexShow(is, true);
	    }
	}

	scanner.close();

	System.out.println("Lexer Tester Exitting");
    }

    public static void lexShow(final InputStream is, final boolean debug) {
	final SMPLLexer lexer = new SMPLLexer(is);
	System.out.print("Token Stream: ");
	Symbol currentToken = null;
	do
	    try {
		currentToken = lexer.next_token();
		System.out
			.print(LexerTestMain.sSymbolMap.get(currentToken.sym));
		System.out.print("('");
		System.out.print(lexer.getText());
		System.out.print("')");
		System.out.print(" ");
	    } catch (final IOException ex) {
		if (debug)
		    LexerTestMain.sLogger.log(Level.SEVERE, null, ex);
		else
		    System.out.println("ERROR: " + ex.getMessage());
	    }
	while (currentToken != null && currentToken.sym != sym.EOF);
	System.out.println();
    }
}
