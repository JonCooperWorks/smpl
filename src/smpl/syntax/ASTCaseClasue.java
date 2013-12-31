/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCaseClasue extends ASTArithExpression<ASTExpression<?>> {

	public ASTCaseClasue(final ASTExpression<?> predicate,
			final ASTExpression<?> clause) {
		super("case_clause", predicate, clause);
	}

	public ASTExpression<?> getPredicateExpression() {
		return getChild(0);
	}

	public ASTExpression<?> getClauseExpresion() {
		return getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCaseClasue(this, state);
	}
}
