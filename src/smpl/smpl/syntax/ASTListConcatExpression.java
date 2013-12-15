/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTListConcatExpression extends
	ASTListExpression<ASTExpression<?>> {

    public ASTListConcatExpression(final ASTExpression<?> exp,
	    final ASTExpression<?> exp2) {
	super("list_concat_expression", exp, exp2);
    }

    public ASTExpression<?> getOperand1() {
	return getChild(0);
    }

    public ASTExpression<?> getOperand2() {
	return getChild(1);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitListConcatenationExpression(this, state);
    }

}
