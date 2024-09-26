/**
 * Implementation of Binary Search Tree by 
 * @author Amelia Do
 */

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BSTree<E> implements Cloneable
{
	public class Node 
	{
		public E data; 		// data of the node 
		public Node left;	// left-child of the node 
		public Node right;	// right-child of the node 
		public Node parent;	// parent of the node 
		
		/**
		 * The given node is assigned data, parent, and left-right children (if there exist)
		 * @param data
		 */
		public Node (E data) 
		{
			this.data = data;
			left = null;
			right = null; 
			parent = null;
		}
	}

	private Node root;
	private Comparator<E> comparator;
	
	public BSTree(Comparator<E> compare) 
	{
		root = null;
		comparator = compare;
	}

	/**
	 * Return the position of the root
	 * @return the position of the root 
	 */
	public Node getRoot() 
	{
		return root;
	}
	
	/**
	 * Add the given item to the tree.
	 * @param item item to be added into the tree
	 */
	void addLoop ( E item )
	{
		if (root == null) {
			root = new Node( item );
		}
		
		else {
			Node newNode = new Node ( item );
			Node curr = root;
			while ( curr != null ) {
				if ( comparator.compare(item, curr.data) > 0 ) {
					if (curr.right != null) {
						curr = curr.right;
					}
					else {
						curr.right = newNode;
						newNode.parent = curr;
						return;
					}
				}
				else if ( comparator.compare(item, curr.data) < 0 ) {
					if (curr.left != null) {
						curr = curr.left;
					}
					else {
						curr.left = newNode;
						newNode.parent = curr;
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Determine if the tree is empty
	 * @return true if the tree is empty
	 */
	public boolean isEmpty() 
	{
		return root == null;
	}

	/**
	 * Determine the largest value of the tree 
	 * @return the largest value of the tree
	 */
	public E maxValueLoop() throws NoSuchElementException
	{
		Node node = findMaxNodeLoop( root );
		if ( node == null ) {
			throw new NoSuchElementException();
		}
		else {
			E data = node.data;
			return data;
		}
	}
	
	/**
	 * Determine the node of largest value in the sub-tree rooted at the given node node.
	 * @param node given node to find the largest value of
	 * @return the node the node of largest value in the sub-tree rooted at the given node node.
	 */
	private Node findMaxNodeLoop ( Node node )
	{
		if (node == null) {
			return null;
		}
		
		while ( node.right != null ) {
			node = node.right;
		}
		return node;
	}
	
	/**
	 * Determines if the tree contains the given item 
	 * @param item the given item to see if the tree does contain
	 * @return true if the tree contain the item 
	 */
	public boolean containsLoop ( E item ) 
	{
		if ( findNodeLoop( root, item ) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Finds the node in the sub-tree rooted at the given node node that contains the given item.
	 * @param node the given node where the sub-tree is located 
	 * @param item the given item to be search for 
	 * @return the node in the sub-tree rooted from the given node node that contains the given item 
	 */
	private Node findNodeLoop ( Node node, E item ) 
	{
		Node foundNode = null;
		if (root == null) {
			return null;
		}
		
		Node curr = root;
		while ( comparator.compare(item, curr.data) != 0 ) {
			if ( comparator.compare(item, curr.data) > 0 ) {
				if (curr.right != null) {
					curr = curr.right;
				}
				else {
					return null;
				}
			}
			else if ( comparator.compare(item, curr.data) < 0 ) {
				if (curr.left != null) {
					curr = curr.left;
				}
				else {
					return null;
				}
			}
		}
		if ( comparator.compare(item, curr.data) == 0 ) {
			foundNode = curr;
		}
		return foundNode;
	}
	
	/**
	 * Adds the given item to the tree
	 * @param item the given item to be added to the tree 
	 */
	public void add ( E item ) throws NoSuchElementException
	{
		if ( root == null ) {
			root = new Node ( item );
		}
		else {
		add( root, item );
		}
	}
	
	/**
	 * Adds the given item to the sub-tree rooted at the given curr node.
	 * @param curr the given curr node that the sub-tree rooted from
	 * @param item the given item to be added to the sub-tree from the curr node. 
	 */
	private void add ( Node curr, E item ) 
	{
		Node newNode = new Node( item );
		 if ( comparator.compare(item, curr.data) > 0 ) {
			if ( curr.right != null ) {
				add( curr.right, item );
			}
			else {
				curr.right = newNode;
				newNode.parent = curr;
				return;
			}
		}
		else if ( comparator.compare(item, curr.data) < 0 ) {
			if ( curr.left != null ) {
				add( curr.left, item );
			}
			else {
				curr.left = newNode;
				newNode.parent = curr;
				return;
			}
		}
	}
	
	/**
	 * Return the largest value of the tree
	 * @return the largest value in the tree
	 */
	public E maxValue() throws NoSuchElementException
	{
		if ( root == null ) {
			throw new NoSuchElementException();
		}
		Node node = findMaxNode( root );
		return node.data;
	}
	
	/**
	 * Determine the node of largest value in the sub-tree rooted at the given node curr. 
	 * @param curr given node to find the largest value of
	 * @return the node of largest value in the sub-tree rooted at the given node node.
	 */
	private Node findMaxNode(Node curr)
	{
		if ( curr.right == null ) {
			return curr;
		}
		else {
			return findMaxNode( curr.right );
		}
	}
	
	/**
	 * Determines if the tree contains the given item 
	 * @param item the item to be determined if the tree contains
	 * @return true if item found 
	 */
	public boolean contains(E item) {
		Node foundNode = findNode( root, item );
		if ( foundNode == null ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Finds the node in the sub-tree rooted at the given node curr that contains the given item.
	 * @param curr the given node where the sub-tree is located 
	 * @param item the given item to be search for 
	 * @return the node in the sub-tree rooted from the given node curr that contains the given item 
	 */
	private Node findNode(Node curr, E item) 
	{
		if ( curr == null ) {
			return null;
		}
		else if ( comparator.compare(item, curr.data) == 0 ) {
			return curr;
		}
		else if ( comparator.compare(item, curr.data) > 0 ) {
			return findNode( curr.right, item );
		}
		else {
			return findNode( curr.left, item );
		}
	}
	
	/**
	 * Removes the given item from the tree.
	 * @param item given item to be removed
	 * @return true if the item is removed. 
	 */
	public boolean remove(E item)
	{
		Node node = findNodeLoop( root, item );	
		
		if ( node == null ) {
			return false;
		}
		else if ( node.right == null || node.left == null ) {
			removeMissing(node);
			return true;
		}
		else { 
			removeHasBoth(node);
			return true;
		}
	}
	
	/**
	 * Removes the given node assuming it is missing one or both children.
	 * @param node given node to be removed
	 */
	private void removeMissing(Node node)
	{
		if (node == root) {
			root = null;
		}
		else if (node.right == null && node.left == null) {
			if ( comparator.compare(node.data, node.parent.data) >= 0 ) {
				node.parent.right = null;
			}
			else {
				node.parent.left = null;
			}
		}
		else if (node.right != null) {
			node.right.parent = node.parent;
			node.parent.right = node.right;
		}
		else {
			node.left.parent = node.parent;
			node.parent.left = node.left;	
		}
	}
	
	/**
	 * Removes the given node assuming it has exactly two children
	 * @param node the given node to be removed
	 */
	private void removeHasBoth(Node node)
	{
		Node maxNode = findMaxNodeLoop( node.left );
		E data = maxNode.data;
		removeMissing(maxNode);
		node.data = data;
	}
	
	/**
	 * Performs preorder traversal of the tree
	 * @param visitor given visitor to start traverse preorder through
	 */
	public void preorder(Visitor<E> visitor) {
	    preorder(visitor, root);
	}
	
	/**
	 * Performs preorder traversal of the tree rooted at the given node curr.
	 * @param visitor given visitor to start traverse preorder through
	 * @param curr given node to start the preorder traversal 
	 */
	private void preorder(Visitor<E> visitor, Node curr) 
	{
		if (curr == null) 
		{
			return;
		}
	    visitor.visit(curr.data);
	    preorder(visitor, curr.left);
	    preorder(visitor, curr.right);
	}
	
	/**
	 * Performs inorder traversal of the tree
	 * @param visitor given visitor to start traverse inorder through
	 */
	public void inorder(Visitor<E> visitor) 
	{
		inorder(visitor, root);
	}
	
	/**
	 * Performs inorde traversal of the tree rooted at the given node curr.
	 * @param visitor given visitor to start traverse inorder through
	 * @param curr given node to start the inorder traversal 
	 */
	private void inorder(Visitor<E> visitor, Node curr) 
	{
		if (curr == null) 
		{
			return;
		}
        inorder(visitor, curr.left);
        visitor.visit(curr.data);
        inorder(visitor, curr.right);
	}
	
	/**
	 * Performs postorder traversal of the tree
	 * @param visitor given visitor to start traverse postorder through
	 */
	public void postorder(Visitor<E> visitor)
	{
		postorder(visitor, root);
	}
	
	/**
	 * Performs postorde traversal of the tree rooted at the given node curr.
	 * @param visitor given visitor to start traverse postorder through
	 * @param curr given node to start the postorder traversal 
	 */
	private void postorder(Visitor<E> visitor, Node curr) 
	{
		if (curr == null) 
		{
			return;
		}
		postorder(visitor, curr.left);
		postorder(visitor, curr.right);
		visitor.visit(curr.data);
	}
	
	/**
	 * Compare this tree to other object 
	 * @return true if the given object is equal to the tree
	 */
	public boolean equals(Object other) 
	{
		if (this == other) { 
			return true;
		}
		else if (other == null || this.getClass() != other.getClass()) {  
			return false; 
		}
		else {
			BSTree<E> otherTree = (BSTree<E>) other;
			return equals(root, otherTree.root);
			
		}
	}
	
	/**
	 * Compare the 2 given nodes 
	 * @param root1 the given node 1
	 * @param root2 the given node 2
	 * @return true if the trees rooted at the given nodes are identical
	 */
	public boolean equals(Node root1, Node root2) 
	{
		if (root1 == null && root2 == null) {
			return true;
		}
		else if (root1 == null || root2 == null) {
			return false;
		}
		return ((root1.data == root2.data) && equals(root1.left, root2.left)
				&& equals(root1.right, root2.right));
	}
	
	/**
	 * @return a copy of this tree
	 */
	public Object clone()
	{
	    try {
	        BSTree<E> copy = (BSTree<E>) super.clone();
	        copy.comparator = this.comparator;
	        copy.root = copyTree(this.root);
	        return copy;
	    }
	    catch (CloneNotSupportedException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * Returns a copy of the tree rooted at the given node
	 * @param curr the given node to return the copy of the tree from
	 * @return the copy of the tree from the given node
	 */
	private Node copyTree(Node node) 
	{
	    if (node == null) {
	        return null;
	    }
	    Node newNode = new Node(node.data); 
	    newNode.left = copyTree(node.left);
	    newNode.right = copyTree(node.right);
	    return newNode;
	}

	
	/**
	 * Creates the tree from the given preorder array of items
	 * @param items An array of items to be inserted into the tree
	 * @param comp A comparator to define the order of the items
	 */
	public BSTree(E[] items, Comparator<E> comp)
	{
	    this.comparator = comp;
	    this.root = rebuildPreorder(items, 0, items.length - 1);
	}
	
	/**
	 * Creates a tree from the given preorder array of items
	 * @param items The array of items in preorder
	 * @param i The starting index in the array
	 * @param j The ending index in the array
	 * @return The root node of the rebuilt subtree
	 */
	Node rebuildPreorder(E[] items, int i, int j) 
	{
        if (i > j) 
        {
            return null;
        }

        Node node = new Node(items[i]);

        // If there is only one item, it's the root with no children
        if (i == j) 
        {
            return node;
        }

        int start;
        for (start = i + 1; start <= j; start++) {
            if (comparator.compare(items[start], node.data) > 0) {
                break;
            }
        }
        node.left = rebuildPreorder(items, i + 1, start - 1);
        node.right = rebuildPreorder(items, start, j);

        return node;
    }
	
	/**
	 * Returns an iterator for traversing the tree
	 * @return An iterator over the elements in the tree
	 */
	public Iterator<E> iterator() 
	{
		return new BSTreeIterator(this.root);
	}
	
	private class BSTreeIterator implements Iterator<E> {
		private Queue<Node> queue;

		public BSTreeIterator(BSTree<E>.Node root) {
			this.queue = new LinkedList<>();
            if (root != null) 
            {
                this.queue.offer(root);
            }
		}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		@Override
		public E next() {
			if (!hasNext()) 
			{
                throw new NoSuchElementException();
            }
            Node current = queue.poll();
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
            return current.data;
		}
	    
	}
	
	/**
	 * Return the string representation of the tree level-by-level and from left-to-right
	 * @return the string representation of the tree
	 */
	public String toString()
	{
	    return new BSTreeUtils<E>().toString( root );
	}
	
	/**
	 * Return the string representation of the tree level-by-level and from left-to-right
	 * @return the string representation of the tree
	 */
	public String toStringLevel() 
	{
		String tree = new String("");
		Iterator<E> iter = this.iterator();
		
		while ( iter.hasNext() ) 
		{
            E element = iter.next();
            tree += element.toString() + " ";
        }
		
        return "[" + tree.trim() + "]";
	}
}
