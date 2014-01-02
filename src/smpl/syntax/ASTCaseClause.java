/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCaseClause extends ASTArithExp<ASTExp<?>> {

	public ASTCaseClause(final ASTExp<?> predicate,
			final ASTExp<?> clause) {
		super("case_clause", predicate, clause);
	}

	public ASTExp<?> getPredicateExpression() {
		return getChild(0);
	}

	public ASTExp<?> getClauseExpresion() {
		return getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCaseClasue(this, state);
	}
}
