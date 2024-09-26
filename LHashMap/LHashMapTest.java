import static org.junit.Assert.*;

import org.junit.Test;

public class LHashMapTest {
	
    private LHashMap<MyString, Integer> map;
	
	private static MyString ms(String str)
	{
	    return new MyString(str);
	}

	@Test
	public void test() {
		map = new LHashMap<>(10, 0.5);
		
		// put, remove, size testing 
		assertEquals( map.toString(), "0:[E E E E E E E E E E]" );
		
		assertTrue( map.put( ms("january"), 31 ) == null );
		assertEquals( map.toString(), "1:[E E E E E E E january:31 E E]" );
		
		assertTrue( map.put( ms("february"), 28 ) == null );
		assertEquals( map.toString(), "2:[E E E E E E E january:31 february:28 E]" );
		
		assertTrue( map.put( ms("march"), 5 ) == null );
		assertEquals( map.toString(), "3:[E E E E E march:5 E january:31 february:28 E]" );
		
		assertEquals( map.size(), 3 );
		
		assertTrue( map.put( ms("ten"), 3 ) == null );
		assertEquals( map.toString(), "4:[E E E ten:3 E march:5 E january:31 february:28 E]" );
		
		assertTrue( map.remove( ms("january") ) == 31 );
		assertEquals( map.toString(), "3:[E E E ten:3 E march:5 E D february:28 E]" );
		
		assertTrue( map.remove( ms("march") ) == 5 );
		assertEquals( map.toString(), "2:[E E E ten:3 E D E D february:28 E]" );
		
		// get testing 
		assertTrue( map.get( ms("february") ) == 28 );
		assertFalse( map.get( ms("february") ) == 5 );
		
		// contains testing 
		assertTrue( map.containsKey(ms("february")) );
		assertFalse( map.containsKey(ms("october")) );
		assertFalse( map.containsKey(ms("march")) );
		assertTrue( map.containsValue(28) );
		assertFalse( map.containsValue(70) );
		
		// size testing 
		assertEquals( map.size(), 2 );
		
		map.put( ms("seven"), 5 );
		assertEquals( map.size(), 3 );
		
		assertTrue( map.put( ms("seven"), 6 ) == 5 );
		assertEquals( map.size(), 3 );
		
		map.put( ms("hello"), 7 );
		assertEquals( map.size(), 3 );
		
		map.put( ms("october"), 7 );
		assertEquals( map.size(), 4 );
		
		map.put( ms("abilities"), 9 );
		assertEquals( map.size(), 15 );

		
		// clear, isEmpty & size testing 
		map.clear();
//		assertEquals( map.toString(), "0:[E E E E E E E E E E E E E E E]" );
		assertTrue( map.isEmpty() );
		assertEquals( map.size(), 0 );
	}

}
