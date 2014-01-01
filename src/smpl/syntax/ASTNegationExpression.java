/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTNegationExpression extends
		ASTBitwiseExp<ASTExp<?>> {

	public ASTNegationExpression(final ASTExp<?> operand) {
		super("negation_expression", operand);
	}

	public ASTExp<?> getOperand() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitNegation(this, state);
	}
}
