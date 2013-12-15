/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTPrintExpression extends ASTExpression<ASTExpression<?>> {
    public ASTPrintExpression(final ASTExpression<?> expression) {
	super("print_expresssion", expression);
    }

    public ASTExpression<?> getExpression() {
	return getChild(0);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitPrint(this, state);
    }
}
