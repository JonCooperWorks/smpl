package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTExpressionList extends ASTNode<ASTExpression<?>> {

    public ASTExpressionList() {
	super("expresson_list");
    }

    public ASTExpressionList(final ASTExpression<?>... exp) {
	super("expresson_list", exp);
    }

    /* Chainable Function */
    public ASTExpressionList add(final ASTExpression<?> exp) {
	addChild(exp);
	return this;
    }

    public ASTExpression<?> getExpression(final int i) {
	return getChild(i);
    }

    public Iterable<ASTExpression<?>> getExpressions() {
	return getChildren();
    }

    public int numExpressions() {
	return getNumChildren();
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitExpressionList(this, state);
    }

}
