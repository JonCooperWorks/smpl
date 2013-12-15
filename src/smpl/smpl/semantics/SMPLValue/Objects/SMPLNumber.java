/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

/**
 * @author aston
 * 
 */
public abstract class SMPLNumber extends SMPLObject {
    public SMPLNumber(final String typeName) {
	super(typeName);
    }

    public abstract Number getNumber();

    public abstract boolean isLessThan(final SMPLNumber otherNum);

    public abstract boolean isGreaterThan(final SMPLNumber otherNum);
}
