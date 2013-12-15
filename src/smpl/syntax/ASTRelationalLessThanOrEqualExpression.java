/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTRelationalLessThanOrEqualExpression extends
	ASTRelationalExpression<ASTExpression<?>> {

    public ASTRelationalLessThanOrEqualExpression(
	    final ASTExpression<?> operand1, final ASTExpression<?> operand2) {
	super("less_or_equal_expression", operand1, operand2);
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
	return visitor.visitRelationalLessThanOrEqual(this, state);
    }

}
