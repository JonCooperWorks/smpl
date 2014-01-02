package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTBitwiseOrExp extends
		ASTBitwiseExp<ASTExp<?>> {

	public ASTBitwiseOrExp(final ASTExp<?> operand1,
			final ASTExp<?> operand2) {
		super("bit_or_expression", operand1, operand2);
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
		return visitor.visitBitwiseOr(this, state);
	}

}
