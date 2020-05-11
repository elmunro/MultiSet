package pobj.tme5;

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

//	private boolean isConsistent() {
//		int cnt = 0;
//		for (T t : map.keySet()) {
//			Integer val = map.get(t);
//			if (val < 0)
//				return false;
//			cnt += val;
//		}
//		if(this.size() != cnt)
//			return false;
//	
//		return true;
//	}

	@Override
	public boolean add(T e, int count) {
		if (count < 0)
			throw new IllegalArgumentException("Can't add less than 0 object to collection");
		if(count == 0)
			return false;
		int n = count(e);
		map.put(e, n + count);
		size += count;
		//assert isConsistent();
		return true;
	}

	@Override
	public boolean add(T e) {
		int n = count(e);
		map.put(e, n + 1);
		size++;
		//assert isConsistent();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e) {
		T t;
		try {
			t = (T) e;
		} catch (ClassCastException cce) {
			throw new IllegalArgumentException();
		}
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
		//assert isConsistent();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e, int count) {
		T t;
		try {
			t = (T) e;
		} catch (ClassCastException cce) {
			throw new IllegalArgumentException();
		}
		if (count < 0)
			throw new IllegalArgumentException("Can't remove less than 0 object to collection");
		if (map.containsKey(t)) {
			int n = count(t);
			if (n < count) {
				map.remove(t, n);
				return true;
			}
			map.put(t, n - count);
			size -= count;
			return true;
		}
		//assert isConsistent();
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
	public List<T> elements() {
		ArrayList<T> list = new ArrayList<>(map.keySet());
		// for(T key : map.keySet()) {
		// list.add(key);
		// }
		return list;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T t : this) {
			sb.append(t.toString());
		}
		return sb.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMultiSetIterator(this);
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
