/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTListExpression<ChildrenType extends ASTNode<?>>
		extends ASTExp<ChildrenType> {

	public ASTListExpression(final String nm) {
		super(nm);
	}

	@SafeVarargs
	public ASTListExpression(final String nm, final ChildrenType... c) {
		super(nm, c);
	}

	public ASTListExpression(final String nm, final ArrayList<ChildrenType> c) {
		super(nm, c);
	}
}
