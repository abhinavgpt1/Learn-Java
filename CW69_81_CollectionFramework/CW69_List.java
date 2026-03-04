import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CW69_List {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        /* add() / add(index, element) */
        list.add("apple");
        // PTR: add(index, element) is valid for index = size(arr)
        list.add(1, "banana"); 
        System.out.println("List1 size: " + list.size());
        System.out.println("Does list contain 'apple'? " + list.contains("apple")); // true
        System.out.println("Does list contain 'apples'? " + list.contains("apples")); // false 
        System.out.println("Index of 'banana' in list: " + list.indexOf("banana")); // 0
        System.out.println("Index of 'bananas' in list: " + list.indexOf("bananas")); // -1

        /* clone() */
        ArrayList<String> clonedList = (ArrayList<String>)list.clone();
        System.out.println("Original list: " + list);
        System.out.println("Cloned list: " + clonedList);

        /* print using iterator() */
        System.out.println("\nUsing iterator to print list:");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String element = it.next();
            System.out.println(element); //prints the next element
        }
        // PTR: Collection<E> has iterator() which returns Iterator<E> eg. new ArrayList<Integer aka E>().iterator() will return Iterator<Integer> and not Iterator<ArrayList<Integer>>
        // - don't confuse it with iterator from cpp eg. vector::iterator it = arr.begin();

        /* print using forEach() */
        System.out.println("\nUsing forEach to print list:");
        list.forEach(System.out::println); // prints each element in the list
        
        System.out.println("------------");

        /* remove(index) / remove(Object) */ // IMP: remove(Object) != remove(index) especially for List<Integer>
        list.add("apricot");
        list.add("brinjal");
        list.remove("apple");
        System.out.println("List1 after adding apricot and brinjal, and removing apple: " + list);

        /* removeIf() */
        list.removeIf(s -> s.startsWith("b")); // removes elements starting with 'b'
        System.out.println("After removal all elements starting with 'b': " + list);
        
        /* replaceAll() */
        list.replaceAll(s -> s.toUpperCase()); // converts all elements to uppercase // can use String::UpperCase instead of lambda
        System.out.println("After replaceAll to uppercase: " + list);
        
        /* set(index, element) */
        list.set(0, "kiwi"); // replaces the first element
        // list.set(1, "kiwi2"); // error since arr size = 1 // set doesn't work out of bound
        System.out.println("After update element at index 0 with kiwi: " + list); //[kiwi]

        /* isEmpty() */
        System.out.println("Is list empty? " + list.isEmpty()); // false
        
        list.add("apple");
        list.add("watermelon");
        System.out.println("List1 after adding apple and watermelon: " + list);
        
        /* sort(null) / sort(Comparator) */
        list.sort(null); // null => natural order sorting
        System.out.println("After sort (inc): " + list); // prints sorted list

        list.sort(Comparator.reverseOrder()); // or use (a, b) -> b.compareTo(a) // Comparator.naturalOrder() is opposite of Comparator.reverseOrder().
        System.out.println("After sorting in reverse order: " + list);

        /* subList(fromIndex, toIndex) / Collections.frequency() / stream().count() / stream().map() */
        System.out.println("Sublist(1,3):" + list.subList(1,3)); // exclusive end index // endIndex should not be out of bound
        System.out.println("Uppercase list: " + list.stream().map(String::toUpperCase).collect(Collectors.toList())); // collects elements into a list
        System.out.println("Frequency of 'apple': " + Collections.frequency(list, "apple")); // no direct method in List/ArrayList for count check
        System.out.println("Frequency of 'guava': " + Collections.frequency(list, "guava")); // returns 0 if not found
        System.out.println("Frequency of 'apple' using stream and filter: " + list.stream().filter(s -> s.equals("apple")).count()); // returns long
        System.out.println("Frequency of 'guava' using stream and filter: " + list.stream().filter(s -> s.equals("guava")).count()); // returns long

        // PTR: remove(int) != remove(Integer) for List<Integer>
        List<Integer> listInt = new ArrayList<>(Arrays.asList(1, 2, 3));
        listInt.remove(1); // removes element at index 1, i.e., 2
        System.out.println("remove(i) vs remove(Obj): List<Integer> after remove(1): " + listInt); // [1, 3]
        listInt.remove(Integer.valueOf(1)); // removes element 1
        System.out.println("remove(i) vs remove(Obj): List<Integer> after remove(Integer.valueOf(1)): " + listInt); // [3]

        // Trick: Convert ArrayList<String> to String[]
        String []arrStr = list.stream().toArray(String[]::new);
        System.out.println("String[] from List<String>: " + Arrays.toString(arrStr));

        list.clear();
        System.out.println("List1 after clear: " + list);

        // Trick: Convert int[] to List<Integer> - REMEMBER boxed()
        int arr[] = { 1, 2, 3 };
        List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println("List<Integer> from int[]: " + arrList);
        
        char arrChar[] = {'a', 'b', 'c'};
        
        // PTR: Arrays.stream(arrChar); // No CharStream available in Arrays. Use IntStream and map to char.
        // ref StackOverflow: No CharStream exists in Java's Stream API to avoid API clutter — a deliberate design decision by the Java team.
        // 1. convert arrChar to String
        String arrCharStr = new String(arrChar);
        // 2. convert arrChar to Character arrayList
        List<Character> arrCharList = new String(arrChar).chars().mapToObj(c -> (char)c).collect(Collectors.toList());

        // Trick: Convert List<Integer> to int[] - REMEMBER toArray()
        int[] arr2 = arrList.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("int[] from List<Integer>: " + Arrays.toString(arr2));

        // Trick: Convert List<Integer> to ArrayList<Integer>
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        ArrayList<Integer> arrayList = new ArrayList<>(list2);
        System.out.println("ArrayList<Integer> from List<Integer>: " + arrayList);

        // Trick: get ArrayList with default values
        ArrayList<Integer> defaultList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("ArrayList with default values (manually listed): " + defaultList);

        // Trick: get ArrayList with default values using Collections.nCopies
        ArrayList<Integer> defaultList2 = new ArrayList<>(Collections.nCopies(5, 0)); // creates a list of 5 elements, all initialized to 0
        System.out.println("ArrayList with N=5 default value V=0 using Collections.nCopies: " + defaultList2);

        // Trick: Find min/max in List<Integer>
        List<Integer> nums = Arrays.asList(5, 2, 8, 1, 4);
        int min = Collections.min(nums);
        int max = Collections.max(nums);
        System.out.println("Using Collections, List Min: " + min + ", List Max: " + max);

        nums.stream().min((i1, i2) -> i1 - i2).ifPresent(minVal -> System.out.println("List Min using stream: " + minVal));
        nums.stream().max((i1, i2) -> i1 - i2).ifPresent(maxVal -> System.out.println("List Max using stream: " + maxVal));

        // PTR: LinkedList<>().get(index) works in O(n), but it searches from either
        // start or end depending on whether index is in first half or second half of
        // the list. So, it's not O(n) in worst case but O(n/2) which is still O(n) but
        // better than O(n) for ArrayList when it comes to get(index).

        // practice problems
        // - https://www.geeksforgeeks.org/problems/java-arraylist5312/1
        // - https://www.geeksforgeeks.org/problems/arraylist-operation/1
        // - https://www.hackerrank.com/challenges/java-arraylist/problem?isFullScreen=true
        // - https://www.w3resource.com/java-exercises/collection/array-list.php
    }
}

/**
 * Output:
 * -------
 * List1 size: 2
 * Does list contain 'apple'? true
 * Does list contain 'apples'? false
 * Index of 'banana' in list: 1
 * Index of 'bananas' in list: -1
 * Original list: [apple, banana]
 * Cloned list: [apple, banana]
 * 
 * Using iterator to print list:
 * apple
 * banana
 * 
 * Using forEach to print list:
 * apple
 * banana
 * ------------
 * List1 after adding apricot and brinjal, and removing apple: [banana, apricot, brinjal]
 * After removal all elements starting with 'b': [apricot]
 * After replaceAll to uppercase: [APRICOT]
 * After update element at index 0 with kiwi: [kiwi]
 * Is list empty? false
 * List1 after adding apple and watermelon: [kiwi, apple, watermelon]
 * After sort (inc): [apple, kiwi, watermelon]
 * After sorting in reverse order: [watermelon, kiwi, apple]
 * Sublist(1,3):[kiwi, apple]
 * Uppercase list: [WATERMELON, KIWI, APPLE]
 * Frequency of 'apple': 1
 * Frequency of 'guava': 0
 * Frequency of 'apple' using stream and filter: 1
 * Frequency of 'guava' using stream and filter: 0
 * remove(i) vs remove(Obj): List<Integer> after remove(1): [1, 3]
 * remove(i) vs remove(Obj): List<Integer> after remove(Integer.valueOf(1)): [3]
 * String[] from List<String>: [watermelon, kiwi, apple]
 * List1 after clear: []
 * List<Integer> from int[]: [1, 2, 3]
 * int[] from List<Integer>: [1, 2, 3]
 * ArrayList<Integer> from List<Integer>: [1, 2, 3]
 * ArrayList with default values (manually listed): [1, 2, 3, 4, 5]
 * ArrayList with N=5 default value V=0 using Collections.nCopies: [0, 0, 0, 0, 0]
 * Using Collections, List Min: 1, List Max: 8
 * List Min using stream: 1
 * List Max using stream: 8
 */