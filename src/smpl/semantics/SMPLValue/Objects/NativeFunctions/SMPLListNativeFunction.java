/**
 * 
 */
package smpl.semantics.SMPLValue.Objects.NativeFunctions;

import java.util.ArrayList;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.Objects.SMPLList;
import smpl.semantics.SMPLValue.Objects.SMPLNativeProcedure;
import smpl.semantics.SMPLValue.Objects.SMPLNillObject;
import smpl.semantics.SMPLValue.Objects.SMPLObject;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.syntax.ASTException;
import smpl.syntax.ASTIdentifier;
import smpl.syntax.ASTNoOperationNode;

/**
 * 
 */
public class SMPLListNativeFunction extends SMPLNativeProcedure {

    public SMPLListNativeFunction() {
	super(new ArrayList<String>(), new ASTNoOperationNode(),
		new ASTIdentifier("expressionList"), Flags.VariableArity);
    }

    @Override
    public SMPLValue<SMPLObject> invoke(final SMPLEvaluator evaluator,
	    final SMPLEnvironment newFrame) throws ASTException {
	final SMPLValue<SMPLObject> listElements = newFrame
		.lookUp("expressionList");

	final SMPLList list = (SMPLList) listElements.getInternalValue(
		evaluator, newFrame);

	list.add(new SMPLValue<SMPLObject>(new SMPLNillObject()));

	return listElements;
    }

}
