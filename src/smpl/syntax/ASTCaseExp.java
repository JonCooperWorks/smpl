/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTCaseExp extends ASTArithExp<ASTCaseClauseList> {

	public ASTCaseExp(final ASTCaseClauseList clauseList) {
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
