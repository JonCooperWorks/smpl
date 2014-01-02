/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTSpecialCallProcedureExp extends
		ASTExp<ASTExp<?>> {
	public ASTSpecialCallProcedureExp(final ASTExp<?> procedure,
			final ASTExp<?> args) {
		super("special_call_procedure", procedure, args);
	}

	public ASTExp<?> getProcedureExpression() {
		return getChild(0);
	}

	public ASTExp<?> getArgListExpression() {
		return getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitSpecialCallProcedure(this, state);
	}
}
