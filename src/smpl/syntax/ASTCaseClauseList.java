/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCaseClauseList extends ASTNode<ASTCaseClause> {

    public ASTCaseClauseList() {
	super("case_clause_list");
    }

    public ASTCaseClauseList(final ASTCaseClause... exp) {
	super("case_clause_list", exp);
    }

    /* Chainable Function */
    public ASTCaseClauseList add(final ASTCaseClause exp) {
	addChild(exp);
	return this;
    }

    public ASTCaseClause getClause(final int i) {
	return getChild(i);
    }

    public Iterable<ASTCaseClause> getClauses() {
	return getChildren();
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitCaseClauseList(this, state);
    }

}
