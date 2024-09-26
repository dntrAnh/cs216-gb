/**
 * Implementation of Linear Probing HashMap
 * @author Amelia Do 
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LHashMap<K, V> {
	private Entry<K, V>[] map; 		// initial array
	private int size; 				// size of the map
	private double loadFactor;		// loadFactor uses for hashing 
	private Entry<K, V> empty; 		// marker of empty for certain entries
	private Entry<K, V> deleted;	// marker of deleted for certain entries

	/**
	 * Constructor: to create a map with initial capacity and load factor  
	 * @param initialCapacity the given initial capacity to create the HashMap with 
	 * @param loadFactor limited capacity of the map
	 */
	public LHashMap(int initialCapacity, double loadFactor) {
		map = (Entry<K, V>[]) new Entry[initialCapacity];
		this.loadFactor = loadFactor;

		deleted = new Entry<K, V>(null, null);
		empty = new Entry<K, V>(null, null);

		for (int i = 0; i < initialCapacity; i++) {
			map[i] = empty;
		}

		size = 0;
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
		
		for (int i = 0; i < map.length; i++) { 
			Entry<K, V> entry = map[index];
			K entryKey = entry.key; 
			
			if (entry != deleted && entryKey.equals(key)) {
				V oldValue = entry.value;
				entry.value = value; 
				return oldValue; 
			}
			else {
				if (entry == empty) {
					map[index] = new Entry<K, V>(key, value);
					size++; 
					break;
				}
			}
			index = (index + 1) % map.length;
		}
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

		while (map[index] != empty) {
			if (map[index] != deleted) {
				K entryKey = map[index].key;
				if (entryKey.equals(key)) { 
					V value = map[index].value;
					return value;
				}
			}
			index = (index + 1) % map.length;
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

		while (map[index] != empty) {
			if (map[index] != deleted) {
				K entryKey = map[index].key;

				if (entryKey.equals(key)) {
					V oldValue = map[index].value;
					map[index] = deleted;
					size--;

					return oldValue;
				}
			}
			index = (index + 1) % map.length;
		}
		return null;
	}

	/**
	 * Clear the entries of the HashMap
	 */
	public void clear() {
		for (int i = 0; i < map.length; i++) {
			map[i] = empty;
		}
		size = 0;
	}

	/**
	 * Determines whether the map contains the element with given key
	 * @param key the given key to search in the map
	 * @return true if the map contains element with given key, false otherwise
	 */
	public boolean containsKey(K key) {
		int index = key.hashCode() % map.length;

		while (map[index] != empty) {
			if (map[index] != deleted) {
				K entryKey = map[index].key;
				if (entryKey.equals(key)) { 
					return true;
				}
			}
			index = (index + 1) % map.length;
		}

		return false;
	}

	/**
	 * Determines whether the map contains the element with given value
	 * @param value the given value to search in the map
	 * @return true if the map contains element with given value, false otherwise
	 */
	public boolean containsValue(V value) {
		Iterator<V> iter = this.iterator();
		while (iter.hasNext()) {
			V entryValue = iter.next();
			if (entryValue.equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	private void rehash() {
		Entry<K, V>[] tmpMap = new Entry[map.length];

		for (int i = 0; i < map.length; i++) {
			tmpMap[i] = map[i];
		}

		map = (Entry<K, V>[]) new Entry[map.length * 2];
		for (int i = 0; i < map. length; i++) {
			map[i] = empty;
		}
		for (Entry<K, V> entry : tmpMap) {
			K tmpK = entry. key;
			V tmpV = entry. value;
			this.put(tmpK, tmpV);
		}
	}

	/**
	 * Returns a string representation of the map
	 */
	public String toString() {
		String str = size + ":[";
		for (Entry<K, V> item : map) {
			if (item == empty) {
				str += "E ";
			} 
			else if (item == deleted) {
				str += "D ";
			} 
			else {
				str += item + " ";
			}
		}
		str = str.trim() + "]";
		return str;
	}

	/**
	 * Returns an iterator of values stored in the map
	 * @return set of values in the map 
	 */
	public Iterator<V> iterator() {
		return new LHashMapIterator();
	}

	private class LHashMapIterator implements Iterator<V> {

		private int currIndex;

		public LHashMapIterator() {
			currIndex = 0;
		}

		@Override
		public boolean hasNext() {
			while (currIndex < map.length) {
				if (map[currIndex] != deleted && map[currIndex] != empty) {
					return true;
				}
				currIndex++;
			}
			return false; 
		}

		@Override
		public V next() {
			if (hasNext()) {
				V valueNext = map[currIndex].value;
				currIndex++;
				return valueNext;
			}
			return null;
		}

	}
}

