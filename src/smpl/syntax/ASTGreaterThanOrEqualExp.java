/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTGreaterThanOrEqualExp extends
		ASTRelationalExpression<ASTExp<?>> {

	public ASTGreaterThanOrEqualExp(
			final ASTExp<?> operand1, final ASTExp<?> operand2) {
		super("greater_or_equal_expression", operand1, operand2);
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
		return visitor.visitGreaterThanOrEqual(this, state);
	}

}
