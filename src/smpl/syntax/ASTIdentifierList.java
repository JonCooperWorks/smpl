/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTIdentifierList extends ASTNode<ASTIdent> {

    public ASTIdentifierList() {
	super("identifier_list");
    }

    public ASTIdentifierList(final ASTIdent... exp) {
	super("identifier_list", exp);
    }

    /* Chainable Function */
    public ASTIdentifierList add(final ASTIdent exp) {
	addChild(exp);
	return this;
    }

    public ASTIdent getIdentifier(final int i) {
	return getChild(i);
    }

    public Iterable<ASTIdent> getIdentifiers() {
	return getChildren();
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitIdentifierList(this, state);
    }

}
