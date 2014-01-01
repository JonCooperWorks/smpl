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
import smpl.semantics.SMPLValue.Objects.SMPLPair;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.syntax.ASTException;
import smpl.syntax.ASTIdent;
import smpl.syntax.ASTNoOp;

/**
 * 
 */
public class SMPLIsPairNativeFunction extends SMPLNativeProcedure {

    public SMPLIsPairNativeFunction() {
	super(new ArrayList<String>(Arrays.asList(new String[] { "list" })),
		new ASTNoOp(), new ASTIdent("expressionList"),
		Flags.None);
    }

    @Override
    public SMPLValue<SMPLObject> invoke(final SMPLEvaluator evaluator,
	    final SMPLEnvironment newFrame) throws ASTException {
	final SMPLValue<SMPLObject> list = newFrame.lookUp("list");

	final SMPLBoolean result;
	if (!(list.rawValue() instanceof SMPLPair))
	    result = new SMPLBoolean(false);
	else
	    result = new SMPLBoolean(true);

	return new SMPLValue<SMPLObject>(result);
    }
}
