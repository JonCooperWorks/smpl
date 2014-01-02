/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTLazyEvaluationExp extends
		ASTExp<ASTExp<?>> {
	public ASTLazyEvaluationExp(final ASTExp<?> expression) {
		super("lazy_evaluate_procedure", expression);
	}

	public ASTExp<?> getExpression() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitLazyEvaluation(this, state);
	}
}
