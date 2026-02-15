import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CW68_Generics_3_TypeErasureAndGenericExceptions {
    public static void main(String[] args) {
        /* Type Erasure */
        // Type erasure: is a process by which the Java compiler removes all generic type information during compilation. 
        // This means that at runtime, the generic types are not available, and all generic types are treated as their raw types (e.g., List<T> becomes List). 
        // Type erasure allows for backward compatibility with older versions of Java that do not support generics.

        /* Type Erasure - Example 1 */
        class Box<T> {
            private T value;
            
            public void set(T value) {
                this.value = value;
            }

            public T get() {
                return value;
            }
        }
        Box<String> strBox = new Box<>();
        strBox.set("Hello");
        String value = strBox.get();
        // At compilation, T is replaced with Object since unbounded:
        /**
         * class Box {
         *    private Object value;
         *    public void set(Object value) {
         *       this.value = value;
         *    }
         *    public Object get() {
         *       return value;
         *    }
         * }
         * 
         * Box strBox = new Box();
         * strBox.set("Hello");
         * String value = (String) strBox.get();
         */

        /* Type Erasure - Example 2 */
        class NumberBox<T extends Number> {
            private T value;
            
            public void set(T value) {
                this.value = value;
            }

            public T get() {
                return value;
            }
        }
        NumberBox<Integer> intBox = new NumberBox<>();
        intBox.set(123);
        Integer intValue = intBox.get();

        // At compilation, T is replaced with Number since bounded:
        /**
         * class NumberBox {
         *    private Number value;
         *    public void set(Number value) {
         *       this.value = value;
         *    }
         *    public Number get() {
         *       return value;
         *    }
         * }
         * 
         * NumberBox intBox = new NumberBox();
         * intBox.set(123);
         * Integer intValue = (Integer) intBox.get();
         */

        /* Type erasure - T super Number */
        // PTR: The super keyword is only allowed with wildcards (?)
        // [CORRECT] T extends Number, ? extends Number, ? super Number
        // [WRONG] T super Number
        // Reason: In a class definition, a super bound would effectively mean the type
        // could be Object (the ultimate supertype of all reference types), which
        // provides no meaningful restriction beyond using raw types.
        
        // IMP: Usage of ?: The wildcard character is used as a special type argument when
        // declaring variables, method parameters and return types of a generic type, but not
        // in the definition of the generic type itself.
        
        // For class definition, ? doesn't make sense:
        // Wildcard ? is too vague: ? means "some unknown type," but it doesn't specify what that type is or its bounds. 
        // For example:
        // - You couldn't define fields like private ? value; (what type is it?).
        // - Methods like public ? get() { return value; } wouldn't compile because the return type is unknown.
        // - The class would be unusable — how would you instantiate new Box<?>()? The compiler needs to know the exact type to enforce safety.
        // => for class definition only "T extends XYZ" is possible

        /* Generic Exceptions */
        // Java doesn't support generic exception due to type erasure. 
        // Since exceptions are closely tied to runtime operations like catching them in try-catch blocks, having generic exceptions wouldn't work as expected.
        // Say, you had GenericException<T>, you wouldn't be able to catch it with a specific type
        // param because the type info would be erased at runtime.
        
        // Non-effective sol.: create type specific exceptions - StringProcessingException extends Exception, IntegerProcessingException extends Exception
        
        // Better solution: create a generic constructor for your custom exception
        class MyException extends Exception {
            public <T> MyException(T value) {
                // super(message); // standard procedure while creating custom exception
                super("Exception related to value: " + value.toString() + " of type: " + value.getClass().getName());
            }
        }

        try {
            throw new MyException(123);
        } catch (MyException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            throw new MyException("Hello Knull");
        } catch (MyException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}

/**
 * Output:
 * -------
 * Caught exception: Exception related to value: 123 of type: java.lang.Integer
 * Caught exception: Exception related to value: Hello Knull of type: java.lang.String
 */