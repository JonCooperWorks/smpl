/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTRelationalEqualExpression extends
	ASTRelationalExpression<ASTExpression<?>> {

    public ASTRelationalEqualExpression(final ASTExpression<?> operand1,
	    final ASTExpression<?> operand2) {
	super("equal_expression", operand1, operand2);
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
	return visitor.visitRelationalEqual(this, state);
    }

}
