/**
 * 
 */
package smpl.semantics.SMPLValue.Objects.NativeFunctions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.SMPLException;
import smpl.semantics.SMPLValue.Objects.SMPLInteger;
import smpl.semantics.SMPLValue.Objects.SMPLNativeProcedure;
import smpl.semantics.SMPLValue.Objects.SMPLObject;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.semantics.SMPLValue.Objects.SMPLVector;
import smpl.syntax.ASTException;
import smpl.syntax.ASTIdent;
import smpl.syntax.ASTNoOp;

/**
 * 
 */
public class SMPLSizeNativeFunction extends SMPLNativeProcedure {

    public SMPLSizeNativeFunction() {
	super(new ArrayList<String>(Arrays.asList(new String[] { "vector" })),
		new ASTNoOp(), new ASTIdent("expressionList"),
		Flags.None);
    }

    @Override
    public SMPLValue<SMPLObject> invoke(final SMPLEvaluator evaluator,
	    final SMPLEnvironment newFrame) throws ASTException {
	final SMPLObject vectorValue = newFrame.lookUp("vector")
		.getInternalValue(evaluator, newFrame);

	if (!(vectorValue instanceof SMPLVector))
	    throw new SMPLException(
		    "Size function requires a vetor as its argument. \n Given: "
			    + vectorValue.toRepr());
	else {
	    final SMPLVector vector = (SMPLVector) vectorValue;

	    final SMPLInteger result = new SMPLInteger(new BigInteger(
		    ((Integer) vector.size()).toString()));

	    return new SMPLValue<SMPLObject>(result);
	}
    }

}
