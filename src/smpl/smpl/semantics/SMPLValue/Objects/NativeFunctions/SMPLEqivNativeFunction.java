/**
 * 
 */
package smpl.semantics.SMPLValue.Objects.NativeFunctions;

import java.util.ArrayList;
import java.util.Arrays;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.Objects.SMPLBoolean;
import smpl.semantics.SMPLValue.Objects.SMPLNativeProcedure;
import smpl.semantics.SMPLValue.Objects.SMPLObject;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.syntax.ASTException;
import smpl.syntax.ASTIdentifier;
import smpl.syntax.ASTNoOperationNode;

/**
 * 
 */
public class SMPLEqivNativeFunction extends SMPLNativeProcedure {

    public SMPLEqivNativeFunction() {
	super(
		new ArrayList<String>(
			Arrays.asList(new String[] { "e1", "e2" })),
		new ASTNoOperationNode(), new ASTIdentifier("expressionList"),
		Flags.None);
    }

    @Override
    public SMPLValue<SMPLObject> invoke(final SMPLEvaluator evaluator,
	    final SMPLEnvironment newFrame) throws ASTException {
	final SMPLValue<SMPLObject> e1 = newFrame.lookUp("e1");
	final SMPLValue<SMPLObject> e2 = newFrame.lookUp("e2");

	final SMPLObject e1Obj = e1.getInternalValue(evaluator, newFrame);
	final SMPLObject e2Obj = e2.getInternalValue(evaluator, newFrame);

	final SMPLBoolean result = new SMPLBoolean(e1Obj.equals(e2Obj));

	return new SMPLValue<SMPLObject>(result);
    }

}
