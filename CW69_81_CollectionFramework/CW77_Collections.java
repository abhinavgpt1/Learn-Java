import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class CW77_Collections {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 4, 2);
        System.out.println("list before sorting: " + list);

        // 1.1. Collections.sort(list) : sorts in natural order = Comparator.naturalOrder()
        // 1.2. Collections.sort(list, ComparatorInstance) : sorts according to the provided Comparator
        Collections.sort(list); // sorts in natural order
        System.out.println("list after sorting in natural order: " + list);
        
        Collections.sort(list, Comparator.reverseOrder());
        System.out.println("list after sorting with comparator (desc.): " + list);

        // 2. Collections.reverse(list)
        Collections.reverse(list);
        System.out.println("list after reversing: " + list);

        // 3. Collections.frequency(collection, element) ~= count of element in collection
        System.out.println("Frequency of 2 in list: " + Collections.frequency(list, 2));
        System.out.println("Frequency of unknown number in list: " + Collections.frequency(list, 5)); // 0

        // 4. Collections.min(list), Collections.max(list)
        System.out.println("Min list: " + Collections.min(list));
        System.out.println("Max list: " + Collections.max(list));

        // PTR: Comparator can be used in Collections.max()/min() - but it can be confusing
        // Rule: comparator sorts the elements first. The first element in the sequence becomes min, and last becomes max. 
        // eg1. asc sort of arr = [7,8,9]. Here the min = 7, max = 9.
        // eg2. desc sort of arr = [9,8,7]. Here the min = 9, max = 7.
        //  - Collections.min(collection, ascendingComparator) → gets minimum element
        //  - Collections.max(collection, ascendingComparator) → gets maximum element
        //  - Collections.min(collection, descendingComparator) → gets maximum element
        //  - Collections.max(collection, descendingComparator) → gets minimum element
        // eg. https://www.geeksforgeeks.org/problems/elected-candidate/1

        // 5. Collections.binarySearch(list, key) : returns the index of the search key, if it is contained in the list; 
        // otherwise, (-(insertion point) - 1). // For questions like find insertion point, find closest element, etc. this is useful.
        System.out.println("BinarySearch: Index of 2: " + Collections.binarySearch(list, 2));
        System.out.println("BinarySearch: Index of 5 (unknown): " + Collections.binarySearch(list, 5)); // key not found = -(low + 1) ~= negative value
        
        // 6. Collections.swap(list, i, j) // swaps elements at index i and j
        Collections.swap(list, 1, 3);
        System.out.println("list after swapping 'index' at 1 and 3: " + list);

        // IMP: 7. Collections.nCopies(n, element) : IMP: Unmodifiable.
        List<Integer> nArrList = Collections.nCopies(5, -1);
        System.out.println("List with 5 copies of -1 (unmodifiable): " + nArrList);
        // nArrList.add(1); // throws UnsupportedOperationException since it's immutable

        System.out.println();

        // 8. Sort using Comparable and Comparator for a custom class
        // IMP: Comparable (compareTo()) vs Comparator (compare())
        // --------------------------------------------------------
        // Comparable is used for natural ordering within a class.
        // Comparator is used for custom ordering outside the class, can be used for multiple sorting criteria.
        // ref: https://www.geeksforgeeks.org/java/comparable-vs-comparator-in-java/
        List<Student> listStudents = Arrays.asList(new Student("Snow", 3), new Student("John", 2),
                new Student("Jane", 1), new Student("Doe", 4));
        
        // Using Comparator: Extendable approach
        Collections.sort(listStudents, (s1, s2) -> s1.id - s2.id); // sort by id in ascending order
        System.out.println("List of students after sorting by id using comparator: ");
        System.out.println(listStudents);
        
        // Using Comparable: Inbuilt approach, but less flexible since you can have only 1 compareTo() definition in the class. For multiple sorting criteria, you need to use Comparator.
        // IMP: Class needs to implement Comparable and override public int compareTo(Student)
        // IMP: PTR: natural ordering eg. Comparator.naturalOrder() or listStudents.sort(null) is valid only for inbuilt types and not for user defined data types (UDDT) unless they implement Comparable interface.
        Collections.sort(listStudents); // OR listStudents.sort(null);
        System.out.println("List of students after sorting naturally by id using Comparable: ");
        System.out.println(listStudents);
        
        System.out.println();

        // PTR:
        // - compareTo != equals
        // - compareTo is used for sorting, equals is used for equality check.
        // - It is a good practice to maintain the contract between compareTo, equals and hashCode.
        // - IMP: two equal objects (as defined by equals()) will have same hashCode, but not vice-versa.

        // IMP: 9.1 List<Integer> -> arr[] : use stream() and mapToInt() to convert List<Integer> to IntStream, then use toArray() to convert IntStream to int[].
        int arr[] = list.stream().mapToInt(Integer::intValue).toArray();
        // IMP: 9.2 <AnyCollection>.toArray() : returns Object[]. Use toArray(Datatype array)
        List<String> lstr = new ArrayList<>();
        Object[] objarr = lstr.toArray();
        String[] larr = lstr.toArray(new String[0]); // or use String[]::new

        // 10. Collectors: Used to accumulate elements of a stream into a summary result, such as a List, Set, Map, or even a single value like sum or average.
        // - Collectors.toList()
        List<Integer> list2 = new ArrayList<>();
        list2.add(1); list2.add(2); list2.add(2); list2.add(3); list2.add(3); list2.add(3);
        System.out.println("Collectors.toList(): " + list2.stream().filter(x -> x < 3).collect(Collectors.toList())); // [1, 2, 2]
        // - Collectors.toSet()
        System.out.println("Collectors.toSet(): " + list2.stream().filter(x -> x < 3).collect(Collectors.toSet())); // [1, 2]
        // - Collectors.toCollection(): collects the elements of a stream into a specific collection type, such as ArrayList, LinkedList, HashSet, etc.
        System.out.println("Collectors.toCollection(HashSet::new): " + list2.stream().filter(x -> x < 3).collect(Collectors.toCollection(HashSet::new))); // [1, 2] // Use any concrete class. List::new or Set::new will error out.
        // - Collectors.toMap(): collects the elements of a stream into a Map. You need to provide keyMapper and valueMapper functions to specify how to extract keys and values from the stream elements.
        System.out.println("Collectors.toMap(): x:2x: " + list2.stream().collect(Collectors.toMap(x->x, x->2*x, (existingKey, replacementDupKey) -> existingKey))); // {1=2, 2=4, 3=6} // Use merge function to handle duplicate keys. Here, we keep the existing value and ignore the replacement value for duplicate keys.
            //  PTR: x -> x === Function.identity() : returns the input argument as is. So, Collectors.toMap(x -> x, x -> 2*x) can be written as Collectors.toMap(Function.identity(), x -> 2*x)
        // - Collectors.joining(CharSequence delimiter): concatenates the input elements into a single String, separated by the specified delimiter.
        System.out.println("Collectors.joining(deli): " + list2.stream().map(x->x.toString()).collect(Collectors.joining(",")));
        // - Collectors.joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
        System.out.println("Collectors.joining(deli, pre, suff): " + list2.stream().map(x->x.toString()).collect(Collectors.joining(",", "[", "]")));
        // - Collectors.groupingBy(classifier): returns Map
        System.out.println("Collectors.groupingBy(): x->2x: " + list2.stream().collect(Collectors.groupingBy(x -> 2*x))); // {2=[1], 4=[2, 2], 6=[3, 3, 3]} // groups the elements by their double value
        System.out.println("Collectors.groupingBy() + counting(): x->x: " + list2.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()))); // {1=1, 2=2, 3=3} // counts the frequency of each element. 2=[2,2] changes to 2=2 as [2,2] changes to 2 becoz of counting().
        // Extra: Following can be used with groupingBy() - mapping(), counting(), collectingAndThen(), reducing(), etc.

        // 11. Collections.swap(List<> / Object[], int i, int j)
        ArrayList<Character> alphabet = new ArrayList<>(); alphabet.add('a'); alphabet.add('b'); alphabet.add('c');
        Collections.swap(alphabet, 0, 2);
        System.out.println("Collections.swap({a,b,c}, 0, 2): " + alphabet);

        // Notes from BCE book:
        // 1. No concrete class in Collections framework DIRECTLY implements the Collection interface.
        // 2. All concrete classes implement the Serializable and Cloneable interfaces.
        // 3. Vector is thread-safe / synchronized, hence it is slow than ArrayList.
        // 4. HashMap allows null as value (in key-pair) and is NOT thread-safe. HashTable is thread-safe and DOES NOT allow null.

        System.out.println();

        // Bonus: 
        // ------
        // - List to Integer[]: list.toArray(new Integer[0]) : this can be converted to int[] using stream and mapToInt
        Integer[] arrInteger = list.toArray(new Integer[0]);
        int arrFromInteger[] = Arrays.stream(arrInteger).mapToInt(Integer::intValue).toArray(); // int arr[]
        // - Back to list: int arr[] -> List<Integer>
        List<Integer> listFromArr = Arrays.stream(arr).boxed().toList();

        // -------Can skip the following since they're not used in competitive much-------

        // - Collections.singletonList(element) // returns an immutable list containing only the specified element
        List<Integer> singletonList = Collections.singletonList(2);
        System.out.println("Singleton list (unmodifiable): " + singletonList);
        // singletonList.add(3); // throws UnsupportedOperationException since it's immutable

        // - Collections.shuffle(list)
        System.out.println("list before shuffling: " + list);
        Collections.shuffle(list); // shuffles the list
        System.out.println("list after shuffling: " + list);

        // - Collections.disjoint(collection1, collection2) : returns true if the two collections have no elements in common

        // - Collections.emptyList(), Collections.emptySet(), Collections.emptyMap()
        List<Integer> emptyList = Collections.emptyList(); // immutable empty list // EmptyList extends AbstractList which throws UnsupportedOperationException for all mutating operations
        // emptyList.add(1); // throws UnsupportedOperationException since it's immutable
        Collections.emptySet(); // immutable empty set
        Collections.emptyMap(); // immutable empty map
        
        // - Collections.rotate(list, distance)
        Collections.rotate(list, 1); // rotates the list by 1 position
        System.out.println("list after rotating by 1 (clockwise): " + list); // clockwise
        Collections.rotate(list, -1); // rotates the list by 1 position
        System.out.println("list after rotating by 1 (anti-clockwise): " + list); // anti-clockwise = use negative distance
    }
}

class Student implements Comparable<Student> {
    int id;
    String name;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override // IMPORTANT if implementing Comparable.
    public int compareTo(Student other) { 
        // notice how this warning says to override equals() method as well, since
        // compareTo() is used for sorting and equals() is used for equality check, they
        // should be consistent with each other. If compareTo() returns 0 for two objects,
        // then equals() should return true for those objects. This is a contract that
        // should be maintained to avoid unexpected behavior in collections that rely on
        // these methods.
        // => Recall: equals-hashCode contract. Don't forget to override
        // hashCode() when you override equals() to maintain the contract that equal
        // objects must have equal hash codes (not vice-versa).
        // => good practice: While overriding compareTo(), also override equals() and hashCode() to maintain consistency and avoid unexpected behavior in collections that rely on these methods.
        return this.id - other.id;
    }

    @Override
    public String toString(){
        return "Student {name='" + name + "', id=" + id + "}";
    }
}

/**
 * Output:
 * -------
 * list before sorting: [3, 1, 4, 2]
 * list after sorting in natural order: [1, 2, 3, 4]
 * list after sorting with comparator (desc.): [4, 3, 2, 1]
 * list after reversing: [1, 2, 3, 4]
 * Frequency of 2 in list: 1
 * Frequency of unknown number in list: 0
 * Min list: 1
 * Max list: 4
 * BinarySearch: Index of 2: 1
 * BinarySearch: Index of 5 (unknown): -5
 * list after swapping 'index' at 1 and 3: [1, 4, 3, 2]
 * List with 5 copies of -1 (unmodifiable): [-1, -1, -1, -1, -1]
 * 
 * List of students after sorting by id using comparator: 
 * [Student {name='Jane', id=1}, Student {name='John', id=2}, Student {name='Snow', id=3}, Student {name='Doe', id=4}]
 * List of students after sorting naturally by id using Comparable: 
 * [Student {name='Jane', id=1}, Student {name='John', id=2}, Student {name='Snow', id=3}, Student {name='Doe', id=4}]
 * 
 * Collectors.toList(): [1, 2, 2]
 * Collectors.toSet(): [1, 2]
 * Collectors.toCollection(HashSet::new): [1, 2]
 * Collectors.toMap(): x:2x: {1=2, 2=4, 3=6}
 * Collectors.joining(deli): 1,2,2,3,3,3
 * Collectors.joining(deli, pre, suff): [1,2,2,3,3,3]
 * Collectors.groupingBy(): x->2x: {2=[1], 4=[2, 2], 6=[3, 3, 3]}
 * Collectors.groupingBy() + counting(): x->x: {1=1, 2=2, 3=3}
 * Collections.swap({a,b,c}, 0, 2): [c, b, a]
 * 
 * Singleton list (unmodifiable): [2]
 * list before shuffling: [1, 4, 3, 2]
 * list after shuffling: [3, 4, 2, 1]
 * list after rotating by 1 (clockwise): [1, 3, 4, 2]
 * list after rotating by 1 (anti-clockwise): [3, 4, 2, 1]
 */