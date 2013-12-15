/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

/**
 * 
 */
public class SMPLObject {
    private String mTypeName;

    public SMPLObject(final String typeName) {
	super();
	mTypeName = typeName;
    }

    protected void setTypeName(final String name) {
	mTypeName = name;
    }

    public String toRepr() {
	return mTypeName + "(" + toString() + ")";
    }

    public boolean isEqualTo(final SMPLObject obj) {
	return equals(obj);
    }

}
