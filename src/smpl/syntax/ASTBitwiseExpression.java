/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTBitwiseExpression<ChildrenType extends ASTNode<?>>
		extends ASTExpression<ChildrenType> {

	public ASTBitwiseExpression(final String nm) {
		super(nm);
	}

	@SafeVarargs
	public ASTBitwiseExpression(final String nm, final ChildrenType... c) {
		super(nm, c);
	}

	public ASTBitwiseExpression(final String nm, final ArrayList<ChildrenType> c) {
		super(nm, c);
	}

}
