/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTReadIntegerExp extends ASTExp<ASTNode<?>> {

	public ASTReadIntegerExp() {
		super("read_integer_command");
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitReadInteger(this, state);
	}

}
