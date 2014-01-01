/**
 * 
 */
package smpl.semantics.SMPLValue.Objects.NativeFunctions;

import java.util.ArrayList;
import java.util.Arrays;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.Objects.SMPLList;
import smpl.semantics.SMPLValue.Objects.SMPLNativeProcedure;
import smpl.semantics.SMPLValue.Objects.SMPLObject;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.syntax.ASTIdent;
import smpl.syntax.ASTNoOp;

/**
 * 
 */
public class SMPLPairNativeFunction extends SMPLNativeProcedure {

    public SMPLPairNativeFunction() {
	super(
		new ArrayList<String>(
			Arrays.asList(new String[] { "e1", "e2" })),
		new ASTNoOp(), new ASTIdent("expressionList"),
		Flags.None);
    }

    @Override
    public SMPLValue<SMPLObject> invoke(final SMPLEvaluator evaluator,
	    final SMPLEnvironment newFrame) {
	final SMPLValue<SMPLObject> e1 = newFrame.lookUp("e1");
	final SMPLValue<SMPLObject> e2 = newFrame.lookUp("e2");

	final SMPLList pair = new SMPLList(2);
	pair.add(e1);
	pair.add(e2);

	return new SMPLValue<SMPLObject>(pair);
    }

}
