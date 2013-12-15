package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTVectorComprehensionExpression extends
	ASTVectorExpression<ASTExpression<?>> {

    public ASTVectorComprehensionExpression(final ASTExpression<?> vectorSize,
	    final ASTExpression<?> elementProcedue) {
	super("vector_comprehension_expression", vectorSize, elementProcedue);
    }

    public ASTExpression<?> getVectorSizeExpression() {
	return getChild(0);
    }

    public ASTExpression<?> getElementProcedureExpression() {
	return getChild(1);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitVectorComprehension(this, state);
    }

}
