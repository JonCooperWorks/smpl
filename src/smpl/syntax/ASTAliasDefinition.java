/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTAliasDefinition extends ASTExpression<ASTNode<?>> {

    public ASTAliasDefinition(final ASTIdentifier iden,
	    final ASTExpression<?> exp) {
	super("alias_definition", iden, exp);
    }

    public ASTIdentifier getIdentifierNode() {
	return (ASTIdentifier) getChild(0);
    }

    public ASTExpression<?> getExpressionNode() {
	return (ASTExpression<?>) getChild(1);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitAliasDefinition(this, state);
    }

}
