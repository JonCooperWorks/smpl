/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

/**
 * 
 */
public class SMPLBoolean extends SMPLObject {

    private final Boolean mInternalBoolean;

    public SMPLBoolean(final Boolean boolValue) {
	super("bool");
	mInternalBoolean = boolValue;
    }

    public Boolean getBool() {
	return mInternalBoolean;
    }

    @Override
    public boolean isEqualTo(final SMPLObject obj) {
	if (obj instanceof SMPLBoolean)
	    return mInternalBoolean.booleanValue() == ((SMPLBoolean) obj)
		    .getBool().booleanValue();
	return false;
    }

    @Override
    public String toString() {
	return mInternalBoolean.toString();
    }
}
