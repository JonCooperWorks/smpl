/**
 * 
 */
package smpl.syntax;

import org.apache.commons.lang3.StringEscapeUtils;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCharacter extends ASTExp<ASTNoOp> {
	private final Character mInternalValue;

	private final String mMatchedString;

	public ASTCharacter(final String strValue) {
		super("character(" + strValue + ")");

		mMatchedString = strValue;
		final String unEscapedString = StringEscapeUtils.unescapeJava(strValue);
		if (unEscapedString.length() < 1)
			mInternalValue = null;
		else
			mInternalValue = unEscapedString.charAt(0);
	}

	public ASTCharacter(final String strValue, final boolean isHExValue) {
		super("character(" + strValue + ")");

		mMatchedString = strValue;
		final int unicodeValue = Integer.parseInt(strValue.substring(2), 16);
		mInternalValue = (char) unicodeValue;
		setName("character(" + strValue + " = "
				+ StringEscapeUtils.escapeJava(mInternalValue.toString()) + ")");
	}

	public Character getCharacterValue() {
		return mInternalValue;
	}

	@SuppressWarnings("unused")
	private String getMatchedString() {
		return mMatchedString;
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCharacter(this, state);
	}

}
