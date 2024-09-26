/**
 * Implementation of Entry class for LHashMap
 * 
 * @param K the key for the entry 
 * @param V the value for the entry
 */
public class Entry<K, V> {
	
    K key; 		// key of the entry 
    V value;	// value of the entry
    
    /**
     * Constructor of the entry of a key and a value 
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public Entry() {
	}

	/**
     * String representation of the entry 
     * @return a string of entry in the format of "key:value"
     */
    public String toString() {
    	return key + ":" + value;
    }
}