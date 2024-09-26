/**
 * Implementation of Chaining Probing HashMap
 * @author Amelia Do 
 */
import java.util.Iterator;
import java.util.LinkedList;

public class CHashMap<K, V> {
	private LinkedList<Entry<K, V>>[] map;
	private int size;
	private double loadFactor; 

	/**
	 * Constructor: to create a map with initial capacity and load factor  
	 * @param initialCapacity the given initial capacity to create the HashMap with 
	 * @param loadFactor 
	 */
	public CHashMap(int initialCapacity, double loadFactor) {
		size = 0;
		this.loadFactor = loadFactor;
		map = (LinkedList<Entry<K, V>>[]) new LinkedList[initialCapacity];
		for (int i = 0; i < initialCapacity; i++) {
			map[i] = new LinkedList<Entry<K, V>>();
		}
	}

	/**
	 * Determine whether the map is empty or not
	 * @return true if the hashmap is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Determine the size of the hashmap
	 * @return the size (number of entries) of the hashmap
	 */
	public int size() {
		return size;
	}

	/**
	 * Input the new set of entry of key and value into the hashmap 
	 * @param key the key of the entry
	 * @param value the value of the entry 
	 * @return the previous entry associated with the key or null if the key was not in the map 
	 */
	public V put(K key, V value) {
		int index = key.hashCode() % map.length;
		LinkedList<Entry<K, V>> list = map[index]; 

		for (Entry<K, V> item : list) {
			K itemKey = item.key;
			if (itemKey.equals(key)) {
				V oldValue = item.value;
				item.value = value;
				return oldValue;
			}
		}
		list.add(new Entry<>(key, value));
		size++;
		
		if (size >= loadFactor * map.length) {
			rehash();
		}
		
		return null;
	}

	/**
	 * Return the value that associated with the given key 
	 * @param key the given key to search the value 
	 * @return the value associated with the key given or null if the key was not in the map 
	 */
	public V get(K key) {
		int index = key.hashCode() % map.length;
		LinkedList<Entry<K, V>> list = map[index]; 

		for (Entry<K, V> item : list) {
			K itemKey = item.key;
			if (itemKey.equals(key)) {
				V value = item.value;
				return value;
			}
		}
		return null;
	}

	/**
	 * Deletes the entry with the given key 
	 * @param key the given key where the value be deleted
	 * @return the value associated with the key given or null if the key was not in the map 
	 */
	public V remove(K key) {
		int index = key.hashCode() % map.length;
		LinkedList<Entry<K, V>> list = map[index]; 

		Iterator<Entry<K, V>> iterator = list.iterator();

		while (iterator.hasNext()) {
			Entry<K, V> entry = iterator.next();
			K entryKey = entry.key;
			if (entryKey.equals(key)) {
				V oldValue = entry.value;
				size--;
				iterator.remove();
				return oldValue;
			}
		}
		return null;
	}

	/**
	 * Clear the entries of the HashMap
	 */
	public void clear() {
		for (LinkedList<Entry<K,V>> list : map) {
			list.clear();
		}
		size = 0;
		loadFactor = 0;
	}

	/**
	 * Determines whether the map contains the element with given key
	 * @param key the given key to search in the map
	 * @return true if the map contains element with given key, false otherwise
	 */
	public boolean containsKey(K key) {
		int index = key.hashCode() % map.length;

		for (Entry<K, V> item : map[index]) {
			K itemKey = item.key;
			if (itemKey.equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines whether the map contains the element with given value
	 * @param value the given value to search in the map
	 * @return true if the map contains element with given value, false otherwise
	 */
	public boolean containsValue(V value) {
		for (LinkedList<Entry<K,V>> list : map) {
			for (Entry<K, V> item : list) {
				if (item.value == value) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * To expand the map storage once it meet the capacity of the map
	 */
	private void rehash() {
		LinkedList<Entry<K, V>>[] tmpMap = map;
		map = (LinkedList<Entry<K, V>>[]) new LinkedList[map.length * 2];
		
		for (LinkedList<Entry<K,V>> list : tmpMap) {
			for (Entry<K, V> item : list) {
				K tmpK = item.key;
				V tmpV = item.value;
				this.put(tmpK, tmpV);
			}
		}
	}

	/**
	 * Returns a string representation of the map
	 */
	public String toString() {
		String str = "";
		for (LinkedList<Entry<K, V>> list : map) {
			str += list + " ";
		}
		str = str.replaceAll(",", "");
		str = size + ":[" + str.trim() + "]";

		return str;
	}

}

