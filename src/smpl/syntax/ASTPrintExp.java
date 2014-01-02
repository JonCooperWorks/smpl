/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTPrintExp extends ASTExp<ASTExp<?>> {
	public ASTPrintExp(final ASTExp<?> expression) {
		super("print_expresssion", expression);
	}

	public ASTExp<?> getExpression() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitPrint(this, state);
	}
}
