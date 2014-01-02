/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTLetAliasDefinitionExpansionExp extends
		ASTExp<ASTNode<?>> {
	public ASTLetAliasDefinitionExpansionExp(
			final ASTAliasDefinitionList definitions,
			final ASTExp<?> body) {
		super("let_extend_body_definitions", definitions, body);
	}

	public ASTAliasDefinitionList getDefinitions() {
		return (ASTAliasDefinitionList) getChild(0);
	}

	public ASTExp<?> getBodyExpression() {
		return (ASTExp<?>) getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitLetAliasDefinitionExpansion(this, state);
	}
}
