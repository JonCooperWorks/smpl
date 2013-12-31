/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCreateProcedureExpression extends ASTExpression<ASTNode<?>> {
	public ASTCreateProcedureExpression(final ASTIdentifierList ids,
			final ASTExpression<?> body) {
		super("create_procedure", ids, body);
	}

	public ASTIdentifierList getParameters() {
		return (ASTIdentifierList) getChild(0);
	}

	public ASTExpression<?> getBodyExpression() {
		return (ASTExpression<?>) getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCreateProcedure(this, state);
	}
}
