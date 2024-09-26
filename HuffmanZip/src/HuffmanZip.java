import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Implementation of HuffmanZip by 
 * @author Amelia Do 
 */

public class HuffmanZip 
{
	/**
	 * Encodes the text file with the given name
	 * @param filename - the given filename to be encoded 
	 * @throws IOException 
	 */
	public static void encode(String filename) throws IOException 
	{
		TreeMap<Character, Integer> frequencyMap = new TreeMap<>();

		// 1. read the given text file one character at time to build a map of character frequencies
		FileInputStream inputStream = new FileInputStream(filename);
		int c;
		while ((c = inputStream.read()) != -1) {
			char character = (char) c;
			frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
		}
		inputStream.close();

		// 2. build the Huffman Tree
		HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);

		// 3. save the map of frequencies to the binary file
		String outputFilename = filename.substring(0, filename.length() - 4) + ".hz";

		BitOutputStream outputStream = new BitOutputStream( outputFilename ); 
		outputStream.writeObject(frequencyMap);

		// 4. again read the given text file one character at time and use the Huffman Tree 
		// to write the binary code of each character to the binary file
		inputStream = new FileInputStream(filename);
		while ( (c = inputStream.read()) != -1 ) {
			char character = (char) c;
			huffmanTree.writeCode(character, outputStream);
		}

		inputStream.close();
		outputStream.close();
	} 


	/**
	 * Decodes the text file with the given name 
	 * @param filename - the given filename to be decoded 
	 * @throws IOException 
	 */
	public static void decode(String filename) throws IOException, ClassNotFoundException 
	{
		String outputFilename = filename.substring(0, filename.length() - 3) + ".huz";

		// 1. read the map from the binary file and build the Huffman Tree
		BitInputStream inputStream = new BitInputStream( filename );

		TreeMap<Character, Integer> frequencyMap = (TreeMap<Character, Integer>) inputStream.readObject();

		HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);

		// 2. use the Huffman Tree to extract each character from the binary file and 
		// immediately write the character to the text file
		FileOutputStream outputStream = new FileOutputStream(outputFilename);
		while ( inputStream.hasNext() ) {
			char character = huffmanTree.readCode(inputStream);
			outputStream.write(character);
		}

		inputStream.close();
		outputStream.close();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException  
	{
		String command = args[0]; 
		if (command.equals("-encode")) {
			encode(args[1]);
		}
		else if (command.equals("-decode")) {
			decode(args[1]);
		}
	}
}
