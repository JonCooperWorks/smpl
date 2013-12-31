/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

/**
 * 
 */
public class SMPLString extends SMPLObject {
	private final String mInternalString;

	public SMPLString(final String str) {
		super("str");
		mInternalString = str;
	}

	public String getString() {
		return mInternalString;
	}

	@Override
	public String toString() {
		return mInternalString.toString();
	}

	@Override
	public boolean isEqualTo(final SMPLObject obj) {
		if (obj instanceof SMPLString)
			return mInternalString.equals(((SMPLString) obj).getString());
		return false;
	}
}
