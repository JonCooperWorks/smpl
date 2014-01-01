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
import smpl.syntax.ASTIdent;
import smpl.syntax.ASTNoOp;

/**
 * 
 */
public class SMPLCdrNativeFunction extends SMPLNativeProcedure {

    public SMPLCdrNativeFunction() {
	super(new ArrayList<String>(Arrays.asList(new String[] { "list" })),
		new ASTNoOp(), new ASTIdent("expressionList"),
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

	    if (listFOrmatted.size() == 1)
		return listFOrmatted.get(0);

	    if (listFOrmatted.isEmpty())
		throw new SMPLException(
			"Cdr function requires a list with atleast 1 element, as its argument. \n Given: "
				+ list.toRepr());

	    final SMPLList newList = new SMPLList();
	    newList.addAll(listFOrmatted.subList(1, listFOrmatted.size()));

	    if (newList.size() < 2) {

	    }
	    return new SMPLValue<SMPLObject>(newList);
	}
    }

}
