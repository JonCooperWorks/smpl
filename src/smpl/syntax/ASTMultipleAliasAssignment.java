/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTMultipleAliasAssignment extends ASTExp<ASTNode<?>> {

	public ASTMultipleAliasAssignment(final ASTIdentifierList identifierList,
			final ASTExp<?> exp) {
		super("multiple_alias_assignment", identifierList, exp);
	}

	public ASTIdentifierList getIdentifierList() {
		return (ASTIdentifierList) getChild(0);
	}

	public ASTExp<?> getExpressionNode() {
		return (ASTExp<?>) getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitMultipleAliasAssignment(this, state);
	}

}
