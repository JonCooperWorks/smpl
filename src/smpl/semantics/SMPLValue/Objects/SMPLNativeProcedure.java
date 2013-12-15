/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

import java.util.ArrayList;

import smpl.syntax.ASTExpression;
import smpl.syntax.ASTIdentifier;

/**
 * 
 */
public abstract class SMPLNativeProcedure extends SMPLProcedure {

    protected SMPLNativeProcedure(final ArrayList<String> params,
	    final ASTExpression<?> body, final ASTIdentifier vArgIdentifier,
	    final int flags) {
	super(params, body, vArgIdentifier, flags);
    }

}
