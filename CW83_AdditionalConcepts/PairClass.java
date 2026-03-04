import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PairClass {
    public static void main(String[] args) {
        // ref: https://www.geeksforgeeks.org/java/pair-class-in-java/

        // Pair class is available in javafx.util, but generally this libarary is not available in competitive programming.
        // There isn't a built-in Pair class, but we can easily create one ourselves.

        // Way 1: Use AbstractMap.SimpleEntry (or AbstractMap.SimpleImmutableEntry)
        Map.Entry<Integer, String> pair1 = new AbstractMap.SimpleEntry<>(1, "one");
        // - getValue()
        // - setValue()
        // - getKey()
        
        // Way 2: Use Map.entry (Java 9+)
        Map.Entry<Integer, String> pair2 = Map.entry(2, "two");
        // - getValue()
        // - setValue()
        // - getKey()

        // Way 3: Create a custom Pair class
        class Pair<K, V> {
            private final K key;
            private final V value;

            public Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }
        }
        
        // sort a list of Entry
        List<Map.Entry<Integer, String>> entries = new ArrayList<>();
        entries.add(Map.entry(0, "zero"));
        entries.add(Map.entry(1, "one"));
        entries.add(Map.entry(2, "two"));
        // Way 1: list.sort()
        entries.sort((e1, e2) -> e1.getValue().length() - e2.getValue().length()); // sort by value's length
        System.out.println("sort by value length: " + entries);
        // Way 2: Collections.sort()
        Collections.sort(entries, (e1, e2) -> e1.getKey() - e2.getKey());
        System.out.println("sort by key: " + entries);
    }
}

/**
 * Output:
 * -------
 * sort by value length: [1=one, 2=two, 0=zero]
 * sort by key: [0=zero, 1=one, 2=two]
 */