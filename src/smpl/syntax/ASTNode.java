/**
 * 
 */
package smpl.syntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import smpl.semantics.Visitor;

/**
 * 
 */
public abstract class ASTNode<ChildrenType extends ASTNode<?>> {

    private final ArrayList<ChildrenType> mChildren;
    private String mNodeName;

    public ASTNode(final String nm) {
	this(nm, new ArrayList<ChildrenType>());
    }

    @SafeVarargs
    public ASTNode(final String nm, final ChildrenType... c) {
	this(nm, new ArrayList<ChildrenType>(Arrays.asList(c)));
    }

    public ASTNode(final String nm, final ArrayList<ChildrenType> c) {
	mNodeName = nm;
	mChildren = c;
    }

    /**
     * 
     * @return The name of this node
     */
    protected String getName() {
	return mNodeName;
    }

    protected void setName(final String name) {
	mNodeName = name;
    }

    protected ChildrenType getChild(final int index) {
	return mChildren.get(index);
    }

    protected void addChild(final ChildrenType child) {
	mChildren.add(child);
    }

    protected List<ChildrenType> getChildren() {
	return mChildren;
    }

    protected int getNumChildren() {
	return mChildren.size();
    }

    /**
     * Visit this node using the given visitor
     * 
     * @param <S>
     *            The type of the state to be used by the visitor
     * @param <T>
     *            The type of the return value produced by the visitor
     * @param visitor
     *            The visitor to be used to perform the visit
     * @param state
     *            The state to be passed to the visitor
     * @return The result of calling the visitor's visit method for this AST
     *         node
     * @throws ASTException
     * @throws CGException
     *             If anything goes wrong during the visit computation
     */
    public abstract <S, T> T visit(Visitor<S, T> visitor, S state)
	    throws ASTException;

    public String getTreeRepresentation() {
	return getTreeNodeRepresentation("");
    }

    protected String getTreeNodeRepresentation(final String indent) {
	final StringBuilder result = new StringBuilder(indent);
	result.append(mNodeName);
	if (!mChildren.isEmpty()) {
	    result.append(":\n");
	    for (final ChildrenType child : mChildren) {
		result.append(child.getTreeNodeRepresentation(indent + "    "));
		result.append("\n");
	    }
	}

	return result.toString();
    }

    @Override
    public String toString() {
	return getTreeRepresentation();
    }
}
