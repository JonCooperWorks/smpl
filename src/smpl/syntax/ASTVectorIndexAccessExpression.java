package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTVectorIndexAccessExpression extends
		ASTVectorExpression<ASTExpression<?>> {

	public ASTVectorIndexAccessExpression(final ASTExpression<?> index,
			final ASTExpression<?> vector) {
		super("vector_index_expression", index, vector);
	}

	public ASTExpression<?> getIndexExpression() {
		return getChild(0);
	}

	public ASTExpression<?> getVectorExpression() {
		return getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitVectorIndexAccess(this, state);
	}

}
