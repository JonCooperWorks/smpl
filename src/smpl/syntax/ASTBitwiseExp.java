/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTBitwiseExp<ChildrenType extends ASTNode<?>>
		extends ASTExp<ChildrenType> {

	public ASTBitwiseExp(final String nm) {
		super(nm);
	}

	@SafeVarargs
	public ASTBitwiseExp(final String nm, final ChildrenType... c) {
		super(nm, c);
	}

	public ASTBitwiseExp(final String nm, final ArrayList<ChildrenType> c) {
		super(nm, c);
	}

}
