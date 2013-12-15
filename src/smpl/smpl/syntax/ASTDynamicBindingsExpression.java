/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTDynamicBindingsExpression extends
	ASTExpression<ASTIdentifierList> {

    public ASTDynamicBindingsExpression(final ASTIdentifierList l) {
	super("dynamic_identifiers", l);
    }

    public ASTIdentifierList getDynamicIdentifiers() {
	return getChild(0);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitDynamicBindingExpression(this, state);
    }

}
