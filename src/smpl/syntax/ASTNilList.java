/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTNilList extends ASTListCreationExp {

	public ASTNilList() {
		super();
		setName("nill");
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitNilList(this, state);
	}

}
