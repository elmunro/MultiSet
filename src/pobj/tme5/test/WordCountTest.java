package pobj.tme5.test;


import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
import pobj.tme5.NaiveMultiSet;
import pobj.tme5.WordCount;

public class WordCountTest {
	
	@Test
	public void testNaiveCount() {
		WordCount wc = new WordCount();
		MultiSet<String> base = new NaiveMultiSet<>();
		wc.main(base);
	}
	
	@Test
	public void testHashCount() {
		WordCount wc = new WordCount();
		MultiSet<String> base = new HashMultiSet<>();
		wc.main(base);
	}
}
