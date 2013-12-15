/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTReadIntegerExpression extends ASTExpression<ASTNode<?>> {

    public ASTReadIntegerExpression() {
	super("read_integer_command");
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitReadInteger(this, state);
    }

}
