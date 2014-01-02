/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCreateOpenArityProcedureExp extends
		ASTExp<ASTNode<?>> {
	public ASTCreateOpenArityProcedureExp(
			final ASTIdent vargIdentifier, final ASTExp<?> body) {
		super("create_open_arg_procedure", vargIdentifier, body);
	}

	public ASTIdent getVArgIdentifier() {
		return (ASTIdent) getChild(0);
	}

	public ASTExp<?> getBodyExpression() {
		return (ASTExp<?>) getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCreateOpenArityProcedure(this, state);
	}
}
