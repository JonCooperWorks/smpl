/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTAliasDefinitionList extends ASTNode<ASTAliasDefinition> {

	public ASTAliasDefinitionList() {
		super("alias_definition_list");
	}

	public ASTAliasDefinitionList(final ASTAliasDefinition... exp) {
		super("identifier_list", exp);
	}

	/* Chainable Function */
	public ASTAliasDefinitionList add(final ASTAliasDefinition exp) {
		addChild(exp);
		return this;
	}

	public ASTAliasDefinition getAliasDefinition(final int i) {
		return getChild(i);
	}

	public Iterable<ASTAliasDefinition> getAliasDefinitions() {
		return getChildren();
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitAliasDefinitionList(this, state);
	}

}
