/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTArithExpression<ChildrenType extends ASTNode<?>>
		extends ASTExpression<ChildrenType> {

	public ASTArithExpression(final String nm) {
		super(nm);
	}

	@SafeVarargs
	public ASTArithExpression(final String nm, final ChildrenType... c) {
		super(nm, c);
	}

	public ASTArithExpression(final String nm, final ArrayList<ChildrenType> c) {
		super(nm, c);
	}

}
