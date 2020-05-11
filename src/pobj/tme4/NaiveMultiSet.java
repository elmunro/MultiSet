package pobj.tme4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class NaiveMultiSet<T> implements MultiSet<T> {
	private ArrayList<T> list;
	private NaiveMultiSetComparator comparer = new NaiveMultiSetComparator();

	public NaiveMultiSet() {
		list = new ArrayList<T>();
	}

	public NaiveMultiSet(Collection<T> col) {
		list = new ArrayList<T>(col);
	}

	@Override
	public boolean add(T e, int count) {
		for (int i = 0; i < count; i++)
			list.add(e);
		return true;
	}

	@Override
	public boolean add(T e) {
		list.add(e);
		return true;
	}

	@Override
	public boolean remove(Object e) {
		list.remove(e);
		return true;
	}

	@Override
	public boolean remove(Object e, int count) {
		for (int i = 0; i < count; i++) {
			list.remove(e);
		}
		return true;
	}

	@Override
	public int count(T o) {
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(o))
				cnt++;
		}
		return cnt;
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<T> elements() {
		return new ArrayList<T>(new HashSet<T>(list));
	}

	@Override
	public Comparator<T> getComparer() {
		return comparer;
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	public class NaiveMultiSetComparator implements Comparator<T> {
		@Override
		public int compare(T arg0, T arg1) {
			if (count(arg0) > count(arg1))
				return 1;
			else if (count(arg0) == count(arg1))
				return 0;
			else
				return -1;
		}

	}
}
