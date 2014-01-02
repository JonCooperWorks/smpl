/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTComplementExp extends
		ASTBitwiseExp<ASTExp<?>> {

	public ASTComplementExp(final ASTExp<?> operand) {
		super("complement_expression", operand);
	}

	public ASTExp<?> getOperand() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitBitwiseComplement(this, state);
	}
}
