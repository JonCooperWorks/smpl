/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.syntax.ASTException;
import smpl.syntax.ASTExpression;

/**
 * 
 */
public class SMPLLazyExpression extends SMPLObject {
    private final ASTExpression<?> mExpressionToEvaluate;

    private SMPLObject mChachedValue;

    public SMPLLazyExpression(final ASTExpression<?> exp) {
	super("lazy");
	mExpressionToEvaluate = exp;
    }

    public SMPLObject getEvaluatedExpression(final SMPLEvaluator visitor,
	    final SMPLEnvironment state) throws ASTException {

	if (mChachedValue == null) {
	    @SuppressWarnings("unchecked")
	    final SMPLValue<SMPLObject> smplEvaluatedExpression = mExpressionToEvaluate
		    .visit(visitor, state);

	    mChachedValue = smplEvaluatedExpression.getInternalValue(visitor,
		    state);
	}
	return mChachedValue;
    }

    @Override
    public String toString() {
	if (mChachedValue == null)
	    return mExpressionToEvaluate.getTreeRepresentation();
	return "chache=" + mChachedValue.toRepr();
    }
}
