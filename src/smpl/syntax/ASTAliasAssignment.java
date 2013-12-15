/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTAliasAssignment extends ASTExpression<ASTNode<?>> {

    public ASTAliasAssignment(final ASTIdentifier iden,
	    final ASTExpression<?> exp) {
	super("alias_assignment", iden, exp);
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
	return visitor.visitAliasAssignment(this, state);
    }

}
