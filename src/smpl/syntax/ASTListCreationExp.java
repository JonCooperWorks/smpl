package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTListCreationExp extends
		ASTListExpression<ASTExpressionList> {

	public ASTListCreationExp() {
		super("emty_list_creation_expression", new ASTExpressionList());
	}

	public ASTListCreationExp(final ASTExpressionList exps) {
		super("list_creation_expression", exps);
	}

	public ASTExpressionList getListExpressions() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitListCreation(this, state);
	}

}
