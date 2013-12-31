/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTIfThenElseExpression extends ASTExpression<ASTStatement<?>> {

	public ASTIfThenElseExpression(final ASTExpression<?> condition,
			final ASTStatement<?> consequence) {
		super("if_statement", condition, consequence, new ASTNoOperationNode());
	}

	public ASTIfThenElseExpression(final ASTExpression<?> condition,
			final ASTStatement<?> consequence, final ASTStatement<?> alternative) {
		super("if_statement", condition, consequence, alternative);
	}

	public ASTExpression<?> getConditionExpression() {
		return (ASTExpression<?>) getChild(0);
	}

	public ASTStatement<?> getConsequenceExpression() {
		return getChild(1);
	}

	public ASTStatement<?> getAlternativeExpression() {
		return getChild(2);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitIfThenElse(this, state);
	}

}
