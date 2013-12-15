/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

/**
 * 
 */
public class SMPLSubVectorExpression extends SMPLObject {
    private final SMPLInteger mSubVectorSize;
    private final SMPLProcedure mSubVectorProcedure;

    public SMPLSubVectorExpression(final SMPLInteger subVectorSize,
	    final SMPLProcedure subVectorFunction) {
	super("sub_vector_expression");

	mSubVectorSize = subVectorSize;
	mSubVectorProcedure = subVectorFunction;
    }

    public SMPLInteger getSubVectorSize() {
	return mSubVectorSize;
    }

    public SMPLProcedure getSubVectorFunction() {
	return mSubVectorProcedure;
    }

    @Override
    public String toString() {
	return mSubVectorSize.toString() + ":" + mSubVectorProcedure.toString();
    }
}
