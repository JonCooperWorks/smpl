/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCreateOpenAriyProcedureExpression extends
	ASTExpression<ASTNode<?>> {
    public ASTCreateOpenAriyProcedureExpression(
	    final ASTIdentifier vargIdentifier, final ASTExpression<?> body) {
	super("create_open_arg_procedure", vargIdentifier, body);
    }

    public ASTIdentifier getVArgIdentifier() {
	return (ASTIdentifier) getChild(0);
    }

    public ASTExpression<?> getBodyExpression() {
	return (ASTExpression<?>) getChild(1);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitCreateOpenAriyProcedure(this, state);
    }
}
