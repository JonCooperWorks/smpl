/**
 * 
 */
package smpl.semantics.SMPLValue.Objects.NativeFunctions;

import java.util.ArrayList;
import java.util.Arrays;

import smpl.semantics.SMPLValue.SMPLEnvironment;
import smpl.semantics.SMPLValue.SMPLEvaluator;
import smpl.semantics.SMPLValue.SMPLException;
import smpl.semantics.SMPLValue.Objects.SMPLInteger;
import smpl.semantics.SMPLValue.Objects.SMPLNativeProcedure;
import smpl.semantics.SMPLValue.Objects.SMPLObject;
import smpl.semantics.SMPLValue.Objects.SMPLString;
import smpl.semantics.SMPLValue.Objects.SMPLValue;
import smpl.syntax.ASTException;
import smpl.syntax.ASTIdentifier;
import smpl.syntax.ASTNoOperationNode;

/**
 * 
 */
public class SMPLSubStrNativeFunction extends SMPLNativeProcedure {

    public SMPLSubStrNativeFunction() {
	super(new ArrayList<String>(Arrays.asList(new String[] { "string",
		"start", "stop" })), new ASTNoOperationNode(),
		new ASTIdentifier("expressionList"), Flags.None);
    }

    @Override
    public SMPLValue<SMPLObject> invoke(final SMPLEvaluator evaluator,
	    final SMPLEnvironment newFrame) throws ASTException {
	final SMPLObject strValue = newFrame.lookUp("string").getInternalValue(
		evaluator, newFrame);
	final SMPLObject startValue = newFrame.lookUp("start")
		.getInternalValue(evaluator, newFrame);
	final SMPLObject stopValue = newFrame.lookUp("stop").getInternalValue(
		evaluator, newFrame);

	final SMPLString str;
	final SMPLInteger start;
	final SMPLInteger stop;

	if (!(strValue instanceof SMPLString))
	    throw new SMPLException(
		    "SubStr function requires a string and two intergers as its arguments. \n Given: "
			    + strValue.toRepr()
			    + " and "
			    + startValue.toRepr()
			    + " and " + stopValue.toRepr());
	else
	    str = (SMPLString) strValue;

	if (!(startValue instanceof SMPLInteger))
	    throw new SMPLException(
		    "SubStr function requires a string and two intergers as its arguments. \n Given: "
			    + strValue.toRepr()
			    + " and "
			    + startValue.toRepr()
			    + " and " + stopValue.toRepr());
	else
	    start = (SMPLInteger) startValue;

	if (!(stopValue instanceof SMPLInteger))
	    throw new SMPLException(
		    "SubStr function requires a string and two intergers as its arguments. \n Given: "
			    + strValue.toRepr()
			    + " and "
			    + startValue.toRepr()
			    + " and " + stopValue.toRepr());
	else
	    stop = (SMPLInteger) stopValue;

	final String strString = str.getString();
	final int startInt = start.getNumber().intValue();
	final int stopInt = stop.getNumber().intValue();
	if (startInt >= strString.length() || startInt < 0)
	    throw new SMPLException(
		    "SubStr function requires start index be positive and less than the length of the string. \n Given: "
			    + strValue.toRepr()
			    + " and "
			    + startValue.toRepr()
			    + " and " + stopValue.toRepr());
	if (stopInt > strString.length())
	    throw new SMPLException(
		    "SubStr function requires end index be at most, the length of the string. \n Given: "
			    + strValue.toRepr()
			    + " and "
			    + startValue.toRepr()
			    + " and " + stopValue.toRepr());

	SMPLString result;
	if (startInt > stopInt)
	    result = new SMPLString("");
	else
	    result = new SMPLString(strString.substring(startInt, stopInt));

	return new SMPLValue<SMPLObject>(result);
    }
}
