import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

public class CW76_HashMap_TreeMap_LinkedHashMap {
    public static void main(String[] args) {
        // LinkedHashMap and HashMap don't accept comparator, but TreeMap does. eg. new TreeMap<>(Comparator.reverseOrder());
        // PTR: In case of custom class, for LinkedHashMap and HashMap, make sure Class of key has appropriate hashCode() and equals() defintion. FYI, these methods come from Object class by default.
        // PTR: ForTreeMap, key's Class should be Comparable or a Comparator should be provided in definition of TreeMap. equals() and hashCode() are not necessary.

        /* put() */
        // PTR: map.put returns null or the prev. value if key is present.
        Map<Integer, String> map = new TreeMap<>((a, b) -> b - a); // ~Comparators.reverseOrder()
        map.put(1, "Doug");
        map.put(0, "Anna");
        map.put(3, "Zack");
        map.put(2, "Paul");
        /**
         * The following print as follows for different Map implementations:
         * - HashMap: {3=Zack, 2=Paul, 1=Doug, 0=Anna} // can be random subject to key's Class.
         * - LinkedHashMap: {1=Doug, 0=Anna, 3=Zack, 2=Paul} // maintains insertion order
         * - TreeMap: {3=Zack, 2=Paul, 1=Doug, 0=Anna} // sorted by comparator or key's Comparable impl.
         */
        /* get() / containKey() / containsValue() */
        System.out.println("Map (here treemap with id desc.): " + map);
        System.out.println("Value for key 2: " + map.get(2));
        System.out.println("Value for unknown key 99: " + map.get(99)); // returns null if key is not present.
        System.out.println("Does 99 gets inserted (like in cpp - mp[99])? " + map.containsKey(99));
        System.out.println("Does map contain value 'Jane'? " + map.containsValue("Jane"));

        /* remove() / remove(key, value) / size() / isEmpty() */
        map.remove(2);
        System.out.println("Map after removing key 2: " + map);
        map.remove(0, "Stark"); // removes conditionally // no effect if value doesn't match
        System.out.println("Key 0 not removed after conditional matching with 'Stark': " + map);
        map.remove(0, "Anna"); // removes conditionally if value matches
        System.out.println("Key 0 removed after conditionally matching value with 'Anna': " + map);
        System.out.println("Size: " + map.size());
        System.out.println("Is map empty?: " + map.isEmpty());
        // PTR: remove() doesn't throw exception if key doesn't exist, but it returns null.

        /* values() / keySet() : IMP: returns a Collection and a Set respectively */
        Collection<String> valuesCollection = map.values(); // focus on return type here - it's not a List but a Collection. Contrary, keySet() returns a Set.
        List<String> values = valuesCollection.stream().toList();
        System.out.println("Values in the map: " + values);
        
        Set<Integer> keyset = map.keySet();
        System.out.println("Keys in the map: " + keyset);

        System.out.println("Iterating over entrySet() = Set<Map.Entry>:");
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        for (Map.Entry<Integer, String> entry : entrySet) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        System.out.println("------------------");

        /* putIfAbsent() / computeIfPresent() / replace() / clear() */
        // putIfAbsent: Same as: if (!map.containsKey(4)) { map.put(4, "Ralph"); }
        System.out.println("putIfAbsent(4, 'Ralph') adds or returns existing key: " + map.putIfAbsent(4, "Ralph")); // returns old value and doesn't update if key already exists
        System.out.println("Try putting null value in map (5, null): " + map.put(5, null));
        System.out.println("Map after putting null value: " + map);
        map.putIfAbsent(5, "'null overriden in putIfAbsent. Generally it skips if key is found'");
        System.out.println("putIfAbsent overrides null value if key is found: " + map);

        map.computeIfPresent(5, (k, v) -> v == null ? "new value for 5" : v + " updated");
        System.out.println("Map after computeIfPresent on key 5: " + map);

        map.replace(1, "johnny"); // similar to put, but works only if key exists
        System.out.println("Map after replace(1, 'johnny'): " + map);

        map.replace(99, "non-existing key can't be replaced but put()");
        System.out.println("Trying to replace() non-existing key 99: " + map);

        map.clear();
        System.out.println("Map after clear: " + map);
        System.out.println("Size of map after clear: " + map.size());

        System.out.println("------------------");

        /* merge() : inc/dec and also adds if key not present */
        Map<Integer, Integer> freqMap = new HashMap<>();
        freqMap.put(1, 1);
        freqMap.put(2, 2);
        System.out.println("Frequency map: " + freqMap);
        System.out.println("Increment value of key 1 by 1: " + freqMap.merge(1, 1, Integer::sum)); // merge returns new value after merging
        System.out.println("Decrement value of key 2 by 1: " + freqMap.merge(2, -1, Integer::sum));
        System.out.println("Increment value of unknown key 99 by 1: " + freqMap.merge(99, 1, Integer::sum)); // merge returns new value after merging
        System.out.println("Frequency map final: " + freqMap);

        /* getOrDefault() : returns value for key if present, else returns default value */
        System.out.println("getOrDefault(1, 0): " + freqMap.getOrDefault(1, 0));
        System.out.println("getOrDefault(99, 0): " + freqMap.getOrDefault(99, 0));
        
        /**
         * HashMap vs LinkedHashMap
         * 1. Ordering
         *  HashMap: No guaranteed order of elements.
         *  LinkedHashMap: Maintains insertion order. Elements are stored in the order they were inserted.
         * 2. Internal Structure
         *  HashMap: Uses only a hash table with buckets
         *  LinkedHashMap: Uses a hash table + doubly-linked list to maintain order
         * 3. Performance
         *  HashMap: Slightly faster for basic operations (get, put, remove)
         *  LinkedHashMap: Slightly slower due to maintaining the linked list, but still O(1) for basic operations
         * 4. Memory Usage
         *  HashMap: Less memory overhead
         *  LinkedHashMap: More memory overhead due to additional pointers for the linked list
         */
        
        // Good question - https://www.hackerrank.com/challenges/java-dequeue/problem
        
        // Efficient way to increment/decrement value by 1 in map:
        // - map.merge(key, 1, Integer::sum);
        // - map.merge(key, -1, Integer::sum);
        // ref: https://stackoverflow.com/questions/81346/most-efficient-way-to-increment-a-map-value-in-java
        
        // PTR: map.merge(key, x, Integer::sum); // inc. by x

        // Other ways to change value by x:
        // - map.put(key, map.get(key) + x); // assuming key exists
        // - map.computeIfPresent(key, (k, v) -> v + x);

        // TLDR; main motive of Set and Map is to avoid duplicate insertion. So, to differentiate between elements/Entries we need hashCode and equals.
        // Now, on top of that if you need to order too, then you need to provide a way for that. It can be comparator or key extending Comparable.
        
        // BIG PTR: comparator in cpp revieved the whole `bool operator() (pair<k,v>&a, pair<k,v>&b)` in case of map, but in java you can't create TreeMap based on value.
        // To sort by value, create a list of entries and sort that list using a custom comparator.
        // eg.
        List<Map.Entry<Integer, String>> entries = new ArrayList<>(map.entrySet());
        entries.sort((e1, e2) -> {
            if (!e1.getValue().equals(e2.getValue())) {
                return e1.getValue().compareTo(e2.getValue());
            }
            return e1.getKey().compareTo(e2.getKey());
        });

        // workaround - make this key,value as a new class and use it as key in TreeMap. Note: you won't be able to use get() and other methods of Map interface directly on this TreeMap, but you can iterate over it and find the value for a key.
        // ----IMP for DSA-----
        /**
         * Effective way:
         *  - Create a custom class (e.g., Pair) that holds both key and value.
         *  - Implement Comparable<Pair> or provide a Comparator<Pair> that sorts by value, then key.
         *  - Use a TreeSet<Pair> for automatic ordering and efficient removal of first/last elements.
         *  - Maintain a separate HashMap<K, Pair> for fast lookup by key (if needed).
         * This way, you get: 
         *  - Sorted order by value (then key).
         *  - Efficient removal of first/last (TreeSet).
         *  - Optional fast lookup by key (HashMap).
         */

        // TRICK & IMP Note:
        // -----------------
        // Map.Entry interface doesn't provide setKey() since Map (like a HashMap)
        // depends on the key's hash code to determine its storage location. If
        // you could change the key in-place, the Map would likely "lose" the object
        // becoz it would still be stored in a bucket corresponding to the old hash code
        // - map.put(newKey, map.remove(oldKey)); // use this to effectively update the key in map

        // Easy ways to create Entry
        // 1. Map.Entry<String, Integer> e1 = Map.entry("key", 1); // immutable // e1.setValue(90); // UnsupportedOperationException
        // 2. Map.Entry<String, Integer> e2 = new AbstractMap.SimpleEntry<>("key", 1); // mutable
    }
}

/**
 * Output:
 * -------
 * Map (here treemap with id desc.): {3=Zack, 2=Paul, 1=Doug, 0=Anna}
 * Value for key 2: Paul
 * Value for unknown key 99: null
 * Does 99 gets inserted (like in cpp - mp[99])? false
 * Does map contain value 'Jane'? false
 * Map after removing key 2: {3=Zack, 1=Doug, 0=Anna}
 * Key 0 not removed after conditional matching with 'Stark': {3=Zack, 1=Doug, 0=Anna}
 * Key 0 removed after conditionally matching value with 'Anna': {3=Zack, 1=Doug}
 * Size: 2
 * Is map empty?: false
 * Values in the map: [Zack, Doug]
 * Keys in the map: [3, 1]
 * Iterating over entrySet() = Set<Map.Entry>:
 * key: 3, value: Zack
 * key: 1, value: Doug
 * ------------------
 * putIfAbsent(4, 'Ralph') adds or returns existing key: null
 * Try putting null value in map (5, null): null
 * Map after putting null value: {5=null, 4=Ralph, 3=Zack, 1=Doug}
 * putIfAbsent overrides null value if key is found: {5='null overriden in putIfAbsent. Generally it skips if key is found', 4=Ralph, 3=Zack, 1=Doug}
 * Map after computeIfPresent on key 5: {5='null overriden in putIfAbsent. Generally it skips if key is found' updated, 4=Ralph, 3=Zack, 1=Doug}
 * Map after replace(1, 'johnny'): {5='null overriden in putIfAbsent. Generally it skips if key is found' updated, 4=Ralph, 3=Zack, 1=johnny}
 * Trying to replace() non-existing key 99: {5='null overriden in putIfAbsent. Generally it skips if key is found' updated, 4=Ralph, 3=Zack, 1=johnny}
 * Map after clear: {}
 * Size of map after clear: 0
 * ------------------
 * Frequency map: {1=1, 2=2}
 * Increment value of key 1 by 1: 2
 * Decrement value of key 2 by 1: 1
 * Increment value of unknown key 99 by 1: 1
 * Frequency map final: {1=2, 2=1, 99=1}
 * getOrDefault(1, 0): 2
 * getOrDefault(99, 0): 1
 */