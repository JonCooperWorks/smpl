package smpl.syntax;

import smpl.semantics.Visitor;

public class ASTLogicalNotExpression extends
	ASTBitwiseExpression<ASTExpression<?>> {

    public ASTLogicalNotExpression(final ASTExpression<?> operand) {
	super("not_expression", operand);
    }

    public ASTExpression<?> getOperand() {
	return getChild(0);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitLogicalNot(this, state);
    }

}
