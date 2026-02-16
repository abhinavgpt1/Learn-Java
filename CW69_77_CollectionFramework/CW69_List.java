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
        list.add("apple");
        list.add(1, "banana"); // add(index) is valid for index <= size
        System.out.println("List1 size: " + list.size());
        System.out.println("Does list contain 'apple'? " + list.contains("apple")); // true
        System.out.println("Does list contain 'apples'? " + list.contains("apples")); // false 
        System.out.println("Index of 'banana' in list: " + list.indexOf("banana")); // 0
        System.out.println("Index of 'bananas' in list: " + list.indexOf("bananas")); // -1

        ArrayList<String> clonedList = (ArrayList<String>)list.clone();
        System.out.println("Original list: " + list);
        System.out.println("Cloned list: " + clonedList);

        // Print using iterator
        System.out.println("\nUsing iterator to print list:");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String element = it.next();
            System.out.println(element); //prints the next element
        }
        // Print using forEach
        System.out.println("\nUsing forEach to print list:");
        list.forEach(System.out::println); // prints each element in the list
        
        System.out.println("------------");

        list.add("apricot");
        list.add("brinjal");
        list.remove("apple");
        System.out.println("List1 after adding apricot and brinjal, and removing apple: " + list);

        list.removeIf(s -> s.startsWith("b")); // removes elements starting with 'b'
        System.out.println("After removal all elements starting with 'b': " + list);
        
        list.replaceAll(s -> s.toUpperCase()); // converts all elements to uppercase
        System.out.println("After replaceAll to uppercase: " + list);
        
        list.set(0, "kiwi"); // replaces the first element // set doesn't work out of bound
        // list.set(1, "kiwi2"); // replaces the second element // error since arr size = 1
        System.out.println("After update element at index 0 with kiwi: " + list); //[kiwi]

        System.out.println("Is list empty? " + list.isEmpty()); // false
        
        list.add("apple");
        list.add("watermelon");
        System.out.println("List1 after adding apple and watermelon: " + list);
        
        list.sort(null); // natural order sorting
        System.out.println("After sort (inc): " + list); // prints sorted list
        
        // list.sort((a, b) -> b.compareTo(a)); // using custom comparator to sort
        list.sort(Comparator.reverseOrder()); // opposite of this comparator => Comparator.naturalOrder();
        System.out.println("After sorting in reverse order: " + list);

        System.out.println("Sublist(1,3):" + list.subList(1,3)); // exclusive end index // endIndex should not be out of bound
        System.out.println("Uppercase list: " + list.stream().map(String::toUpperCase).collect(Collectors.toList())); // collects elements into a list
        System.out.println("Frequency of 'apple': " + Collections.frequency(list, "apple")); // no direct method in List/ArrayList for count check
        System.out.println("Frequency of 'guava': " + Collections.frequency(list, "guava")); // returns 0 if not found

        // Trick: Convert ArrayList<String> to String[]
        String []arrStr = list.stream().toArray(String[]::new);
        System.out.println("String[] from List<String>: " + Arrays.toString(arrStr));

        list.clear();
        System.out.println("List1 after clear: " + list);

        // Trick: Convert int[] to List<Integer>
        int arr[] = { 1, 2, 3 };
        List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println("List<Integer> from int[]: " + arrList);

        // Trick: Convert List<Integer> to int[]
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
 * String[] from List<String>: [watermelon, kiwi, apple]
 * List1 after clear: []
 * List<Integer> from int[]: [1, 2, 3]
 * int[] from List<Integer>: [1, 2, 3]
 * ArrayList<Integer> from List<Integer>: [1, 2, 3]
 * ArrayList with default values (manually listed): [1, 2, 3, 4, 5]
 * ArrayList with N=5 default value V=0 using Collections.nCopies: [0, 0, 0, 0, 0]
 */