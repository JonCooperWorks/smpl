/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTStmtSequence extends ASTNode<ASTStatement<?>> {

	public ASTStmtSequence() {
		super("statement_sequence");
	}

	public ASTStmtSequence(final ASTStatement<?>... exp) {
		super("statement_sequence", exp);
	}

	/* Chainable Function */
	public ASTStmtSequence add(final ASTStatement<?> exp) {
		addChild(exp);
		return this;
	}

	public ASTStatement<?> getStatement(final int i) {
		return getChild(i);
	}

	public Iterable<ASTStatement<?>> getStatements() {
		return getChildren();
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitStatementSequence(this, state);
	}

}
