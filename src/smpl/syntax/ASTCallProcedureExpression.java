/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCallProcedureExpression extends ASTExpression<ASTNode<?>> {

	public ASTCallProcedureExpression(final ASTExpression<?> identifier,
			final ASTArgumentList params) {
		super("call_procedure", identifier, params);
	}

	public ASTExpression<?> getIdentifier() {
		return (ASTExpression<?>) getChild(0);
	}

	public ASTArgumentList getParameters() {
		return (ASTArgumentList) getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCallProcedure(this, state);
	}
}
