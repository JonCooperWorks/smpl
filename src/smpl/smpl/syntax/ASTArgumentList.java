/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTArgumentList extends ASTStatement<ASTExpression<?>> {

    public ASTArgumentList() {
	super("parameter_list");
    }

    public ASTArgumentList(final ASTExpression<?>... exp) {
	super("parameter_list", exp);
    }

    /* Chainable Function */
    public ASTArgumentList add(final ASTExpression<?> exp) {
	addChild(exp);
	return this;
    }

    public ASTExpression<?> getExpression(final int i) {
	return getChild(i);
    }

    public Iterable<ASTExpression<?>> getExpressions() {
	return getChildren();
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitArgumentList(this, state);
    }

}
