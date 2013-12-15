/**
 * 
 */
package smpl.semantics.SMPLValue.Objects.NativeFunctions;

import java.util.ArrayList;
import java.util.Arrays;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.SMPLException;
import smpl.semantics.SMPLValue.Objects.SMPLList;
import smpl.semantics.SMPLValue.Objects.SMPLNativeProcedure;
import smpl.semantics.SMPLValue.Objects.SMPLObject;
import smpl.semantics.SMPLValue.Objects.SMPLPair;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.syntax.ASTException;
import smpl.syntax.ASTIdentifier;
import smpl.syntax.ASTNoOperationNode;

/**
 * 
 */
public class SMPLCarNativeFunction extends SMPLNativeProcedure {

    public SMPLCarNativeFunction() {
	super(new ArrayList<String>(Arrays.asList(new String[] { "list" })),
		new ASTNoOperationNode(), new ASTIdentifier("expressionList"),
		Flags.None);
    }

    @Override
    public SMPLValue<SMPLObject> invoke(final SMPLEvaluator evaluator,
	    final SMPLEnvironment newFrame) throws ASTException {
	final SMPLObject list = newFrame.lookUp("list").getInternalValue(
		evaluator, newFrame);

	if (!(list instanceof SMPLPair))
	    throw new SMPLException(
		    "Car function requires a list as its argument. \n Given: "
			    + list.toRepr());
	else {
	    final SMPLList listFOrmatted = (SMPLList) list;

	    if (listFOrmatted.isEmpty())
		throw new SMPLException(
			"Car function requires a non empty list as its argument. \n Given: "
				+ list.toRepr());
	    return listFOrmatted.get(0);
	}
    }

}
