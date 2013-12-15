/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTInteger extends ASTExpression<ASTNoOperationNode> {

    private final String mMatchedString;

    public ASTInteger(final String strValue) {
	super("integer(" + strValue + ")");

	mMatchedString = strValue;
    }

    public String getMatchedString() {
	return mMatchedString;
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitInteger(this, state);
    }

}
