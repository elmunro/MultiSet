package pobj.tme5.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;
import pobj.tme5.NaiveMultiSet;

public class NaiveMultiSetTest {
	
	private MultiSet<String> m;
	
	@Before
	public void before() {
		MultiSet<String> base = new NaiveMultiSet<>();
		m = new MultiSetDecorator<>(base);
	}
	
	@Test 
	public void testAdd0() {
		m.add("a",0);
		assertEquals(0, m.count("a"));
	}
	
	@Test 
	public void testAdd1() {
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testAdd2() {
		m.add("a");
		m.add("a",-1);
	}
	
	@Test 
	public void testRemove0() {
		m.remove("a",0);
		assertEquals(0, m.count("a"));
	}
	
	@Test 
	public void testRemove1() {
		m.add("a", 6);
		int cnt = m.count("a");
		m.remove("a",2);
		assertEquals(cnt-2, m.count("a"));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		m.remove("a");
		m.remove("a",-1);
	}
	
	
	@Test 
	public void testSize() {
		int size = m.size();
		m.add("a", 6);
		m.add("b", 4);
		assertEquals(size+10, m.size());
	}
	
	@Test 
	public void testClear() {
		m.add("a", 6);
		m.add("b", 4);
		m.clear();
		assertEquals(0, m.size());
	}
	
	@Test 
	public void testToString() {
		m.add("a", 6);
		m.add("b", 4);
		assertEquals("aaaaaabbbb", m.toString());
	}
	
	@Test
	public void testMulti() {
		m.add("a", 6);
		m.add("b", 4);
		m.add("c", 4);
		assertEquals(6, m.count("a"));
		assertEquals(4, m.count("b"));
		assertEquals(4, m.count("c"));
		
		m.remove("a", 3);
		m.remove("b", 1);
		m.remove("c", 1);
		
		assertEquals(3, m.count("a"));
		assertEquals(3, m.count("b"));
		assertEquals(3, m.count("c"));
	}
	
}
