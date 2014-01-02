/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTBooleanConstantExp extends
		ASTExp<ASTNoOp> {

	private final boolean mInternalValue;

	public ASTBooleanConstantExp(final boolean value) {
		super("boolean(" + value + ")");
		mInternalValue = value;
	}

	public boolean getBool() {
		return mInternalValue;
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitBooleanConstant(this, state);
	}
}
