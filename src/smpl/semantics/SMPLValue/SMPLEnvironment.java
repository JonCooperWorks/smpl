/**
 * 
 */
package smpl.semantics.SMPLValue;

import java.util.ArrayList;
import java.util.HashMap;

import smpl.semantics.SMPLValue.Objects.SMPLObject;
import smpl.semantics.SMPLValue.Objects.SMPLValue;

/**
 * 
 */
public class SMPLEnvironment {

	private final ArrayList<String> mDynamicKeys;
	private final HashMap<String, SMPLValue<SMPLObject>> mStorage;

	private final SMPLEnvironment mDynamicParent;
	private final SMPLEnvironment mStaticParent;

	public SMPLEnvironment(final SMPLEnvironment staticParent,
			final SMPLEnvironment dynamicParent) {
		mStaticParent = staticParent;
		mDynamicParent = dynamicParent;

		mDynamicKeys = new ArrayList<String>();
		mStorage = new HashMap<String, SMPLValue<SMPLObject>>();
	}

	public boolean isBounded(final String identifierName) {
		if (mStorage.containsKey(identifierName))
			return true;

		if (mStaticParent != null)
			return mStaticParent.isBounded(identifierName);

		return false;
	}

	public SMPLValue<SMPLObject> lookUp(final String identifierName) {

		if (mStorage.containsKey(identifierName))
			return mStorage.get(identifierName);

		if (mDynamicKeys.contains(identifierName)) {
			if (mDynamicParent != null)
				return mDynamicParent.lookUp(identifierName);
		} else if (mStaticParent != null)
			return mStaticParent.lookUp(identifierName);

		return null;
	}

	public void addBinding(final String identiferName,
			final SMPLValue<SMPLObject> obj) {
		mStorage.put(identiferName, obj);
	}

	public void addDynamicBinding(final String identifierName) {
		mDynamicKeys.add(identifierName);
	}

}
