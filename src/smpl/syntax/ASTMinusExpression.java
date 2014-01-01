/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTMinusExpression extends ASTArithExp<ASTExp<?>> {

	public ASTMinusExpression(final ASTExp<?> operand1,
			final ASTExp<?> operand2) {
		super("minus_expression", operand1, operand2);
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
		return visitor.visitMinus(this, state);
	}
}
