import java.util.ArrayList;
import java.util.List;

public class Var_ReservedName {
    public static void main(String[] args) {
        // This example clarifies that var isn't a keyword
        var var = new ArrayList<>();
        var.add(1);
        System.out.println(var);

        // Once datatype is set for var (identifier), it can't be changed.
        // var = 10; // error: incompatible types: int cannot be converted to ArrayList<Object>
        // System.out.println(var);

        // ref: https://www.geeksforgeeks.org/java/var-keyword-in-java/
        // Def: The var reserved type name (not a Java keyword) was introduced in Java 10. 
        // Type inference is used in var keyword in which it detects automatically the datatype of a variable based on the surrounding context.

        /**
         * Rules:
         * - var can be used as a local variable - for loop, inside main as a datatype, try-with-resources
         * 
         * - var can't be used as 
         *  - method param, eg. void fun(var a) {} [WRONG]
         *  - return type, eg. var fun() {} [WRONG]
         *  - instance or global variable
         *  - generic type, eg. List<var>
         *  - generic declarations, eg. var<Integer> l = ... [WRONG] | var l = new ArrayList<>(); [RIGHT]
         *  
         * - var needs initialization, eg. var v = null; [WRONG]
         * - var needs target type, eg. var obj = (a, b) -> a + b; [WRONG]
         *  - Consumer<String> consumer = (@Nonnull var s) -> System.out.println(s.toUpperCase()); [RIGHT] Can apply annotations like @Nonnull directly to the parameter
         */

        // Valid case 1: for each loop
        var names = List.of("Alice", "Bob", "Charlie");
        // Infers 'name' as a String
        for (var name : names) {
            System.out.println(name);
        }

        // Valid case 2: for loop
        // Infers 'i' as an int
        for (var i = 0; i < 10; i++) {
            System.out.print(i + ",");
        }

        // Valid case 3: local variable with initialization
        var x = 10;
        var list = List.of(1, 2, 3);
        var stream = list.stream();

        // Valid case 4: try-with-resources
        // try (var fis = new FileInputStream("student_records.txt")) {
        //     // Process the file
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}

/**
 * Output:
 * -------
 * [1]
 * Alice
 * Bob
 * Charlie
 * 0,1,2,3,4,5,6,7,8,9,
 */