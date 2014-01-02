/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTDynamicBindingsExp extends
		ASTExp<ASTIdentifierList> {

	public ASTDynamicBindingsExp(final ASTIdentifierList l) {
		super("dynamic_identifiers", l);
	}

	public ASTIdentifierList getDynamicIdentifiers() {
		return getChild(0);
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitDynamicBindingExp(this, state);
	}

}
