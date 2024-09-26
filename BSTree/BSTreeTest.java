/**
 * Unit test cases for the implementation of a Binary Search Tree.
 *
 * @author Amelia Do
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class BSTreeTest<E>
{
	/**
	 * the tree to use for testing
	 */
	private BSTree<Integer> tree;
	private BSTree<Integer> tree2;

	// returns a tree loaded with the given items
	static BSTree<Integer> load(Integer... items)
	{
		IntComparator compare = new IntComparator();
		BSTree<Integer> tree = new BSTree<Integer>(compare);
		for (Integer value : items) {
			tree.add(value);
		}
		return tree;
	}

	@Test
	public void test_addLoop()
	{
		// testing empty
		tree = load( );
		tree.addLoop( 50 );
		assertEquals( tree.toString(), "[50]" );

		// testing single
		tree = load( 50 );
		tree.addLoop( 70 );
		assertEquals( tree.toString(), "[50 70]" );

		tree = load( 50 );
		tree.addLoop( 30 );
		assertEquals( tree.toString(), "[50 30]" );

		// testing multi
		tree = load( 5, 6, 7, 8, 10, 20 );
		tree.addLoop( 28 );
		assertEquals( tree.toString(), "[5 6 7 8 10 20 28]" );
		tree.addLoop( 4 );
		assertEquals( tree.toString(), "[5 4 6 7 8 10 20 28]" );
		tree.addLoop( 3 );
		assertEquals( tree.toString(), "[5 4 6 3 7 8 10 20 28]" );
		tree.addLoop( 2 );
		assertEquals( tree.toString(), "[5 4 6 3 7 2 8 10 20 28]" );
	}

	@Test
	public void test_maxValueLoop()
	{
		// testing empty
		tree = load( );
		try { tree.maxValueLoop(); fail(); }
		catch (NoSuchElementException e) { /* Test passed */ }
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load( 2 );
		assertTrue( tree.maxValueLoop() == 2 );
		assertFalse( tree.maxValueLoop() == 12 );
		assertEquals( tree.toString(), "[2]" );

		// testing multi
		tree = load( 5, 7, 6, 9, 8, 10, 20 );
		assertTrue( tree.maxValueLoop() ==  20 );
		assertFalse( tree.maxValueLoop() ==  1 );
		assertFalse( tree.maxValueLoop() ==  30 );
		assertFalse( tree.maxValueLoop() ==  8 );
		assertEquals( tree.toString(), "[5 7 6 9 8 10 20]" );
	}

	@Test
	public void test_containsLoop()
	{
		// testing empty
		tree = load( );
		assertFalse( tree.containsLoop(2) );

		// testing single
		tree = load( 2 );
		assertTrue( tree.containsLoop(2) );
		assertEquals( tree.toString(), "[2]" );

		// testing multi
		tree = load( 5, 7, 6, 9, 8, 10, 20 );
		assertTrue( tree.containsLoop(5) );
		assertTrue( tree.containsLoop(8) );
		assertTrue( tree.containsLoop(20) );
		assertFalse( tree.containsLoop(25) );
		assertFalse( tree.containsLoop(4) );
		assertEquals( tree.toString(), "[5 7 6 9 8 10 20]" );
	}

	@Test
	public void test_contains()
	{
		// testing empty
		tree = load( );
		assertFalse( tree.contains(2) );

		// testing single
		tree = load( 2 );
		assertTrue( tree.contains(2) );
		assertEquals( tree.toString(), "[2]" );

		// testing multi
		tree = load( 5, 7, 6, 9, 8, 10, 20 );
		assertTrue( tree.contains(5) );
		assertTrue( tree.contains(6) );
		assertTrue( tree.contains(20) );
		assertFalse( tree.contains(1) );
		assertFalse( tree.contains(40) );
		assertEquals( tree.toString(), "[5 7 6 9 8 10 20]" );
	}

	@Test
	public void test_remove()
	{
		// testing empty
		tree = load( );
		assertFalse( tree.remove(3) );
		assertEquals( tree.toString(), "[]" );

		// testing single 
		tree = load( 8 );
		assertFalse( tree.remove(3) );
		assertEquals( tree.toString(), "[8]" );
		assertTrue( tree.remove(8) );
		assertEquals( tree.toString(), "[]" );

		// testing multi 
		tree = load( 5, 7, 6, 9, 8, 10, 20 );
		assertFalse( tree.remove(12) );
		assertEquals( tree.toString(), "[5 7 6 9 8 10 20]" );

		assertTrue( tree.remove(6) );
		assertEquals( tree.toString(), "[5 7 9 8 10 20]" );

		assertTrue( tree.remove(20) );
		assertEquals( tree.toString(), "[5 7 9 8 10]" );

		assertTrue( tree.remove(9) );
		assertEquals( tree.toString(), "[5 7 8 10]" );

		tree = load( 70, 65, 120, 115, 109, 118, 105, 112, 119, 103, 106, 110 );
		assertTrue( tree.remove(109));
		assertEquals( tree.toString(), "[70 65 120 115 106 118 105 112 119 103 110]" );

		assertTrue( tree.remove(119));
		assertEquals( tree.toString(), "[70 65 120 115 106 118 105 112 103 110]" );

		assertTrue( tree.remove(103));
		assertEquals( tree.toString(), "[70 65 120 115 106 118 105 112 110]" );

		tree = load( 5, 4, 7, 3, 6, 9, 2, 8, 10, 1, 20 );
		assertTrue( tree.remove(10) );
		assertEquals( tree.toString(), "[5 4 7 3 6 9 2 8 20 1]" );

		assertTrue( tree.remove(3) );
		assertEquals( tree.toString(), "[5 4 7 2 6 9 1 8 20]" );
	}

	@Test
	public void test_maxValue() {
		// testing empty
		tree = load( );
		try { tree.maxValue(); fail(); }
		catch (NoSuchElementException e) { /* Test passed */ }
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load( 2 );
		assertTrue( tree.maxValue() == 2 );
		assertEquals( tree.toString(), "[2]" );

		// testing multi
		tree = load( 5, 7, 6, 9, 8, 10, 20 );
		assertFalse( tree.maxValue() ==  30 );
		assertFalse( tree.maxValue() ==  5 );
		assertTrue( tree.maxValue() ==  20 );
		assertEquals( tree.toString(), "[5 7 6 9 8 10 20]" );
	}

	@Test
	public void test_add()
	{
		// testing empty
		tree = load( );
		tree.add( 50 );
		assertEquals( tree.toString(), "[50]" );

		// testing single
		tree = load( 50 );
		tree.add( 70 );
		assertEquals( tree.toString(), "[50 70]" );

		tree = load( 50 );
		tree.add( 30 );
		assertEquals( tree.toString(), "[50 30]" );

		// testing multi
		tree = load( 5, 6, 7, 8, 10, 20 );
		tree.add( 28 );
		assertEquals( tree.toString(), "[5 6 7 8 10 20 28]" );
		tree.add( 4 );
		assertEquals( tree.toString(), "[5 4 6 7 8 10 20 28]" );
		tree.add( 3 );
		assertEquals( tree.toString(), "[5 4 6 3 7 8 10 20 28]" );
		tree.add( 2 );
		assertEquals( tree.toString(), "[5 4 6 3 7 2 8 10 20 28]" );
	}

	@Test
	public void test_isEmpty()
	{
		tree = load(  );
		assertTrue( tree.isEmpty() );

		tree = load( 2, 3, 5 );
		assertFalse( tree.isEmpty() );
	}

	@Test
	public void test_preorder() 
	{
		// testing empty
		tree = load( );
		StringVisitor<Integer> visitor = new StringVisitor<Integer>();
		tree.preorder( visitor );
		assertEquals( visitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load( 6 );
		visitor = new StringVisitor<Integer>();
		tree.preorder( visitor );
		assertEquals( visitor.getValue(), "[6]" );
		assertEquals( tree.toString(), "[6]" );

		// testing multi
		tree = load( 5, 3, 10, 2, 4, 7, 12, 6, 8 );
		visitor = new StringVisitor<Integer>();
		tree.preorder( visitor );
		assertEquals( visitor.getValue(), "[5 3 2 4 10 7 6 8 12]" );
		assertEquals( tree.toString(), "[5 3 10 2 4 7 12 6 8]" );
	}
	
	@Test
	public void test_inorder() 
	{
		// testing empty
		tree = load( );
		StringVisitor<Integer> visitor = new StringVisitor<Integer>();
		tree.inorder( visitor );
		assertEquals( visitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load( 6 );
		visitor = new StringVisitor<Integer>();
		tree.inorder( visitor );
		assertEquals( visitor.getValue(), "[6]" );
		assertEquals( tree.toString(), "[6]" );

		// testing multi
		tree = load( 5, 3, 10, 2, 4, 7, 12, 6, 8 );
		visitor = new StringVisitor<Integer>();
		tree.inorder( visitor );
		assertEquals( visitor.getValue(), "[2 3 4 5 6 7 8 10 12]" );
		assertEquals( tree.toString(), "[5 3 10 2 4 7 12 6 8]" );
	}
	
	@Test
	public void test_postorder() 
	{
		// testing empty
		tree = load( );
		StringVisitor<Integer> visitor = new StringVisitor<Integer>();
		tree.postorder( visitor );
		assertEquals( visitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load( 6 );
		visitor = new StringVisitor<Integer>();
		tree.postorder( visitor );
		assertEquals( visitor.getValue(), "[6]" );
		assertEquals( tree.toString(), "[6]" );

		// testing multi
		tree = load( 5, 3, 10, 2, 4, 7, 12, 6, 8 );
		visitor = new StringVisitor<Integer>();
		tree.postorder( visitor );
		assertEquals( visitor.getValue(), "[2 4 3 6 8 7 12 10 5]" );
		assertEquals( tree.toString(), "[5 3 10 2 4 7 12 6 8]" );
	}
	
	@Test
	public void test_equals() 
	{
		// Testing 1st case: this == other
    	tree = load( 2, 5, 8 ); 
    	assertTrue(tree.equals(tree));
    	
    	// Testing 2nd case: other == null || this.getClass() != other.getClass()
    	tree = load( 2, 5, 8 );
    	tree2 = null; 
    	assertFalse(tree.equals(tree2));
    	
    	tree = load( 2, 5, 8 ); 
    	String str = "hello";
    	assertFalse(tree.equals(str));
    	
    	// Testing empty list 
    	tree = load();
    	tree2 = load(); 
    	assertTrue(tree.equals(tree2));
    	
    	tree = load();
    	tree2 = load( 2, 3, 5 ); 
    	assertFalse(tree.equals(tree2));
    	
    	tree = load( 2, 3, 5 );
    	tree2 = load(); 
    	assertFalse(tree.equals(tree2));
    	
    	// Testing single-element list 
    	tree = load( 7 );
    	tree2 = load( 7 );
    	assertTrue(tree.equals(tree2));
    	
    	tree = load( 7 );
    	tree2 = load( 8 );
    	assertFalse(tree.equals(tree2));
    	
    	tree = load( 7 );
    	tree2 = load( 7, 8, 9 );
    	assertFalse(tree.equals(tree2));
    	
    	tree = load( 7 );
    	tree2 = load( 8, 9, 7 );
    	assertFalse(tree.equals(tree2));
    	
    	tree = load( 7, 8, 9 );
    	tree2 = load( 7 );
    	assertFalse(tree.equals(tree2));
    	
    	tree = load( 8, 9, 7 );
    	tree2 = load( 7 );
    	assertFalse(tree.equals(tree2));
    	
    	// Testing multi-elements list 
    	tree = load( 1, 5, 7, 2, 9 );
    	tree2 = load( 1, 5, 7, 2, 9 );
    	assertTrue(tree.equals(tree2));
    	
    	tree = load( 1, 5, 7, 2, 9 );
    	tree2 = load( 1, 5, 8, 2, 9 );
    	assertFalse(tree.equals(tree2));
    	
    	tree = load( 1, 5, 7, 2, 9 );
    	tree2 = load( 2, 5, 7, 2, 9 );
    	assertFalse(tree.equals(tree2));
    	
    	tree = load( 1, 5, 7, 2, 9 );
    	tree2 = load( 1, 5, 7, 2, 12, 15 );
    	assertFalse(tree.equals(tree2));
	}
	
	@Test
	public void test_clone() 
	{
		// Testing empty
		tree = load( );
		BSTree<Integer> tree3 = (BSTree<Integer>) tree.clone();
		assertTrue(tree.equals(tree3));
		tree3.add(30);
		assertFalse(tree.equals(tree3));
		
		// Testing single 
		tree = load( 5 );
		tree3 = (BSTree<Integer>) tree.clone();
		assertEquals(tree3.toString(), "[5]");
		
		// Testing multi-elements list 
		tree = load( 1, 5, 7, 2, 9 );
    	tree3 = (BSTree<Integer>) tree.clone();
    	assertEquals(tree3.toString(), "[1 5 2 7 9]");
    	assertTrue(tree.equals(tree3));
    	
	}
	
	@Test
	public void test_CountRangeVisitor()
	{
		// Testing empty list 
		tree = load( );
	    CountRangeVisitor<Integer> visitor = new CountRangeVisitor<>(3, 8, new IntComparator());
	    tree.preorder(visitor);
	    assertTrue( visitor.getValue() == 0 );
	    assertEquals( tree.toString(), "[]" );
	    
	    tree = load( );
	    visitor = new CountRangeVisitor<>(0, 0, new IntComparator());
	    tree.preorder(visitor);
	    assertTrue( visitor.getValue() == 0 );
	    assertEquals( tree.toString(), "[]" );
	    
	    // Testing single element list 
	    tree = load( 5 );
	    visitor = new CountRangeVisitor<>(2, 10, new IntComparator());
	    tree.preorder(visitor);
	    assertTrue( visitor.getValue() == 1 );
	    assertEquals( tree.toString(), "[5]" );
	    
	    tree = load( 5 );
	    visitor = new CountRangeVisitor<>(5, 5, new IntComparator());
	    tree.preorder(visitor);
	    assertTrue( visitor.getValue() == 1 );
	    assertEquals( tree.toString(), "[5]" );
	    
	    tree = load( 5 );
	    visitor = new CountRangeVisitor<>(10, 20, new IntComparator());
	    tree.preorder(visitor);
	    assertTrue( visitor.getValue() == 0 );
	    assertEquals( tree.toString(), "[5]" );

		// Testing multi-elements list 
		tree = load( 5, 3, 10, 2, 4, 7, 12, 6, 8 );
	    visitor = new CountRangeVisitor<>(3, 8, new IntComparator());
	    tree.preorder(visitor);
	    assertTrue( visitor.getValue() == 6 );
	    assertEquals( tree.toString(), "[5 3 10 2 4 7 12 6 8]" );
	    
	    tree = load( 5, 3, 10, 2, 4, 7, 12, 6, 8 );
	    visitor = new CountRangeVisitor<>(12, 20, new IntComparator());
	    tree.preorder(visitor);
	    assertTrue( visitor.getValue() == 1 );
	    assertEquals( tree.toString(), "[5 3 10 2 4 7 12 6 8]" );
	    
	    tree = load( 5, 3, 10, 2, 4, 7, 12, 6, 8 );
	    visitor = new CountRangeVisitor<>(16, 20, new IntComparator());
	    tree.preorder(visitor);
	    assertTrue( visitor.getValue() == 0 );
	    assertEquals( tree.toString(), "[5 3 10 2 4 7 12 6 8]" );
	}
	
	@Test
	public void test_rebuildPreorder() 
	{
		Integer[] items = { 5, 3, 10, 2, 4, 7, 12, 6, 8 };
		IntComparator comp = new IntComparator();
		
		BSTree<Integer> myTree = new BSTree<>(items, comp);
		assertEquals( myTree.toString(), "[5 3 10 2 12 4 6 7 8]" );
	}
	
	@Test 
	public void test_toStringLevel() 
	{
		tree = load( );
		assertEquals( tree.toStringLevel(), "[]" );
		
		tree = load( 5 );
		assertEquals( tree.toStringLevel(), "[5]" );
		
		tree = load( 5, 3, 10, 2, 4, 7, 12, 6, 8 );
		assertEquals( tree.toStringLevel(), "[5 3 10 2 4 7 12 6 8]" );
	}
}