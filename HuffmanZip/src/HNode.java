/**
 * Implementation of HNode by
 * @author Amelia Do 
 */

public class HNode {
    
	HNode left;			// left-child of the HNode
    HNode right;		// right-child of the HNode 
    public String symbols; 		// Storing symbols in leaves
    public int freq; 			// Cumulative frequency

    /**
     * Constructor of the HNode 
     * @param c - character c as symbol of the HNode
     * @param f - frequency f as frequency/ data of the HNode
     */
    public HNode(char c, int f) 
    {
        this.symbols = String.valueOf(c);
        this.freq = f;
        this.left = null;
        this.right = null;
    }

    /**
     * Creates a node with the given left and right children.
     * @param left - left-child of the HNode created
     * @param right - right-child of the HNode created 
     */
    public HNode(HNode left, HNode right) 
    {
        this.left = left;
        this.right = right;
        this.symbols = left.symbols + right.symbols;
        this.freq = left.freq + right.freq;
    }

    /**
     * Determine if the node is a leaf
     * @return true if the node is a leaf 
     */
    public boolean isLeaf() 
    {
        return left == null && right == null;
    }

    /**
     * Determine if the node contains the given character 
     * @param ch - character to be checked if the node contains such 
     * @return Returns true if this node contains the given character
     */
    public boolean contains(char ch) 
    {
        return symbols.indexOf(ch) >= 0;
    }

    /**
     * Returns the symbol stored in this node
     * @return the symbol stored in this node, '\0' if it is not a leaf 
     */
    public char getSymbol() 
    {
        if ( isLeaf() ) {
            return symbols.charAt(0);
        }
        return '\0'; 
    }
    
    public String toString()
    {
		return symbols + ":" + freq;
    }
}



