package smpl.semantics.SMPLValue.Objects;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.syntax.ASTException;

/**
 * 
 */
public class SMPLValue<T extends Object> {
    private final T internalValue;

    /**
     * 
     * @param val
     *            The value to be wrapped by this instance
     */
    public SMPLValue(final T val) {
	internalValue = val;
    }

    /**
     * 
     * @return The Java value wrapped by this class
     * @throws ASTException
     */
    @SuppressWarnings("unchecked")
    public T getInternalValue(final SMPLEvaluator visitor,
	    final SMPLEnvironment state) throws ASTException {
	if (internalValue instanceof SMPLLazyExpression)
	    return (T) ((SMPLLazyExpression) internalValue)
		    .getEvaluatedExpression(visitor, state);

	return internalValue;
    }

    @SuppressWarnings("unchecked")
    public Class<T> internalType() {
	return (Class<T>) internalValue.getClass();
    }

    public T rawValue() {
	return internalValue;
    }

    @Override
    public String toString() {
	if (internalValue instanceof SMPLObject)
	    return ((SMPLObject) internalValue).toRepr();

	return internalValue.toString();
    }
}
