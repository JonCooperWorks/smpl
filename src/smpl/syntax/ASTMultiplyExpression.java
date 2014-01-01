/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTMultiplyExpression extends ASTArithExp<ASTExp<?>> {

	public ASTMultiplyExpression(final ASTExp<?> operand1,
			final ASTExp<?> operand2) {
		super("multiply_expression", operand1, operand2);
	}

	public ASTExp<?> getOperand1() {
		return getChild(0);
	}

	public ASTExp<?> getOperand2() {
		return getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitMultiply(this, state);
	}
}
