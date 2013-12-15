/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTStatementSequence extends ASTNode<ASTStatement<?>> {

    public ASTStatementSequence() {
	super("statement_sequence");
    }

    public ASTStatementSequence(final ASTStatement<?>... exp) {
	super("statement_sequence", exp);
    }

    /* Chainable Function */
    public ASTStatementSequence add(final ASTStatement<?> exp) {
	addChild(exp);
	return this;
    }

    public ASTStatement<?> getStatement(final int i) {
	return getChild(i);
    }

    public Iterable<ASTStatement<?>> getStatements() {
	return getChildren();
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitStatementSequence(this, state);
    }

}
