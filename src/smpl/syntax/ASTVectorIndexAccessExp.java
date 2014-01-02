package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTVectorIndexAccessExp extends
		ASTVectorExpression<ASTExp<?>> {

	public ASTVectorIndexAccessExp(final ASTExp<?> index,
			final ASTExp<?> vector) {
		super("vector_index_expression", index, vector);
	}

	public ASTExp<?> getIndexExpression() {
		return getChild(0);
	}

	public ASTExp<?> getVectorExpression() {
		return getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitVectorIndexAccess(this, state);
	}

}
