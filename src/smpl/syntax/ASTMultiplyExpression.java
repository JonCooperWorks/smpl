/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTMultiplyExpression extends ASTArithExpression<ASTExpression<?>> {

	public ASTMultiplyExpression(final ASTExpression<?> operand1,
			final ASTExpression<?> operand2) {
		super("multiply_expression", operand1, operand2);
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
		return visitor.visitMultiply(this, state);
	}
}
