import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Lambda_FunctionalInterface {

    @FunctionalInterface
    interface Greeting {
        void sayHello(String name); // only abstract method

        default void intro() {
            System.out.println("Default method in Functional Interface is allowed");
        }

        static void utility() {
            System.out.println("Static method in Functional Interface is allowed");
        }
    }

    @FunctionalInterface
    interface MathOperation {
        int apply(int firstNumber, int secondNumber);
    }

    @FunctionalInterface
    interface StringFormatter {
        String format(String input);
    }

    public static void main(String[] args) {
        // ref: https://www.geeksforgeeks.org/java/lambda-expressions-java-8/

        // Def - Lambda expression is a concise way to represent an anonymous function.
        // Features:
        // - It gives an implementation for a functional interface.
        // - No separate implementation class is needed.
        // - It lets you pass behavior/code as data, assign it to variables, resulting in clean and readable code.
        //  - Functional Programming: Treat functions as first-class citizens.
        // - Enhanced Collections and Streams: Simplify operations like filtering, mapping, and iterating.
        
        // Def - Functional interface is an interface with exactly one abstract method.
        // Features:
        // - It can have default, and static methods. FYI, interface can never have concrete method.
        // - It serves as a target type for lambda expressions, enabling them to be used where a functional interface is expected.
        // - @FunctionalInterface is optional in Java.
        //  - It is recommended for compile-time safety (compiler enforces single abstract method rule).
        //  - Even without it, an interface is functional if it has exactly one abstract method.
        //  - It serves as clear documentation for other developers, indicating the interface's intended purpose.
        
        // Relation between lambda and functional interface:
        // - Lambda itself has no standalone type.
        // - Lambda needs a target type, and that target type is usually a functional interface.
        // - So, lambda is the implementation; functional interface is the contract.

        // Lambda syntax:
        // (0 or more parameters) -> expression
        // (0 or more parameters) -> { statements; }

        MathOperation addOperation = (a, b) -> a + b;
        MathOperation maxOperation = (a, b) -> {
            if (a > b) {
                return a;
            }
            return b;
        };
        System.out.println("Lambda style 1 - addOperation.apply(5, 7) = " + addOperation.apply(5, 7));
        System.out.println("Lambda style 2 - maxOperation.apply(5, 7) = " + maxOperation.apply(5, 7));

        System.out.println();

        // Functional interface defined as Lambda
        // Example 1
        Greeting greeting = name -> System.out.println("Example 1 - Functional interface defined as Lambda: Hello, " + name + "!");
        greeting.sayHello("Aman");
        greeting.intro(); // default method allowed
        Greeting.utility(); // static method allowed
        
        System.out.println();

        // Example 2
        List<Integer> nums = Arrays.asList(5, 1, 4, 2);
        nums.sort((a, b) -> Integer.compare(a, b));
        System.out.println("Example 2 - Functional interface defined as Lambda: nums:" + nums); // [1, 2, 4, 5]

        System.out.println();

        // Example 3
        StringFormatter trimAndUpperCase = text -> text.trim().toUpperCase();
        String result = trimAndUpperCase.format("   string trimed and uppercased using lambda   ");
        System.out.println("Example 3 - Functional interface defined as Lambda: StringFormatter (in-built): " + result);

        // A method can receive behavior/code via functional interface parameter.
        executeFormatter("   passed StringFormatter (functional interface) = trim&UpperCase as code   ", trimAndUpperCase);
        executeFormatter("   passed custom lambda defined on the fly as code   ", value -> "[" + value.trim() + "]"); // defining lambda on the fly

        System.out.println();

        /* Built-in @FunctionalInterfaces */
        /**
         * Predicate    | boolean test(T t) | filtering/condition by returning true or false.
         * Function     | R apply(T t)      | Transforms an input of type T to an output of type R.
         * Consumer     | void accept(T t)  | Consume/print/store - Performs an action on an input of type T without returning a result.
         * Supplier     | T get()           | provide value lazily - Generates a value of type T without taking any input.
         * Comparator   | int compare(T o1, T o2) | Compares two objects and returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
         * Comparable   | int compareTo(T o) | Compares the current object with another object of the same type and returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
         * Runnable     | void run()         | Represents a task that can be executed concurrently by a thread.
         */

        System.out.print("Using lambda in stream() to filter(), map() and forEach(): ");
        List<String> names = Arrays.asList("alice", "bob", "charlie", "anna");
        // beauty is we don't need to know the classes behind these lambda expressions for stream to work.
        names.stream()
                .filter(name -> name.startsWith("a")) // Predicate
                .map(s -> s.toUpperCase()) // Function // or String::toUpperCase
                .forEach(s -> System.out.print(s + ", ")); // Consumer

        // PTR: Local variable used in lambda should be final or effectively final.
        int multiplier = 2; // should be effectively final
        Function<Integer, Integer> doubler = value -> value * multiplier;
        // multiplier = 3; // compile-time error: variable used in lambda expression should be final or effectively final

        // PTR: 
        // - lambda != anonymous class.
        // - Latest Java 24 refrains the use of lambda due to lack of debugging support. Reason: 
    }

    private static void executeFormatter(String input, StringFormatter formatter) {
        System.out.println("executeFormatter -> " + formatter.format(input));
    }
}

/**
 * Output:
 * -------
 * Lambda style 1 - addOperation.apply(5, 7) = 12
 * Lambda style 2 - maxOperation.apply(5, 7) = 7
 * 
 * Example 1 - Functional interface defined as Lambda: Hello, Aman!
 * Default method in Functional Interface is allowed
 * Static method in Functional Interface is allowed
 * 
 * Example 2 - Functional interface defined as Lambda: nums:[1, 2, 4, 5]
 * 
 * Example 3 - Functional interface defined as Lambda: StringFormatter (in-built): STRING TRIMED AND UPPERCASED USING LAMBDA
 * executeFormatter -> PASSED STRINGFORMATTER (FUNCTIONAL INTERFACE) = TRIM&UPPERCASE AS CODE
 * executeFormatter -> [passed custom lambda defined on the fly as code]
 * 
 * Using lambda in stream() to filter(), map() and forEach(): ALICE, ANNA, 
 */