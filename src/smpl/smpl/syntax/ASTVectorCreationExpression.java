package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTVectorCreationExpression extends
	ASTListExpression<ASTExpressionList> {

    public ASTVectorCreationExpression() {
	super("vector_creation_expression", new ASTExpressionList());
    }

    public ASTVectorCreationExpression(final ASTExpressionList exps) {
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
