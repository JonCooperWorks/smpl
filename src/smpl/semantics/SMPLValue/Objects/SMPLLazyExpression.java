/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.syntax.ASTException;
import smpl.syntax.ASTExp;

/**
 * 
 */
public class SMPLLazyExpression extends SMPLObject {
	private final ASTExp<?> mExpressionToEvaluate;

	private SMPLObject mChachedValue;

	public SMPLLazyExpression(final ASTExp<?> exp) {
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
