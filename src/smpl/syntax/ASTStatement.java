/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTStatement<ChildrenType extends ASTNode<?>> extends
		ASTNode<ChildrenType> {

	public ASTStatement(final String nm) {
		super(nm);
	}

	@SafeVarargs
	public ASTStatement(final String nm, final ChildrenType... c) {
		super(nm, c);
	}

	public ASTStatement(final String nm, final ArrayList<ChildrenType> c) {
		super(nm, c);
	}
}
