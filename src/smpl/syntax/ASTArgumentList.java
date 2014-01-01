/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTArgumentList extends ASTStatement<ASTExp<?>> {

    public ASTArgumentList() {
	super("parameter_list");
    }

    public ASTArgumentList(final ASTExp<?>... exp) {
	super("parameter_list", exp);
    }

    /* Chainable Function */
    public ASTArgumentList add(final ASTExp<?> exp) {
	addChild(exp);
	return this;
    }

    public ASTExp<?> getExpression(final int i) {
	return getChild(i);
    }

    public Iterable<ASTExp<?>> getExpressions() {
	return getChildren();
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitArgumentList(this, state);
    }

}
