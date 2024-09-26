/**
 * Implementation of HuffmanTree Test by 
 * @author Amelia Do 
 */

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Test;

public class HuffmanTreeTest {

	static HuffmanTree load(Character[] characters, Integer[] frequencies) 
	{
        TreeMap<Character, Integer> frequencyMap = new TreeMap<>();
        for (int i = 0; i < characters.length; i++) {
            frequencyMap.put(characters[i], frequencies[i]);
        }
        return new HuffmanTree(frequencyMap);
    }

	
	@Test
	public void test_HuffmanTree() 
	{
        Character[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Integer[] frequencies = {20, 55, 20, 5, 30, 35, 50, 45};
        
        HuffmanTree tree = load(chars, frequencies);
        
        String[] encodedStrings = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            encodedStrings[i] = tree.encode(chars[i]);
            assertFalse(encodedStrings[i].isEmpty());
        }
        
        for (int i = 0; i < encodedStrings.length; i++) {
            char decodedChar = tree.decode(encodedStrings[i]);
            assertTrue(decodedChar == chars[i]);
        }
	}
	
	@Test
	public void test_HNode() 
	{
		// test symbols, freq, isLeaf
		HNode node = new HNode('a', 10);
        assertEquals("a", node.symbols);
        assertEquals(10, node.freq);
        assertTrue(node.isLeaf());
        assertEquals( node.toString(), "a:10" );
        node = new HNode('b', 15);
        assertEquals( node.toString(), "b:15" );
        
        // test contains
        node = new HNode('d', 20);
        assertTrue(node.contains('d'));
        assertFalse(node.contains('a'));
        
        // test getSymbol
        HNode leafNode = new HNode('e', 30);
        HNode internalNode = new HNode(new HNode('f', 35), new HNode('g', 40));
        
        assertEquals('e', leafNode.getSymbol());
        assertEquals('\0', internalNode.getSymbol());
	}

}
