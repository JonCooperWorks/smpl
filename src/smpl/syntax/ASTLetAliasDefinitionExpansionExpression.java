/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTLetAliasDefinitionExpansionExpression extends
	ASTExpression<ASTNode<?>> {
    public ASTLetAliasDefinitionExpansionExpression(
	    final ASTAliasDefinitionList definitions,
	    final ASTExpression<?> body) {
	super("let_extend_body_definitions", definitions, body);
    }

    public ASTAliasDefinitionList getDefinitions() {
	return (ASTAliasDefinitionList) getChild(0);
    }

    public ASTExpression<?> getBodyExpression() {
	return (ASTExpression<?>) getChild(1);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitLetAliasDefinitionExpansion(this, state);
    }
}
