/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTPrintLnExp extends ASTExp<ASTExp<?>> {
	public ASTPrintLnExp(final ASTExp<?> expression) {
		super("print_ln_expresssion", expression);
	}

	public ASTExp<?> getExpression() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitPrintLn(this, state);
	}
}
