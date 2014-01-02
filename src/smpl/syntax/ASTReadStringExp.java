/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTReadStringExp extends ASTExp<ASTNode<?>> {

	public ASTReadStringExp() {
		super("read_string_command");
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitReadString(this, state);
	}

}
