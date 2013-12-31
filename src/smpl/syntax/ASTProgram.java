/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTProgram extends ASTNode<ASTStatementSequence> {
	/**
	 * Create a new program instance from a sequence of statements.
	 * 
	 * @param seq
	 *            The sequence of statements forming the program.
	 */
	public ASTProgram(final ASTStatementSequence seq) {
		super("program", seq);
	}

	/**
	 * 
	 * @return The sequence of statements making up the body of this program.
	 */
	public ASTStatementSequence getBody() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitProgram(this, state);
	}

}
