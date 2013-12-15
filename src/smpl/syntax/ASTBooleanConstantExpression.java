/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTBooleanConstantExpression extends
	ASTExpression<ASTNoOperationNode> {

    private final boolean mInternalValue;

    public ASTBooleanConstantExpression(final boolean value) {
	super("boolean(" + value + ")");
	mInternalValue = value;
    }

    public boolean getBool() {
	return mInternalValue;
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitBooleanConstant(this, state);
    }
}
