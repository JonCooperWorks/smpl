/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTDouble extends ASTExp<ASTNoOp> {

	private final String mMatchedString;

	public ASTDouble(final String strValue) {
		super("double(" + strValue + ")");

		mMatchedString = strValue;
	}

	public String getMatchedString() {
		return mMatchedString;
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitDouble(this, state);
	}

}
