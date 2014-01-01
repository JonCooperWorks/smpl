/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

import java.util.ArrayList;

import smpl.syntax.ASTExp;
import smpl.syntax.ASTIdent;

/**
 * 
 */
public abstract class SMPLNativeProcedure extends SMPLProcedure {

	protected SMPLNativeProcedure(final ArrayList<String> params,
			final ASTExp<?> body, final ASTIdent vArgIdentifier,
			final int flags) {
		super(params, body, vArgIdentifier, flags);
	}

}
