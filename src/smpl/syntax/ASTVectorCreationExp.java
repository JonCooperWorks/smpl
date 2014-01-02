package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTVectorCreationExp extends
		ASTListExpression<ASTExpressionList> {

	public ASTVectorCreationExp() {
		super("vector_creation_expression", new ASTExpressionList());
	}

	public ASTVectorCreationExp(final ASTExpressionList exps) {
		super("vector_creation_expression", exps);
	}

	public ASTExpressionList getVectorExpressions() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitVectorCreation(this, state);
	}

}
