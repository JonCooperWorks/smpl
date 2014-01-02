/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTAliasAssign extends ASTExp<ASTNode<?>> {

	public ASTAliasAssign(final ASTIdent ident,
			final ASTExp<?> exp) {
		super("alias_assignment", ident, exp);
	}

	public ASTIdent getIdentifierNode() {
		return (ASTIdent) getChild(0);
	}

	public ASTExp<?> getExpressionNode() {
		return (ASTExp<?>) getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitAliasAssignment(this, state);
	}

}
