/**
 * 
 */
package smpl.semantics.SMPLValue.Objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 */
public class SMPLPair extends SMPLObject implements
	Iterable<SMPLValue<SMPLObject>>, Collection<SMPLValue<SMPLObject>>,
	List<SMPLValue<SMPLObject>> {

    private final ArrayList<SMPLValue<SMPLObject>> mStorageList;

    public SMPLPair() {
	super("pair");

	mStorageList = new ArrayList<SMPLValue<SMPLObject>>(2);
    }

    protected SMPLPair(final int size) {
	super("pair");

	mStorageList = new ArrayList<SMPLValue<SMPLObject>>(size);
    }

    @Override
    public SMPLValue<SMPLObject> get(final int i) {
	if (i < 0 || i > mStorageList.size())
	    return null;

	return mStorageList.get(i);
    }

    @Override
    public Iterator<SMPLValue<SMPLObject>> iterator() {
	return mStorageList.iterator();
    }

    @Override
    public int size() {
	return mStorageList.size();
    }

    @Override
    public boolean isEmpty() {
	return mStorageList.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
	return mStorageList.contains(o);
    }

    @Override
    public Object[] toArray() {
	return mStorageList.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
	return mStorageList.toArray(a);
    }

    @Override
    public boolean remove(final Object o) {
	return mStorageList.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
	return mStorageList.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends SMPLValue<SMPLObject>> c) {
	return mStorageList.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
	return mStorageList.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
	return mStorageList.retainAll(c);
    }

    @Override
    public void clear() {
	mStorageList.clear();
    }

    @Override
    public boolean add(final SMPLValue<SMPLObject> e) {
	return mStorageList.add(e);
    }

    @Override
    public boolean addAll(final int index,
	    final Collection<? extends SMPLValue<SMPLObject>> c) {
	return mStorageList.addAll(index, c);
    }

    @Override
    public SMPLValue<SMPLObject> set(final int index,
	    final SMPLValue<SMPLObject> element) {
	return mStorageList.set(index, element);
    }

    @Override
    public void add(final int index, final SMPLValue<SMPLObject> element) {
	mStorageList.add(index, element);
    }

    @Override
    public SMPLValue<SMPLObject> remove(final int index) {
	return mStorageList.remove(index);
    }

    @Override
    public int indexOf(final Object o) {
	return mStorageList.indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
	return mStorageList.lastIndexOf(o);
    }

    @Override
    public ListIterator<SMPLValue<SMPLObject>> listIterator() {
	return mStorageList.listIterator();
    }

    @Override
    public ListIterator<SMPLValue<SMPLObject>> listIterator(final int index) {
	return mStorageList.listIterator(index);
    }

    @Override
    public List<SMPLValue<SMPLObject>> subList(final int fromIndex,
	    final int toIndex) {
	return mStorageList.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
	return mStorageList.toString();
    }

    @Override
    public boolean isEqualTo(final SMPLObject obj) {
	if (obj instanceof SMPLPair) {
	    final SMPLPair obj2 = (SMPLPair) obj;

	    if (size() != obj2.size())
		return false;

	    int i = 0;
	    for (final SMPLValue<SMPLObject> obj3 : mStorageList) {
		final SMPLValue<SMPLObject> nextObj = obj2.get(i);
		if (!obj3.rawValue().isEqualTo(nextObj.rawValue()))
		    return false;
		i++;
	    }
	    return true;
	}
	return false;
    }
}
