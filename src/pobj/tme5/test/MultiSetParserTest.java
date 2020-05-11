package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.InvalidMultiSetFormat;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetParser;

public class MultiSetParserTest {

	@Before
	public void before() {
		
	}
	
	@Test 
	public void shortTest() throws InvalidMultiSetFormat{
		MultiSet<String> ms = MultiSetParser.parse("data/short.txt");
		assertEquals(17, ms.size());
		
		System.out.println(ms.toString());
	}
}
