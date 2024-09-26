import java.io.IOException;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Implementation of HuffmanTree by 
 * @author Amelia Do 
 */

public class HuffmanTree {
	public HNode root; 

	/**
	 * builds a Huffman Tree from the given characters and 
	 * their corresponding frequencies.
	 * @param frequencies - given characters and frequencies to build Huffman Tree from
	 */
	public HuffmanTree(TreeMap<Character, Integer> frequencies)
	{
		PriorityQueue<HNode> queue = new PriorityQueue<>(new HNodeComparator());
		for (Entry<Character, Integer> entry : frequencies.entrySet()) {
			queue.add(new HNode(entry.getKey(), entry.getValue()));
		}

		while ( queue.size() > 1 ) {
			HNode left = queue.poll();
			HNode right = queue.poll();
			HNode merged = new HNode(left, right);
			queue.add(merged);
		}
		root = queue.poll();
	}

	/**
	 * returns the binary encoding of the given symbol as 
	 * a string of '0' and '1' characters
	 * @param symbol - the symbol to be encoded 
	 * @return the binary encoding of the given symbol 
	 */
	public String encodeLoop(char symbol) 
	{
		String str = "";
		HNode curr = root;

		while ( !curr.isLeaf() ) {
			if ( curr.right.contains(symbol) ) {
				curr = curr.right;
				str += 1;
			}
			else if ( curr.left.contains(symbol) ) {
				curr = curr.left;
				str += 0;
			}
		}

		if ( curr.isLeaf() && !curr.contains(symbol) ) {
			return null;
		} 
		else {
			return str;
		}
	}

	/**
	 * returns the binary encoding of the given symbol as 
	 * a string of '0' and '1' characters
	 * @param symbol - the symbol to be encoded 
	 * @return the binary encoding of the given symbol 
	 */
	public String encode(char symbol) 
	{
		return encode(symbol, root);	
	}

	/**
	 * returns the binary encoding of the given symbol as 
	 * a string of '0' and '1' characters
	 * @param symbol - the symbol to be encoded 
	 * @return the binary encoding of the given symbol 
	 */
	public String encode(char symbol, HNode curr)
	{
		if (curr == null) {
			return null;
		}
	    if (curr.isLeaf()) {
	    	return curr.contains(symbol) ? "" : null;
	    }

	    String leftPath = encode(symbol, curr.left);
	    if (leftPath != null) {
	    	return "0" + leftPath;
	    }

	    String rightPath = encode(symbol, curr.right);
	    if (rightPath != null) {
	    	return "1" + rightPath;
	    }

	    return null;
	}

	/**
	 * Returns the symbol that corresponds to the given code 
	 * @param code - the given code to be decoded 
	 * @return the symbol that corresponds to the given code 
	 */
	public char decode(String code)
	{
		HNode curr = root;
	    for (char bit : code.toCharArray()) {
	        curr = (bit == '0') ? curr.left : curr.right;
	        if (curr == null) {
	        	return '\0';
	        }
	    }
	    if (curr != null && curr.isLeaf()) {
	    	return curr.getSymbol();
	    }
	    else {
	    	return '\0';
	    }
	}

	/**
	 * Writes the individual bits of the binary encoding of the given 
	 * symbol to the given bit stream
	 * @param symbol - given symbol to write the code for
	 * @param stream - given stream to be written onto
	 * @throws IOException 
	 */
	public void writeCode(char symbol, BitOutputStream stream) throws IOException 
	{
		String binaryEncoding = encode(symbol);
		if (binaryEncoding == null) {
			return; 
		} 
		else {
			for (char bit : binaryEncoding.toCharArray()) {
				stream.writeBit(bit == '1' ? 1 : 0);
			}
		}
	}



	/** 
	 * Reads from the given stream the individual bits of the 
	 * binary encoding of the next symbol 
	 * @param stream - given stream to be read from 
	 * @return the given stream the individual bits of the binary encoding 
	 */
	public char readCode(BitInputStream stream) throws IOException 
	{
		if ( root == null ) {
			return '\0';
		}

		HNode curr = root; 
		while ( !curr.isLeaf() ) {
			int bit = stream.readBit(); 
			curr = (bit == 0) ? curr.left : curr.right; 
		}

		return curr.getSymbol(); 
	}
}


