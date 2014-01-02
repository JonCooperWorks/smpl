package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTVectorComprehensionExp extends
		ASTVectorExpression<ASTExp<?>> {

	public ASTVectorComprehensionExp(final ASTExp<?> vectorSize,
			final ASTExp<?> elementProcedue) {
		super("vector_comprehension_expression", vectorSize, elementProcedue);
	}

	public ASTExp<?> getVectorSizeExpression() {
		return getChild(0);
	}

	public ASTExp<?> getElementProcedureExpression() {
		return getChild(1);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitVectorComprehension(this, state);
	}

}
