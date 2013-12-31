/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTBitwiseComplementExpression extends
		ASTBitwiseExpression<ASTExpression<?>> {

	public ASTBitwiseComplementExpression(final ASTExpression<?> operand) {
		super("complement_expression", operand);
	}

	public ASTExpression<?> getOperand() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitBitwiseComplement(this, state);
	}
}
