package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTLogicalNotExp extends
		ASTBitwiseExp<ASTExp<?>> {

	public ASTLogicalNotExp(final ASTExp<?> operand) {
		super("not_expression", operand);
	}

	public ASTExp<?> getOperand() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitLogicalNot(this, state);
	}

}
