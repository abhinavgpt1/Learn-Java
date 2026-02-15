import java.util.ArrayList;

public class CW66_Generics_1_IntroAndBoundedTypes {
    public static void main(String[] args) {
        /* History */
        // Introduced in Java 5, generics allow you to create classes, interfaces, and
        // methods that can operate on any type of data while providing type safety at compile time.

        ArrayList objList = new ArrayList();
        objList.add("Hello");
        objList.add(123);
        objList.add(3.14);

        // String str = objList.get(0); // error: incompatible types: Object cannot be converted to String.
        // String int1 = (String) objList.get(1); // error: cannot cast Integer to String.

        // Manual cast is required and type safety is not guaranteed. Therefore generics was introduced.

        Object obj1 = objList.get(0); // Raw type ArrayList contains objects of class Object (universal superclass of all classes in java).
        System.out.println("obj1: " + obj1);

        System.out.println("------------------");
        
        /* Basics */
        // Generics allow you to specify the type of data that a class, interface, or method can operate on, providing type safety at compile time. This removes the need for manual casting.
        ArrayList<String> strList = new ArrayList<String>();
        strList.add("Hello");
        // strList.add(123); // errors out since we can add strings only
        // strList.add(3.14);
        // PTR: Latest java version allows to omit type param on RHS. eg. ArrayList<String> strList = new ArrayList<>(); // diamond operator

        String str1 = strList.get(0); // No need for manual cast, type safety is guaranteed at compile time.
        System.out.println("str1: " + str1);
        
        // PTR: Problems before Generics
        // 1. No type safety: You could add any type of object to a collection, which could lead to runtime errors when you tried to retrieve and use those objects.
        // 2. Manual casting: You had to manually cast objects when retrieving them from a collection, which was error-prone and could lead to ClassCastException at runtime if the wrong type was cast.
        // 3. No compile time checking
        
        System.out.println("------------------");

        /* Create a generic class */
        // Generics allow you to define a class, interface, or method with placeholders (type parameters) for the data types they will work with.
        // Template:
        /**
         * class ClassName<T> {
         *  // specify any param with type T as - T var;
         *  // This T spreads across all variables, methods and constructors at compile time. 
         *  // PTR: T is just a convention. You can use a letter or word.
         *  // PTR: At runtime, type erasure occurs, and the type parameter T is replaced with its bound (if specified) or Object (if no bound is specified). This means that the generic type information is not available at runtime, and all generic types are treated as Object.
         * }
         */
        class Box<T> {
            private T value;

            Box(T v) {
                this.value = v;
            }

            void setValue(T v) {
                this.value = v;
            }

            T getValue() {
                return this.value;
            }
        }

        Box<Integer> intBox = new Box<>(123); // Box is type safe.
        System.out.println("intBox value: " + intBox.getValue());

        // Generics help to create reusable code. Say we were to create an int box, float, string, etc boxes then writing separate classes for each type would be redundant.
        System.out.println("------------------");

        /* Multiple param */
        class Pair<K, V> {
            private K key;
            private V value;

            Pair(K k, V v) {
                this.key = k;
                this.value = v;
            }

            K getKey() {
                return this.key;
            }

            V getValue() {
                return this.value;
            }
        }

        Pair<String, Integer> pair = new Pair<>("Age", 30);
        System.out.println("pair key: " + pair.getKey());
        System.out.println("pair value: " + pair.getValue());

        // Convention says:
        // 1. T: Type
        // 2. E: Element (used in collections)
        // 3. K: Key (used in maps)
        // 4. V: Value (used in maps)
        // 5. N: Number (used in numeric contexts)
        System.out.println("------------------");

        /* Generic Interface */
        // Say you have a generic interface. You can either implement it with a specific type or make the implementing class generic as well.
        interface Container<T> {
            void set(T item);
            T get();
        }
        // Use 1: Implementing with specific type
        class StringContainer implements Container<String> {
            private String item;

            @Override
            public void set(String item) {
                this.item = item;
            }

            @Override
            public String get() {
                return this.item;
            }
        }
        // Use 2: Implementing with generic type. T needs to be mentioned aside GenericContainer. This will spread everywhere in the class.
        class GenericContainer<T> implements Container<T> {
            private T item;

            @Override
            public void set(T item) {
                this.item = item;
            }

            @Override
            public T get() {
                return this.item;
            }
        }
        StringContainer sc = new StringContainer();
        sc.set("StringContainerValue");
        System.out.println("StringContainer item: " + sc.get());

        GenericContainer<String> gc = new GenericContainer<>();
        gc.set("GenericContainerValue");
        System.out.println("GenericContainer item: " + gc.get());
        System.out.println("------------------");

        /* Bounded Type Parameters */
        // We can put condition on T - either extends (upper bound) or super (lower bound).
        // IMP: There is no "implements"
        // Using N as per convention for number types.
        class NumberBox<N extends Number> { // N can be any subclass of Number (Integer, Double, etc) but not String or Object.
            private N value;

            NumberBox(N v) {
                this.value = v;
            }

            N getValue() {
                return this.value;
            }
        }
        // NumberBox<String> stringBox = new NumberBox<>("Hello"); // Bound mismatch: The type String is not a valid substitute for the bounded parameter <T extends Number> of the type NumberBox<T>
        // NumberBox<Integer> intBox2 = new NumberBox<>(); // error: there is no default constructor provided by compiler if a constructor is defined.
        NumberBox<Integer> intBox2 = new NumberBox<>(1234);
        System.out.println("Bounded NumberBox intBox2 value: " + intBox2.getValue());
        System.out.println("------------------");

        /* Multiple Bounds */
        // PTR: There can be multiple bounds on T as well.

        /**
         * class Box <T extends Class & Interface1 & Interface2 & Interface3 & ...>
         */
        // - Meaning: While creating object of Box, T should be a subclass of Class and should implement Interface1 and Interface2 and Interface3.
        // - Rules
        //  + First bound needs to be a class followed by zero or more interfaces, or,
        //  + First bound can be interface, with no class present after &.
        //  + Since multiple inh. is allowed for interfaces, we can specify multiple interfaces after &, but the same isn't possible for classes.

        // Recall: There isn't implements in conditions
        // [WRONG] class Box <T implements Interface1> - We use extends for both classes and interfaces in generics.
        // - [CORRECT] class Box <T extends Interface1>

        // tldr; Generics provide type safety at compile time, eliminate the need for manual casting, and allow for code reusability. They can be used with classes, interfaces, and methods, and can have multiple type parameters and bounds to specify constraints on the types that can be used.

        /* Generic Constructor */
        // It isn't necessary for class to be generic to have a generic constructor.
        class GenericConstructor {

            // IMP: specify <T> before class name in constructor. Same convention is followed for generic methods.
            // There can be multiple params & T can be bounded here as well. eg. <T extends Number, V extends Comparable<V>>
            public <T> GenericConstructor(T item) { // T is specific to constructor and doesn't spread across class.
                System.out.println("GenericConstructor item: " + item);
            }
        }

        /* Generic Methods */
        // Similar to generic constructor, it differs by return type. Constructor doesn't have return type but method has.
        class GenericMethod {
            // syntax: AccessSpecifier <T> returnType methodName(T param)
            public <T> void printArray(T[] arr) {
                System.out.print("Generic method printArray: ");
                for (T item : arr) {
                    System.out.print(item + " ");
                }
                System.out.println();
            }
        }
        GenericMethod gm = new GenericMethod();
        Integer[] intArr = {1, 2, 3, 4, 5};
        gm.printArray(intArr);
        gm.printArray(new String[]{"hello", "world", "how", "you", "doin'?"});
        // PTR:
        // <T> ConstructorName (T param) - Generic constructor
        // <T> returnType methodName(T param) - Generic method

        // Syntax for static generic method
        /**
         * static <T> returnType methodName(T param)
         */ 
        // Above example will look like
        /**
         * public static <T> void printArray(T[] arr)
         */

        System.out.println("------------------");

        /* Method overloading in generic methods */
        class OverloadingGenericMethod {
            public <T> void print(T item) {
                System.out.println("Generic method (overloaded) print: " + item);
            }

            public void print(Integer item) { // This is not an overloaded method since erasure of above method will also be print(Object item). Therefore this will give compile time error: name clash: print(T) and print(String) have the same erasure.
                System.out.println("Integer method print: " + item);
            }
        }
        OverloadingGenericMethod ogm = new OverloadingGenericMethod();
        ogm.print("Hello"); // Calls print(T)
        ogm.print(123); // Calls print(Integer)
        System.out.println("------------------");

        /* Generic in Enums */
        // PTR: Enums are type safe already. eg. Day day = 1; WRONG; Day day = Day.MONDAY; CORRECT;
        // So, there isn't a generic enum. We can use a generic method in enum though.
        enum Operation {
            ADD, SUBTRACT, MULTIPLY, DIVIDE;
            // making it public isn't necessary.
            <T extends Number> double apply (T a, T b) {
                switch(this) {
                    case ADD: return a.doubleValue() + b.doubleValue();
                    case SUBTRACT: return a.doubleValue() - b.doubleValue();
                    case MULTIPLY: return a.doubleValue() * b.doubleValue();
                    case DIVIDE: return a.doubleValue() / b.doubleValue();
                    default: throw new UnsupportedOperationException("Unknown operation: " + this);
                }
            }
        }
        System.out.println("Generic operation method - ADD(10,5) result: " + Operation.ADD.apply(10, 5));
        System.out.println("Generic operation method - MULTIPLY(10,5) result: " + Operation.MULTIPLY.apply(10, 5));
    }
}

/**
 * Output:
 * -------
 * obj1: Hello
 * ------------------
 * str1: Hello
 * ------------------
 * intBox value: 123
 * ------------------
 * pair key: Age
 * pair value: 30
 * ------------------
 * StringContainer item: StringContainerValue
 * GenericContainer item: GenericContainerValue
 * ------------------
 * Bounded NumberBox intBox2 value: 1234
 * ------------------
 * Generic method printArray: 1 2 3 4 5 
 * Generic method printArray: hello world how you doin'? 
 * ------------------
 * Generic method (overloaded) print: Hello
 * Integer method print: 123
 * ------------------
 * Generic operation method - ADD result: 15.0
 * Generic operation method - MULTIPLY result: 50.0
 */