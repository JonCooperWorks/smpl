/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTNoOperationNode extends ASTExpression<ASTNode<?>> {

    public ASTNoOperationNode() {
	super("no_operation");
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitNoOperation(this, state);
    }

}
