/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTMinusExpression extends ASTArithExpression<ASTExpression<?>> {

	public ASTMinusExpression(final ASTExpression<?> operand1,
			final ASTExpression<?> operand2) {
		super("minus_expression", operand1, operand2);
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
		return visitor.visitMinus(this, state);
	}
}
