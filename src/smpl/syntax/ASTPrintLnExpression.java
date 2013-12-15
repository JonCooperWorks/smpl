/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTPrintLnExpression extends ASTExpression<ASTExpression<?>> {
    public ASTPrintLnExpression(final ASTExpression<?> expression) {
	super("print_ln_expresssion", expression);
    }

    public ASTExpression<?> getExpression() {
	return getChild(0);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitPrintLn(this, state);
    }
}
