/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTNoOp extends ASTExp<ASTNode<?>> {

	public ASTNoOp() {
		super("no_operation");
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitNoOp(this, state);
	}

}
