/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTLazyEvaluationExpression extends
		ASTExpression<ASTExpression<?>> {
	public ASTLazyEvaluationExpression(final ASTExpression<?> expression) {
		super("lazy_evaluate_procedure", expression);
	}

	public ASTExpression<?> getExpression() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitLazyEvaluation(this, state);
	}
}
