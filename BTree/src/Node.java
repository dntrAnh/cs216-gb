import java.util.Comparator;
import java.util.LinkedList;

/**
 * Implementation of Node class 
 * @author ameliado
 */

public class Node<E> 
{
	LinkedList<E> data;
	LinkedList<Node<E>> children;
	Node<E> parent;
	private Comparator<E> comp;
	private int order;

	public Node(int theOrder, Comparator<E> theComp) {
		this.order = theOrder;
		this.comp = theComp;
		this.data = new LinkedList<>();
		this.children = new LinkedList<>();
		this.parent = null;
	}

	public Node(int theOrder, Comparator<E> theComp, Node<E> left, E item, Node<E> right) {
		this(theOrder, theComp);
		this.children.add(left);
		this.children.add(right);
		this.data.add(item);
		if (left != null) {
			left.parent = this;
		}
		if (right != null) {
			right.parent = this;
		}
	}

	public Node(int theOrder, Comparator<E> theComp, Node<E> theParent, LinkedList<E> theData, LinkedList<Node<E>> theChildren) {
		this.order = theOrder;
		this.comp = theComp;
		this.parent = theParent;
		this.data = theData;
		this.children = theChildren;
	}

	public boolean hasOverflow() {
		return data.size() > order;
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public Node<E> childToFollow(E item)
	{
		if (isLeaf()) {
			return null;
		}
		for (int i = 0; i < data.size(); i++) {
			int compare = comp.compare(item, data.get(i));
			if (compare < 0) {
				return children.get(i);
			}
		}
		return children.getLast();
	}

	public void leafAdd(E item)
	{
		int index = 0;
		while (index < data.size() && comp.compare(data.get(index), item) < 0) {
			index++;
		}
		data.add(index, item);
	}

	public void split()
	{
		int midIndex = order / 2;
		E midValue = data.get( midIndex );

		LinkedList<E> rightData = new LinkedList<>(data.subList( midIndex + 1, data.size() ));
		data.subList(midIndex, data.size()).clear();

		LinkedList<Node<E>> rightChildren = new LinkedList<>();
		if (!isLeaf()) {
			rightChildren = new LinkedList<>(children.subList(midIndex + 1, children.size()));
			children.subList(midIndex + 1, children.size()).clear();
		}

		Node<E> rightNode = new Node<>(order, comp, parent, rightData, rightChildren);
		if (parent != null) {
			int parentIdx = parent.children.indexOf(this);
			parent.data.add(parentIdx, midValue);
			parent.children.add(parentIdx + 1, rightNode);
		}
		else { 
			data.subList(midIndex, data.size()).clear();
			Node<E> newParent = new Node<>(order, comp, this, midValue, rightNode);
			parent = newParent;
			rightNode.parent = newParent;
		}

	}

	public boolean contains(E item)
	{
		return data.contains(item);
	}
}
