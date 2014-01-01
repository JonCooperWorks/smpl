/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTVectorExpression<ChildrenType extends ASTNode<?>>
		extends ASTExp<ChildrenType> {

	public ASTVectorExpression(final String nm) {
		super(nm);
	}

	@SafeVarargs
	public ASTVectorExpression(final String nm, final ChildrenType... c) {
		super(nm, c);
	}

	public ASTVectorExpression(final String nm, final ArrayList<ChildrenType> c) {
		super(nm, c);
	}

}
