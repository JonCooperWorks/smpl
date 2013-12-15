/**
 * 
 */
package smpl.syntax;

import org.apache.commons.lang3.StringEscapeUtils;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTString extends ASTExpression<ASTNoOperationNode> {
    private final String internalValue;

    public ASTString(final String strValue) {
	super("string(" + strValue + ")");
	internalValue = StringEscapeUtils.unescapeJava(strValue);
    }

    public String getStringValue() {
	return internalValue;
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitString(this, state);
    }

}
