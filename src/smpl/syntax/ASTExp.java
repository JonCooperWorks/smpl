/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTExp<ChildrenType extends ASTNode<?>> extends
		ASTStatement<ChildrenType> {

	public ASTExp(final String nm) {
		super(nm);
	}

	@SafeVarargs
	public ASTExp(final String nm, final ChildrenType... c) {
		super(nm, c);
	}

	public ASTExp(final String nm, final ArrayList<ChildrenType> c) {
		super(nm, c);
	}
}
