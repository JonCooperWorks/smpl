/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

/**
 * 
 */
public class SMPLCharacter extends SMPLObject {
    private final Character mInternalCharacter;

    public SMPLCharacter(final Character character) {
	super("char");
	mInternalCharacter = character;
    }

    public Character getChar() {
	return mInternalCharacter;
    }

    @Override
    public boolean isEqualTo(final SMPLObject obj) {
	if (obj instanceof SMPLCharacter)
	    return mInternalCharacter.charValue() == ((SMPLCharacter) obj)
		    .getChar().charValue();
	return false;
    }

    @Override
    public String toString() {
	return mInternalCharacter.toString();
    }

}
