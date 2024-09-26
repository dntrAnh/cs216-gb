/**
 * Unit test cases for the implementation of a Binary Search Tree.
 *
 * @author  Amelia Do
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class BTreeTest
{
	/**
	 * the tree to use for testing
	 */
	private BTree<Integer> tree;

	// returns a tree loaded with the given items
	static BTree<Integer> load(int order, Integer... items)
	{
		IntComparator compare = new IntComparator();
		BTree<Integer> tree = new BTree<Integer>(order, compare);
		for (Integer value : items) {
			tree.add(value);
		}
		return tree;
	}

	/**
	 * Test the add method with tree of order 2 
	 */
	@Test
	public void test_add_2()
	{
		// testing single tree of order 2
		tree = load( 2 );
		tree.add( 8 );
		assertEquals(tree.toString(), "[[2 8]]");
		assertEquals(tree.toStringSorted(), "[2 8]");


		// testing multi tree of order 2
		tree = load( 2, 5, 7, 10, 2, 3 );
		tree.add( 8 );
		assertEquals(tree.toString(), "[[7] [2 5] [8 10]]");
		assertEquals(tree.toStringSorted(), "[2 5 7 8 10]");

		tree = load( 20, 30, 40, 1, 3, 2 );
		tree.add( 6 );
		assertEquals(tree.toString(), "[[2 20] [1] [3 6] [30 40]]");
		assertEquals(tree.toStringSorted(), "[1 2 3 6 20 30 40]");
	}

	@Test
	public void test_add_4()
	{
		// testing empty of order 4
		tree = load( 2 );
		tree.add( 5 );
		assertEquals( tree.toString(), "[[2 5]]" );
		assertEquals( tree.toStringSorted(), "[2 5]" );

		// testing single tree of order 4
		tree = load( 4 );
		tree.add( 8 );
		assertEquals(tree.toString(), "[[4 8]]");
		assertEquals(tree.toStringSorted(), "[4 8]");

		// testing multi tree of order 4
		tree = load( 4, 5, 7, 10, 2, 3 );
		tree.add( 8 );
		assertEquals(tree.toString(), "[[5] [2 3 4] [7 8 10]]");
		assertEquals(tree.toStringSorted(), "2 3 5 7 8 10");
	}
}

