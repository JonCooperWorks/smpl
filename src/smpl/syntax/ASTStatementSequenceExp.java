/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTStatementSequenceExp extends
		ASTExp<ASTStmtSequence> {

	public ASTStatementSequenceExp(final ASTStmtSequence statements) {
		super("statement_sequence_expression", statements);
	}

	public ASTStmtSequence getStatements() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitStatementSequenceExp(this, state);
	}

}
