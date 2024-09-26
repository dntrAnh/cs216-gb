import static org.junit.Assert.*;

import org.junit.Test;

public class CHashMapTest {

	private static MyString ms(String str)
	{
	  return new MyString(str);
	}

	private CHashMap<MyString, Integer> map;

	@Test
	public void test() {
		map = new CHashMap<>(12, 0.5);
		assertTrue( map.size() == 0);
		assertTrue( map.isEmpty() );
		
		// put, get, remove testing 
		assertEquals( map.toString(), "0:[[] [] [] [] [] [] [] [] [] [] [] []]" );
		assertTrue( map.put( ms("january"), 31 ) == null );
		assertEquals( map.toString(), "1:[[] [] [] [] [] [] [] [january:31] [] [] [] []]" );
		
		assertTrue( map.put( ms("february"), 28 ) == null );
		assertEquals( map.toString(), "2:[[] [] [] [] [] [] [] [january:31] [february:28] [] [] []]" );
		
		assertFalse( map.size() == 0);
		assertTrue( map.size() == 2);
		assertFalse( map.isEmpty() );
		
		assertTrue( map.put( ms("march"), 5 ) == null );
		assertEquals( map.toString(), "3:[[] [] [] [] [] [march:5] [] [january:31] [february:28] [] [] []]" );
		
		assertTrue( map.put( ms("march"), 15 ) == 5 );
		assertEquals( map.toString(), "3:[[] [] [] [] [] [march:15] [] [january:31] [february:28] [] [] []]" );
		
		assertTrue( map.put( ms("hello"), 15 ) == null );
		assertEquals( map.toString(), "4:[[] [] [] [] [] [march:15 hello:15] [] [january:31] [february:28] [] [] []]" );
		
		assertTrue( map.get( ms("march") ) == 15 );
		assertTrue( map.get( ms("hello") ) == 15 );
		assertFalse( map.get( ms("february") ) == 25 );
		assertTrue( map.get( ms("october") ) == null );
		
		assertTrue( map.remove( ms("january") ) == 31 );
		assertEquals( map.toString(), "3:[[] [] [] [] [] [march:15 hello:15] [] [] [february:28] [] [] []]" );
		
		assertTrue( map.remove( ms("march") ) == 15 );
		assertEquals( map.toString(), "2:[[] [] [] [] [] [hello:15] [] [] [february:28] [] [] []]" );
		
		assertTrue( map.remove( ms("february") ) == 28 );
		assertEquals( map.toString(), "1:[[] [] [] [] [] [hello:15] [] [] [] [] [] []]" );
		
		assertTrue( map.remove( ms("april") ) == null );
		assertEquals( map.toString(), "1:[[] [] [] [] [] [hello:15] [] [] [] [] [] []]" );
		
		// contains testing 
		assertTrue( map.containsKey( ms("hello")) );
		assertFalse( map.containsKey( ms("january")) );
		assertFalse( map.containsKey( ms("october")) );
		assertTrue( map.containsValue( 15 ) );
		assertFalse( map.containsValue( 28 ) );
		assertFalse( map.containsValue( 30 ) );
		
		// rehash testing 
		assertTrue( map.put( ms("cat"), 3 ) == null );
		assertEquals( map.toString(), "2:[[] [] [] [cat:3] [] [hello:15] [] [] [] [] [] []]" );
		assertTrue( map.put( ms("a"), 1) == null );
		assertEquals( map.toString(), "3:[[] [a:1] [] [cat:3] [] [hello:15] [] [] [] [] [] []]" );
		assertTrue( map.put( ms("be"), 2 ) == null );
		assertEquals( map.toString(), "4:[[] [a:1] [be:2] [cat:3] [] [hello:15] [] [] [] [] [] []]" );
		assertTrue( map.put( ms("dog"), 3 ) == null );
		assertEquals( map.toString(), "5:[[] [a:1] [be:2] [cat:3 dog:3] [] [hello:15] [] [] [] [] [] []]" );
		
		// clear testing 
		map.clear();
		assertTrue( map.isEmpty() );
		assertTrue( map.size() == 0);
	}
}
