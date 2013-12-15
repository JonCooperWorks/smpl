/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;

/**
 * 
 */
public abstract class ASTRelationalExpression<ChildrenType extends ASTNode<?>>
	extends ASTExpression<ChildrenType> {

    public ASTRelationalExpression(final String nm) {
	super(nm);
    }

    @SafeVarargs
    public ASTRelationalExpression(final String nm, final ChildrenType... c) {
	super(nm, c);
    }

    public ASTRelationalExpression(final String nm,
	    final ArrayList<ChildrenType> c) {
	super(nm, c);
    }

}
