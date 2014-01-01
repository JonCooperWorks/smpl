/**
 * 
 */
package smpl.syntax;

import smpl.semantics.Visitor;

/**
 * 
 */
public class ASTAliasDefinitionList extends ASTNode<ASTAliasDefn> {

	public ASTAliasDefinitionList() {
		super("alias_definition_list");
	}

	public ASTAliasDefinitionList(final ASTAliasDefn... exp) {
		super("identifier_list", exp);
	}

	/* Chainable Function */
	public ASTAliasDefinitionList add(final ASTAliasDefn exp) {
		addChild(exp);
		return this;
	}

	public ASTAliasDefn getAliasDefinition(final int i) {
		return getChild(i);
	}

	public Iterable<ASTAliasDefn> getAliasDefinitions() {
		return getChildren();
	}

	@Override
	public <S, T> T visit(final Visitor<S, T> visitor, final S state)
			throws ASTException {
		return visitor.visitAliasDefinitionList(this, state);
	}

}
