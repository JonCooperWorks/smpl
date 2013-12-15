/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTMultipleAliasAssignment extends ASTExpression<ASTNode<?>> {

    public ASTMultipleAliasAssignment(final ASTIdentifierList identifierList,
	    final ASTExpression<?> exp) {
	super("multiple_alias_assignment", identifierList, exp);
    }

    public ASTIdentifierList getIdentifierList() {
	return (ASTIdentifierList) getChild(0);
    }

    public ASTExpression<?> getExpressionNode() {
	return (ASTExpression<?>) getChild(1);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitMultipleAliasAssignment(this, state);
    }

}
