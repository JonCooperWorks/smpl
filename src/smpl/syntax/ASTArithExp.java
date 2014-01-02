/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTArithExp<ChildrenType extends ASTNode<?>>
		extends ASTExp<ChildrenType> {

	public ASTArithExp(final String nm) {
		super(nm);
	}

	@SafeVarargs
	public ASTArithExp(final String nm, final ChildrenType... c) {
		super(nm, c);
	}

	public ASTArithExp(final String nm, final ArrayList<ChildrenType> c) {
		super(nm, c);
	}

}
