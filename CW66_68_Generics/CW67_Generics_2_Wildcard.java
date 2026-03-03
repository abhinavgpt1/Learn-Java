import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CW67_Generics_2_Wildcard {
    public static void main(String[] args) {
        /* Wildcard in generics */
        // Wildcard is used to represent an unknown type in generics. 
        // They allow for more flexible and dynamic code by letting the type be specified later or be loosely defined.

        // Say, you have a generic method which iterates over ArrayList and prints every element
        /**
         * public void <T> printArray(ArrayList<T> list) {...}
         */
        // In short, there is only read operation going on, so we can use ? instead of T.
        /**
         * public void printArray(ArrayList<?> list) {...}
         */
        // Note: No <?> in method signature in wildcard generic method

        // Now, say you have `public void getFirst(ArrayList<?> list) {return list.get(0);}`
        // - this method will return Object. This can be a type safety problem in the method receiving the return value.
        // - Why type safety issue?
        //  + When the caller receives an Object, they might expect a specific type (e.g., a String or Integer). This leads to Unsafe Casting: The caller might cast the returned Object to the expected type, like (String) getFirst(myStringList). If the list actually contains something else (though unlikely in this case), it could throw a ClassCastException at runtime.

        // So, thumb rule:
        // - if read operation, use ?. If return or write operation use T.

        // qq - can wildcard be added in following copy code?
        /**
         * public <T> void copy(ArrayList<T> source, ArrayList<T> destination) {
         *     for (T element : source) {
         *        destination.add(element);
         *    }
         * }
         */
        // ans - no. Source in public void copy(ArrayList<?> source, ArrayList<?> destination) {...} can be anything. Its type is unknown, and needs to be defined before adding to dest.
        // Recall: thumb rule - above function has write operations, so don't use ?.

        ArrayList<?> strList = new ArrayList<String>(); // this is fine. We can assign an ArrayList<String> to an ArrayList<?> because the wildcard allows for any type. However, we cannot add elements to strList because its type is unknown.
        // strList.add("Hello"); // error: cannot add to a collection of unknown type
        // strList.add(123); // error: cannot add to a collection of unknown type
        // strList.add(new Object()); // error: cannot add to a collection of unknown type
        // Recall: Thumb-rule - strList can be used for read-only operations. No write/add is possible.

        /* Wilcard method - Upper bound */
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.0);
        System.out.println("Wildcard lower bound - double array sum: " + wildcardLowerBoundArraySum(doubleList));
        List<Number> numberList = Arrays.asList(1, 1.1, 2.2, 3.0f); // it is Number array since it contains Integer, Double and Float
        System.out.println("Wildcard lower bound - Number array sum: " + wildcardLowerBoundArraySum(numberList));
        
        /* Wilcard method - Lower bound */
        List<Object> objList = new ArrayList<>();
        objList.add("Hello");
        objList.add(123);
        System.out.print("Wildcard upper bound - object array print: ");
        wildcardUpperBoundPrintArray(objList); // This works because Object is a super class of Integer

        // IMP:
        List<?> intList1 = Arrays.asList(1, 2, 3); // read-only array. no add() is possible
        
        List<? extends Number> wildcardExtendList = new ArrayList<>();
        wildcardExtendList.add(null); // null can be assigned to any type, hence this is fine.
        Number num = wildcardExtendList.get(0);

        // error: say wildcardExtendList is of type Double/Float, then adding Integer or Number would be wrong.
        // wildcardExtendList.add((Number)(new Integer(0)));
        // wildcardExtendList.add(new Integer(0));

        List<? super Integer> wildcardSuperList = new ArrayList<>();
        wildcardSuperList.add(null); // null can be assigned to any type, hence this is fine.
        Object obj = wildcardSuperList.get(0);
        Integer intObj = (Integer) wildcardSuperList.get(0);

        // error: say wildcardSuperList is of type Integer, then adding Object or Number would be wrong.
        // error: say wildcardSuperList is of type Object, then adding Integer is allowed. Adding Number seems right, but what if list is of type Integer, then adding Number would be wrong.
        // Hence only Integer (or its subtypes) can be added.
        // wildcardSuperList.add(obj);
        // wildcardSuperList.add(new Object());
        // wildcardSuperList.add((Number)(new Integer(0)));
        wildcardSuperList.add(new Integer(0)); // Integer can be added

        // small demo - adding object, number to Integer array
        ArrayList<Integer> i = new ArrayList<>();
        // i.add(new Object()); // error: Object cannot be added to ArrayList<Integer>
        // i.add((Number)(1.22)); // error: Number cannot be added to ArrayList<Integer>
        
        // small demo - adding integer, number to Object array
        ArrayList<Object> objArrList = new ArrayList<>();
        objArrList.add(new Object());
        objArrList.add((Number)(1.22));
        objArrList.add(new Integer(1));

        // TLDR;
        // "Producer Extends": 
        // - Use ? extends T when you only need to read (the list produces T). 
        // - You cannot add (except null).
        // "Consumer Super": 
        // - Use ? super T when you only need to write/consume T. 
        // - You can add T (and subtypes), but reads are only safe as Object or typecasted to T.

        // Check /* Type erasure - T super Number */ section of CW68_Generics_3_TypeErasureAndGenericExceptions.java to know more on wildcard incompatibility with class.
        // Also, why wildcard support both extends and super, meanwhile type T can extend but not super in class definition.
    }

    // Added an upper bound to the wildcard to restrict it to Number and its subclasses.
    static double wildcardLowerBoundArraySum(List<? extends Number> list) {
        double total = 0.0;
        for (Number num : list) {
            total += num.doubleValue(); // We can call methods of Number class on elements of the list
        }
        return total;
    }

    // Added lower bound - only super classes of Integer like Number and Object are allowed here.
    static void wildcardUpperBoundPrintArray(List<? super Integer> list) {
        for (Object obj : list) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }
}

/**
 * Output:
 * -------
 * Wildcard lower bound - double array sum: 7.0
 * Wildcard lower bound - Number array sum: 7.300000000000001
 * Wildcard upper bound - object array print: Hello 123 
 */