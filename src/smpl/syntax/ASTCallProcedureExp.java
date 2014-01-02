/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCallProcedureExp extends ASTExp<ASTNode<?>> {

	public ASTCallProcedureExp(final ASTExp<?> identifier,
			final ASTArgumentList params) {
		super("call_procedure", identifier, params);
	}

	public ASTExp<?> getIdentifier() {
		return (ASTExp<?>) getChild(0);
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
