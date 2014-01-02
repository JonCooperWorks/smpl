/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCreateProcedureExp extends ASTExp<ASTNode<?>> {
	public ASTCreateProcedureExp(final ASTIdentifierList ids,
			final ASTExp<?> body) {
		super("create_procedure", ids, body);
	}

	public ASTIdentifierList getParameters() {
		return (ASTIdentifierList) getChild(0);
	}

	public ASTExp<?> getBodyExpression() {
		return (ASTExp<?>) getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCreateProcedure(this, state);
	}
}
