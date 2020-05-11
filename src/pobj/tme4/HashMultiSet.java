package pobj.tme4;

import java.util.*;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	private Map<T, Integer> map;
	private int size;
	public final HashMultiSetComparer comparer = new HashMultiSetComparer();

	public HashMultiSet() {
		map = new HashMap<T, Integer>();
		size = 0;
	}

	public HashMultiSet(Collection<T> col) {
		map = new HashMap<T, Integer>();
		for (T t : col) {
			add(t);
		}
	}

	@Override
	public boolean add(T e, int count) {
		int n = count(e);
		map.put(e, n + count);
		size += count;
		return true;
	}

	@Override
	public boolean add(T e) {
		int n = count(e);
		map.put(e, n + 1);
		size++;
		return true;
	}

	@Override
	public boolean remove(Object e) {
		@SuppressWarnings("unchecked")
		T t = (T) e;
		if (map.containsKey(t)) {
			int n = count(t);
			if (n == 1) {
				map.remove(t);
				return true;
			}
			map.put(t, n - 1);
			size--;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object e, int count) {
		@SuppressWarnings("unchecked")
		T t = (T) e;
		if (map.containsKey(t)) {
			int n = count(t);
			if (n < count) {
				map.remove(t);
				return true;
			}
			map.put(t, n - count);
			size -= count;
			return true;
		}
		return false;
	}

	@Override
	public int count(T o) {
		if (map.containsKey(o))
			return (int) map.get(o);
		else
			return 0;
	}

	@Override
	public void clear() {
		map.clear();
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMultiSetIterator(this);
	}

	@Override
	public List<T> elements() {
		ArrayList<T> list = new ArrayList<>(map.keySet());
		// for(T key : map.keySet()) {
		// list.add(key);
		// }
		return list;
	}

	public HashMultiSetComparer getComparer() {
		return comparer;
	}

	public class HashMultiSetComparer implements Comparator<T> {
		@Override
		public int compare(T arg0, T arg1) {
			return map.get(arg0).compareTo(map.get(arg1));
		}

	}

	private class HashMultiSetIterator implements Iterator<T> {
		private int count = 0;
		private T elt;
		private Iterator<Map.Entry<T, Integer>> ite;

		public HashMultiSetIterator(HashMultiSet<T> hms) {
			ite = hms.map.entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return ite.hasNext() || count > 0;
		}

		@Override
		public T next() {
			if (count == 0) {
				Map.Entry<T, Integer> entry = ite.next();
				elt = entry.getKey();
				count = entry.getValue();
			}
			count--;
			return elt;
		}
	}
}
