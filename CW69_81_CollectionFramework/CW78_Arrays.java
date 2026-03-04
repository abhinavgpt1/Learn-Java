import java.util.Arrays;
import java.util.List;

public class CW78_Arrays {
    public static void main(String[] args) {
        int[] arr = { 3, 1, 4, 2 };

        // 1. sout(arr) printed [I@1dbd16a6. Use Arrays.toString(arr) to print the array in a readable format
        System.out.println("Arrays.toString() = arr, before sorting: " + Arrays.toString(arr));

        // 2.1. Arrays.sort(arr)
        // 2.2. Arrays.sort(arr, fromIndex, toIndex)
        //IMP PTR: there's no sort(arr, comparator) since Comparator only works with "object" types that implement Comparable eg. Integer.
        Arrays.sort(arr);

        System.out.println("Arrays.toString() = arr, after sorting: " + Arrays.toString(arr));

        // 3. Arrays.binarySearch(arr, key) : return negative value if key not found not necessarily -1. 
        // IMP: It returns -(insertion point + 1) where insertion point is the index where the key would be inserted to maintain sorted order. 
        // For questions like find insertion point, find closest element, etc. this is useful.
        int index = Arrays.binarySearch(arr, 2);
        System.out.println("Index of value 2: " + index);
        System.out.println("Index of value 5 (not present) = negative value (!= -1) : " + Arrays.binarySearch(arr, 5)); // key not found = -(low + 1) ~= negative value

        // 4.1 Arrays.fill(arr, value)
        // 4.2 Arrays.fill(arr, fromIndex, toIndex, value) : toIndex is exclusive
        Arrays.fill(arr, 0);
        System.out.println("Arrays.fill(arr, 0): " + Arrays.toString(arr));
        
        Arrays.fill(arr, 1, 3, -1); // toIndex is exclusive
        System.out.println("Arrays.fill(arr, 1, 3, -1), toIndex(=3) is exclusive: " + Arrays.toString(arr));

        // Following works in case of Integer[] arr;
        // Arrays.fill(arr, null); // error: The method fill(int[], int) in the type Arrays is not applicable for the arguments (int[], null)

        System.out.println("\nStream API with Arrays:");
        // 5.1. Arrays.stream(arr)
        // 5.2. Arrays.stream(arr, fromIndex, toIndex) : toIndex is exclusive
        // 5.1.1. forEach()
        System.out.print("foreach(): ");
        Arrays.stream(arr).forEach(x -> System.out.print("2*" + x + " = " + 2 * x + ", "));
        System.out.println();

        // 5.1.2. toArray() : returns datatype[]
        int[] arr2 = Arrays.stream(arr).toArray();
        System.out.println("Arrays.stream(arr).toArray() = same int arr[]: " + Arrays.toString(arr2));

        // 5.1.3. int arr[] -> List<Integer> : use boxed() to convert IntStream to Stream<Integer>
        List<Integer> arrToList = Arrays.stream(arr).boxed().toList();
        System.out.println("Arrays.stream(arr).boxed().toList(): " + arrToList); 
        int arrFromList[] = arrToList.stream().mapToInt(Integer::intValue).toArray(); // back to int arr[]

        // 5.1.4. min(), max() : returns Optional<Datatype> eg. OptionalInt.
        // 5.1.5. average() : returns OptionalDouble
        System.out.println("Arrays.stream(arr).min().getAsInt(): " + Arrays.stream(arr).min().getAsInt());
        System.out.println("Arrays.stream(arr).max().getAsInt(): " + Arrays.stream(arr).max().getAsInt());
        System.out.println("Arrays.stream(arr).average().orElse(0.0): " + Arrays.stream(arr).average().orElse(0.0));

        // 6. Arrays.asList(a1, a2, a3...) : return List<WrapperDatatype> eg. List<Integer>
        List<Integer> arrAsList = Arrays.asList(0, 1, 2);
        System.out.println("Arrays.asList(0, 1, 2): " + arrAsList);
    }
}

/**
 * Output:
 * -------
 * Arrays.toString() = arr, before sorting: [3, 1, 4, 2]
 * Arrays.toString() = arr, after sorting: [1, 2, 3, 4]
 * Index of value 2: 1
 * Index of value 5 (not present) = negative value (!= -1) : -5
 * Arrays.fill(arr, 0): [0, 0, 0, 0]
 * Arrays.fill(arr, 1, 3, -1), toIndex(=3) is exclusive: [0, -1, -1, 0]
 * 
 * Stream API with Arrays:
 * foreach(): 2*0 = 0, 2*-1 = -2, 2*-1 = -2, 2*0 = 0, 
 * Arrays.stream(arr).toArray() = same int arr[]: [0, -1, -1, 0]
 * Arrays.stream(arr).min().getAsInt(): -1
 * Arrays.stream(arr).max().getAsInt(): 0
 * Arrays.asList(0, 1, 2): [0, 1, 2]
 */