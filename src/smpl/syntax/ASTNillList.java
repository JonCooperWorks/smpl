/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTNillList extends ASTListCreationExpression {

    public ASTNillList() {
	super();
	setName("nill");
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitNiiList(this, state);
    }

}
