/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCreateVariableAriyProcedureExpression extends
		ASTExpression<ASTNode<?>> {
	public ASTCreateVariableAriyProcedureExpression(
			final ASTIdentifierList ids, final ASTIdentifier vargIdentifier,
			final ASTExpression<?> body) {
		super("create_varg_procedure", ids, vargIdentifier, body);
	}

	public ASTIdentifierList getParameters() {
		return (ASTIdentifierList) getChild(0);
	}

	public ASTIdentifier getVArgIdentifier() {
		return (ASTIdentifier) getChild(1);
	}

	public ASTExpression<?> getBodyExpression() {
		return (ASTExpression<?>) getChild(2);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCreateVariableAriyProcedure(this, state);
	}
}
