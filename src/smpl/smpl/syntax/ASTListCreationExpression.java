package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTListCreationExpression extends
	ASTListExpression<ASTExpressionList> {

    public ASTListCreationExpression() {
	super("emty_list_creation_expression", new ASTExpressionList());
    }

    public ASTListCreationExpression(final ASTExpressionList exps) {
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
