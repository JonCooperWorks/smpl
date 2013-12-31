/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTDivideExpression extends ASTArithExpression<ASTExpression<?>> {

	public ASTDivideExpression(final ASTExpression<?> operand1,
			final ASTExpression<?> operand2) {
		super("divide_expression", operand1, operand2);
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
		return visitor.visitDivide(this, state);
	}
}
