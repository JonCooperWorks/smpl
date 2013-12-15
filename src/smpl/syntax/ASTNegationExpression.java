/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTNegationExpression extends
	ASTBitwiseExpression<ASTExpression<?>> {

    public ASTNegationExpression(final ASTExpression<?> operand) {
	super("negation_expression", operand);
    }

    public ASTExpression<?> getOperand() {
	return getChild(0);
    }

    @Override
    public <S, T> T visit(final Visitor<S, T> visitor, final S state)
	    throws ASTException {
	return visitor.visitNegation(this, state);
    }
}
