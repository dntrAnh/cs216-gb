/**
 * Implementation of Entry class for CHashMap
 * 
 * @param K the key for the entry 
 * @param V the value for the entry
 */
public class Entry<K, V> {
	
    K key; 		// key of the entry 
    V value;	// value of the entry
    
    /**
     * Constructor of the entry of a key and a value 
     * @param key key of the entry
     * @param value value of the entry 
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * String representation of the entry 
     * @return a string of entry in the format of "key:value"
     */
    public String toString() {
    	return key + ":" + value;
    }
}