/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTExpression<ChildrenType extends ASTNode<?>> extends
	ASTStatement<ChildrenType> {

    public ASTExpression(final String nm) {
	super(nm);
    }

    @SafeVarargs
    public ASTExpression(final String nm, final ChildrenType... c) {
	super(nm, c);
    }

    public ASTExpression(final String nm, final ArrayList<ChildrenType> c) {
	super(nm, c);
    }
}
