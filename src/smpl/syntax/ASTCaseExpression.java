/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCaseExpression extends ASTArithExpression<ASTCaseClauseList> {

	public ASTCaseExpression(final ASTCaseClauseList clauseList) {
		super("case_expression", clauseList);
	}

	public ASTCaseClauseList getClauseList() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitCase(this, state);
	}
}
