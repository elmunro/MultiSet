package pobj.tme5;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class MultiSetDecorator<T> implements MultiSet<T> {
	private MultiSet<T> decorated;
	
	public MultiSetDecorator(MultiSet<T> decor) {
		decorated = decor;
	}
	
	private boolean isConsistent() {
		int cnt = 0;
		Iterator<T> iterator = iterator();
		while(iterator.hasNext()) {
			iterator.next();
			cnt++;
		}
		if(decorated.size() != cnt)
			return false;
		return true;
	}

	@Override
	public boolean add(T e, int count) {
		boolean res = decorated.add(e,count);
		assert isConsistent();
		return res;
	}
	
	@Override
	public boolean add(T e) {
		boolean res = decorated.add(e);
		assert isConsistent();
		return res;
	}

	@Override
	public boolean remove(Object e) {
		boolean res = decorated.remove(e);
		assert isConsistent();
		return res;
	}

	@Override
	public boolean remove(Object e, int count) {
		boolean res = decorated.remove(e, count);
		assert isConsistent();
		return res;
	}

	@Override
	public int count(T o) {
		return decorated.count(o);
	}

	@Override
	public void clear() {
		decorated.clear();
	}

	@Override
	public int size() {
		return decorated.size();
	}

	@Override
	public List<T> elements() {
		return decorated.elements();
	}

	@Override
	public Comparator<T> getComparer() {
		return decorated.getComparer();
	}
	
	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}
	
	@Override
	public String toString() {
		return decorated.toString();
	}

}
