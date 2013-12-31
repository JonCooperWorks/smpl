/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTStatementSequenceExpression extends
		ASTExpression<ASTStatementSequence> {

	public ASTStatementSequenceExpression(final ASTStatementSequence statements) {
		super("statement_sequence_expression", statements);
	}

	public ASTStatementSequence getStatements() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitStatementSequenceExpression(this, state);
	}

}
