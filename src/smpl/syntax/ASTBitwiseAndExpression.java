package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTBitwiseAndExpression extends
		ASTBitwiseExpression<ASTExpression<?>> {

	public ASTBitwiseAndExpression(final ASTExpression<?> operand1,
			final ASTExpression<?> operand2) {
		super("bit_and_expression", operand1, operand2);
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
		return visitor.visitBitwiseAnd(this, state);
	}

}
