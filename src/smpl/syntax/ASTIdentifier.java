/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTIdentifier extends ASTExpression<ASTNoOperationNode> {
    private final String mIdentifierName;

    private int mFlags;

    private final static String[] sIdentifierTypes = { "identifier",
	    "reference_identifier" };

    public ASTIdentifier(final String idenitiferName, final int flags) {
	super(ASTIdentifier.sIdentifierTypes[flags] + "(" + idenitiferName
		+ ")");
	mIdentifierName = idenitiferName;
	mFlags = flags;
    }

    public ASTIdentifier(final String idenitiferName) {
	this(idenitiferName, 0);
    }

    public String getIdentifierName() {
	return mIdentifierName;
    }

    public void setIsReference() {
	mFlags = 1;

	setName(ASTIdentifier.sIdentifierTypes[mFlags] + "(" + mIdentifierName
		+ ")");
    }

    public boolean isReference() {
	return mFlags == 1;
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitIdentifier(this, state);
    }

}
