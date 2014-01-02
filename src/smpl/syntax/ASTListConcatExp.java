/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTListConcatExp extends
		ASTListExpression<ASTExp<?>> {

	public ASTListConcatExp(final ASTExp<?> exp,
			final ASTExp<?> exp2) {
		super("list_concat_expression", exp, exp2);
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
		return visitor.visitListConcatExp(this, state);
	}

}
