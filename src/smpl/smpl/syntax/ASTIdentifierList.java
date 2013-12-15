/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTIdentifierList extends ASTNode<ASTIdentifier> {

    public ASTIdentifierList() {
	super("identifier_list");
    }

    public ASTIdentifierList(final ASTIdentifier... exp) {
	super("identifier_list", exp);
    }

    /* Chainable Function */
    public ASTIdentifierList add(final ASTIdentifier exp) {
	addChild(exp);
	return this;
    }

    public ASTIdentifier getIdentifier(final int i) {
	return getChild(i);
    }

    public Iterable<ASTIdentifier> getIdentifiers() {
	return getChildren();
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitIdentifierList(this, state);
    }

}
