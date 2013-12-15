/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

import java.math.BigDecimal;

/**
 * 
 */
public class SMPLDouble extends SMPLNumber {
    private final BigDecimal mInternalDouble;

    public SMPLDouble(final BigDecimal num) {
	super("double");
	mInternalDouble = num;
    }

    @Override
    public BigDecimal getNumber() {
	return mInternalDouble;
    }

    @Override
    public boolean isEqualTo(final SMPLObject obj) {
	if (obj instanceof SMPLDouble)
	    return mInternalDouble.compareTo(((SMPLDouble) obj).getNumber()) == 0;
	else if (obj instanceof SMPLInteger)
	    return mInternalDouble.compareTo(new BigDecimal(((SMPLInteger) obj)
		    .getNumber())) == 0;
	return false;
    }

    @Override
    public boolean isLessThan(final SMPLNumber obj) {
	if (obj instanceof SMPLDouble)
	    return mInternalDouble.compareTo(((SMPLDouble) obj).getNumber()) < 0;
	else if (obj instanceof SMPLInteger)
	    return mInternalDouble.compareTo(new BigDecimal(((SMPLInteger) obj)
		    .getNumber())) < 0;
	return false;
    }

    @Override
    public boolean isGreaterThan(final SMPLNumber obj) {
	if (obj instanceof SMPLDouble)
	    return mInternalDouble.compareTo(((SMPLDouble) obj).getNumber()) > 0;
	else if (obj instanceof SMPLInteger)
	    return mInternalDouble.compareTo(new BigDecimal(((SMPLInteger) obj)
		    .getNumber())) > 0;
	return false;
    }

    @Override
    public String toString() {
	return mInternalDouble.toString();
    }
}
