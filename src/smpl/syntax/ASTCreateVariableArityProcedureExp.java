/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCreateVariableArityProcedureExp extends
		ASTExp<ASTNode<?>> {
	public ASTCreateVariableArityProcedureExp(
			final ASTIdentifierList ids, final ASTIdent vargIdentifier,
			final ASTExp<?> body) {
		super("create_varg_procedure", ids, vargIdentifier, body);
	}

	public ASTIdentifierList getParameters() {
		return (ASTIdentifierList) getChild(0);
	}

	public ASTIdent getVArgIdentifier() {
		return (ASTIdent) getChild(1);
	}

	public ASTExp<?> getBodyExpression() {
		return (ASTExp<?>) getChild(2);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCreateVariableArityProcedure(this, state);
	}
}
