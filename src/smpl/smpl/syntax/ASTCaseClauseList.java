/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCaseClauseList extends ASTNode<ASTCaseClasue> {

    public ASTCaseClauseList() {
	super("case_clause_list");
    }

    public ASTCaseClauseList(final ASTCaseClasue... exp) {
	super("case_clause_list", exp);
    }

    /* Chainable Function */
    public ASTCaseClauseList add(final ASTCaseClasue exp) {
	addChild(exp);
	return this;
    }

    public ASTCaseClasue getClause(final int i) {
	return getChild(i);
    }

    public Iterable<ASTCaseClasue> getClauses() {
	return getChildren();
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitCaseClauseList(this, state);
    }

}
