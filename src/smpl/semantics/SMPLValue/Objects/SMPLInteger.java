/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 
 */
public class SMPLInteger extends SMPLNumber {
    private final BigInteger mInternalInteger;

    public SMPLInteger(final BigInteger num) {
	super("int");
	mInternalInteger = num;
    }

    @Override
    public BigInteger getNumber() {
	return mInternalInteger;
    }

    @Override
    public boolean isEqualTo(final SMPLObject obj) {
	if (obj instanceof SMPLInteger)
	    return mInternalInteger.compareTo(((SMPLInteger) obj).getNumber()) == 0;
	else if (obj instanceof SMPLDouble)
	    return (new BigDecimal(mInternalInteger))
		    .compareTo(((SMPLDouble) obj).getNumber()) == 0;
	return false;
    }

    @Override
    public boolean isLessThan(final SMPLNumber obj) {
	if (obj instanceof SMPLInteger)
	    return mInternalInteger.compareTo(((SMPLInteger) obj).getNumber()) < 0;
	else if (obj instanceof SMPLDouble)
	    return (new BigDecimal(mInternalInteger))
		    .compareTo(((SMPLDouble) obj).getNumber()) < 0;
	return false;
    }

    @Override
    public boolean isGreaterThan(final SMPLNumber obj) {
	if (obj instanceof SMPLInteger)
	    return mInternalInteger.compareTo(((SMPLInteger) obj).getNumber()) > 0;
	else if (obj instanceof SMPLDouble)
	    return (new BigDecimal(mInternalInteger))
		    .compareTo(((SMPLDouble) obj).getNumber()) > 0;
	return false;
    }

    @Override
    public String toString() {
	return mInternalInteger.toString();
    }
}
