/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTSpecialCallProcedureExpression extends
	ASTExpression<ASTExpression<?>> {
    public ASTSpecialCallProcedureExpression(final ASTExpression<?> procedure,
	    final ASTExpression<?> args) {
	super("special_call_procedure", procedure, args);
    }

    public ASTExpression<?> getProcedureExpression() {
	return getChild(0);
    }

    public ASTExpression<?> getArgListExpression() {
	return getChild(1);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitSpecialCallProcedure(this, state);
    }
}
