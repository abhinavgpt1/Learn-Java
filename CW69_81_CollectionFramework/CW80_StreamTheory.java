import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CW80_StreamTheory {
    public static void main(String[] args) {
        // - Stream is a sequence of elements supporting sequential and parallel aggregate operations. 
        // - It doesn't store data, but operates on the source (like Collection) to produce a result.
        // - Stream operations are either intermediate (returning another stream) or terminal (producing a non-stream result). 
        
        // IMP PTR: 
        // - Intermediate operations are lazy and only executed when a terminal operation is invoked.
        // - Streams are not reusable; once a terminal operation is performed, the stream is consumed and cannot be used again. Create a new one from the source if needed.

        // Stream can be created from Collections, Arrays, I/O channels, etc. For example:
        /**
         * List<String> list = Arrays.asList("a", "b", "c");
         * Stream<String> stream = list.stream();
         */

        /**
         * int arr[] = {1, 2, 3};
         * Stream<int[]> arrStream = Stream.of(arr); // Stream<int[]>
         * IntStream intStream = Arrays.stream(arr); // IntStream (primitive specialization of Stream<Integer>)
         * Stream<Integer> intStream = Arrays.stream(arr).boxed(); // Stream<Integer>
        */

        // I/O channels
        // java.nio implements high-speed IO operations and is an alternative to the standard IO API’s.
        /**
         * try {
         *     Stream<String> lines = Files.lines(Paths.get("file.txt"));
         *     lines.forEach(System.out::println);
         * } catch (IOException e) {
         *     e.printStackTrace();
         * }
         */

        // Common Stream operations include filter(), map(), reduce(), collect(), sorted(), etc. For example:
        // - stream.filter(s -> s.startsWith("a")).map(String::toUpperCase).collect(Collectors.toList());

        /* filter() */
        Stream<String> baseStream = Stream.of("apple", "banana", "avocado", "grape");
        Stream<String> filteredBaseStream = baseStream.filter(s -> s.endsWith("e") || s.startsWith("b"));
        List<String> listFruits = filteredBaseStream.collect(Collectors.toList());
        Stream<String> fruitListStream = listFruits.stream();
        Stream<String> fruitListStreamWithEndingE = fruitListStream.filter(fruit -> fruit.endsWith("e"));
        System.out.println("filter(): " + fruitListStreamWithEndingE.collect(Collectors.toList())); // [apple, grape]

        /* map() */
        // Recall: stream once used (i.e. terminal operation performed), can't be reused.
        try {
            Stream<Integer> baseStreamFruitsMappedToLength = baseStream.map(s -> s.length()); // java.lang.IllegalStateException: stream has already been operated upon or closed
        } catch (IllegalStateException e) {
            System.out.println(">>> Stream can't be reused after terminal operation. Create a new stream from the source.");
        }
        Stream<String> baseStream2 = Stream.of("apple", "banana", "avocado", "grape");
        Stream<Integer> baseStreamFruitsMappedToLength = baseStream2.map(s -> s.length());
        System.out.println("map(): " + baseStreamFruitsMappedToLength.collect(Collectors.toSet())); // [5, 6, 7]

        /* reduce() : returns Optional<T> */
        // - reduce() is a terminal operation that combines elements of the stream using a binary operator
        // - It can be used for operations like sum, product, concatenation, etc.
        // - Syntax 1: stream.reduce((a, b) -> a + b).orElse(defaultValue);
        // - Syntax 2: stream.reduce(identity, (a, b) -> a + b);
        //  - identity : initial value for the reduction and also serves as the default result if the stream is empty.
        // - Syntax 3: stream.reduce(identity, (a, b) -> a + b, (a, b) -> a + b); // for parallel streams, the third parameter is a combiner function that combines the results of the reduction from different threads.
        List<Integer> numbers = List.of(1, 2, 3, 4);
        int sum1 = numbers.stream().reduce((a, b) -> a + b).orElse(0);
        int sum2 = numbers.stream().reduce(1, (a, b) -> a + b);
        System.out.println("reduce() without identity: " + sum1); // 10
        System.out.println("reduce() with identity (1): " + sum2); // 11

        /* collect(): Check _09_Collections section (10) */
        // Trick: Collect as entrySet : Main objective is to do a K:V mapping, and then simply collect as entrySet().
        Stream<String> fruits = Stream.of("apple", "banana", "avocado", "grape", "apple");
        Map<String, Long> fruitCount = fruits.collect(Collectors.groupingBy(f -> f, Collectors.counting()));
        Set<Map.Entry<String, Long>> fruitCountEntrySet = fruitCount.entrySet();
        System.out.println("collect() as Map: " + fruitCount); // {banana=1, avocado=1, grape=1, apple=2}
        System.out.println("collect() as entrySet: " + fruitCountEntrySet); // [banana=1, avocado=1, grape=1, apple=2]

        /* sorted() / sorted(Comparator) */
        // - sorted() is an intermediate operation that returns a stream consisting of the elements of the original stream sorted according to natural order or a provided Comparator.
        Stream<String> unsortedFruits = Stream.of("banana", "grape", "apple", "avocado");
        Stream<String> sortedFruits = unsortedFruits.sorted();
        System.out.println("sorted(): " + sortedFruits.collect(Collectors.toList())); // [apple, avocado, banana, grape]
        class Student {
            int id;
            String name;
            double gpa;

            Student(int id, String name, double gpa) {
                this.id = id;
                this.name = name;
                this.gpa = gpa;
            }

            @Override
            public String toString() {
                return "Student{id=" + id + ", name='" + name + "', gpa=" + gpa + "}";
            }
        }
        Stream<Student> students = Stream.of(
                new Student(1, "Alice", 3.5),
                new Student(2, "Bob", 3.8),
                new Student(3, "Charlie", 3.2)
        );
        Stream<Student> sortedStudentsByGPA = students.sorted(Comparator.comparing(s -> s.gpa));
        System.out.println("sorted(Comparator): " + sortedStudentsByGPA.toList());

        /* flatMap() */
        // - flatMap() is an intermediate operation that takes a function which maps each element to a stream of new values, and then flattens those streams into a single stream.
        // - It is often used for operations like splitting strings into words, or flattening nested collections.
        Stream<String> sentences = Stream.of("hello world", "java streams");
        Stream<String> words = sentences.flatMap(sentence -> Arrays.stream(sentence.split(" ")));
        System.out.println("flatMap(): " + words.toList()); // [hello, world, java, streams]

        // PTR: stream is available for every collection type (List, Set, Map, etc.) and also for arrays.
        // It's NOT available for Map directly but you can get a stream of its entries using map.entrySet().stream().

        // map.stream() [WRONG] - can use stream over entrySet(), infact any Collection, but not Map.

        // Bonus: Streams can be processed in parallel using parallelStream() or by calling parallel() on an existing stream. 
        // This allows for concurrent processing of elements, but care must be taken to ensure thread safety and avoid side effects.
        // code example:
        Stream<String> parallelStream = Stream.of("apple", "banana", "avocado", "grape").parallel();
        List<String> parallelSortedFruits = parallelStream.sorted().toList();
        System.out.println("parallelSortedFruits: " + parallelSortedFruits);
    }   
}

/**
 * Output:
 * -------
 * filter(): [apple, grape]
 * >>> Stream can't be reused after terminal operation. Create a new stream from the source.
 * map(): [5, 6, 7]
 * reduce() without identity: 10
 * reduce() with identity (1): 11
 * collect() as Map: {banana=1, avocado=1, apple=2, grape=1}
 * collect() as entrySet: [banana=1, avocado=1, apple=2, grape=1]
 * sorted(): [apple, avocado, banana, grape]
 * sorted(Comparator): [Student{id=3, name='Charlie', gpa=3.2}, Student{id=1, name='Alice', gpa=3.5}, Student{id=2, name='Bob', gpa=3.8}]
 * flatMap(): [hello, world, java, streams]
 * parallelSortedFruits: [apple, avocado, banana, grape]
 */