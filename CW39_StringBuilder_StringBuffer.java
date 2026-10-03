public class CW39_StringBuilder_StringBuffer {
    public static void main(String[] args) {
        // StringBuilder and StringBuffer functions are same. 
        // Only difference is that all methods in StringBuffer are synchronized and in StringBuilder, it is not.
        // - StringBuffer = thread-safe, StringBuilder = thread-unsafe.

        // Initialization ways
        // -------------------
        // | StringBuilder()
        // | StringBuilder(int capacity)
        // | StringBuilder(String str)
        // | StringBuilder(CharSequence seq)
        StringBuilder sb_default_const = new StringBuilder(); // default capacity is 16
        StringBuilder sb_with_string = new StringBuilder("Hello"); // capacity = 16 + 5 = 21
        StringBuilder sb_with_capacity = new StringBuilder(5); // capacity = 5 + 16 = 21
        System.out.println("sb_default_const:");
        System.out.println("\tcapacity:" + sb_default_const.capacity()); // 16
        System.out.println("\tlength:" + sb_default_const.length()); // 0
        System.out.println("sb_with_string:");
        System.out.println("\tcapacity:" + sb_with_string.capacity()); // 21
        System.out.println("\tlength:" + sb_with_string.length()); // 5
        System.out.println("sb_with_capacity:");
        System.out.println("\tcapacity:" + sb_with_capacity.capacity()); // 21
        System.out.println("\tlength:" + sb_with_capacity.length()); // 0
        
        System.out.println();
        
        // append() / insert() / delete() / deleteCharAt() / replace() / reverse()
        // -----------------------------------------------------------------------
        
        // PTR: all the above functions return StringBuilder, hence can be chained

        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World"); // append string to end of StringBuilder
        System.out.println("sb.append(): " + sb); // Hello World
        sb.insert(11, "."); // insert string at index 11
        System.out.println(". insert: " + sb); // Hello World.

        sb.append(" ").append("Yo!!").insert(0, "Hey! ");
        System.out.println("sb.append().insert(): " + sb); // Hey! Hello World. Yo!!
        
        System.out.println();

        // sb = "Hey! Hello World. Yo!!" till here
        System.out.println("sb.delete(): " + sb.delete(0, 5)); // delete from index 0 to 5 (exclusive) // Hello World. Yo.
        sb.deleteCharAt(sb.length() - 1).delete(0, 6); // delete last character & delete from index 0 to 6 (exclusive)
        System.out.println("sb.deleteCharAt().delete(): " + sb); // World. Yo!

        sb.replace(0, 5, "Hello"); // replace from index 0 to 5 (exclusive) with "Hello"
        System.out.println("sb.replace(): " + sb); // Hello. Yo!

        sb.reverse(); // reverse the string
        System.out.println("sb.reverse(): " + sb); // !oY .olleH

        System.out.println();

        // NOT SAFE: equals() and == : use compareTo()
        // -------------------------------------------
        // equals() in StringBuider compares references, not values -> so use them as String for comparison
        StringBuilder sb1 = new StringBuilder("Hello");
        StringBuilder sb2 = new StringBuilder("Hello");
        System.out.println("\tStringBuilder(Hello).equals(StringBuilder(Hello)): " + sb1.equals(sb2)); // false, because StringBuilder doesn't override equals() method
        System.out.println("\tStringBuilder(Hello).toString().equals(StringBuilder(Hello).toString()): " + sb1.toString().equals(sb2.toString())); // true, because String class overrides equals() method
        
        System.out.println();

        // SAFE: compareTo()
        // -----------------
        // compareTo() can be used for StringBuilder comparison
        System.out.println("\tStringBuilder(Hello).compareTo(StringBuilder(Hello)): " + sb1.compareTo(sb2)); // 0
        System.out.println("\tStringBuilder(hello).compareTo(StringBuilder(Hello)): " + new StringBuilder("hello").compareTo(sb2)); // 32
        // StringBuilder("hello").compareToIgnoreCase(sb2) // variation not available
        // StringBuilder("hello").equalsIgnoreCase(sb2) // variation not available
        
        System.out.println();

        // charAt() / setCharAt()
        // ----------------------
        StringBuilder sb3 = new StringBuilder("Hello World");
        System.out.println("For string builder:" + sb3);
        System.out.println("\tsb.charAt(0): " + sb3.charAt(0)); // H
        sb3.setCharAt(0, 'h'); // set character at index 0 to 'h'
        System.out.println("\tsb.setCharAt(): " + sb3); // hello World

        System.out.println();
        
        // indexOf(), indexOf(str, fromIndex), lastIndexOf(), lastIndexOf(str, fromIndex)
        // -------------------------------------------------------------------------------
        System.out.println("indexOf() and lastIndexOf():");
        System.out.println("\tsb.indexOf(\"o\"): " + sb3.indexOf("o")); // 4
        System.out.println("\tsb.indexOf(\"o\", 5): " + sb3.indexOf("o", 5)); // 7
        System.out.println("\tsb.lastIndexOf(\"o\"): " + sb3.lastIndexOf("o")); // 7
        System.out.println("\tsb.lastIndexOf(\"o\", 6): " + sb3.lastIndexOf("o", 6)); // 4

        System.out.println();
        
        // substring()
        // -----------
        // substring(startIndex, [endIndex EXCLUSIVE])
        System.out.println("\tsb.substring(0,5): " + sb3.substring(0, 5)); // Hello
        System.out.println("\tsb.substring(6): " + sb3.substring(6)); // World
        
        System.out.println();

        // setLength() / capacity() / length()
        // -----------------------------------
        sb3.setLength(5); // set length to 5, truncates the string to "hello"
        System.out.println("\tsb.setLength(5): " + sb3); // hello
        // sb3.charAt(6) // StringIndexOutOfBoundsException: Index 6 out of bounds for length 5
        System.out.println("\tsb.length(): " + sb3.length()); // 5
        System.out.println("\tsb.capacity(): " + sb3.capacity()); // 27, reamins same
        // PTR: setLength(50) would result in string[5...50] to be filled with nul aka \0. Also, the capacity increases.
        // sb3.setLength(50);
        // System.out.println("\tsb.setLength(50): " + sb3); // hello followed by 45 null characters
        // System.out.println("\tsb.length(): " + sb3.length()); // 50
        // System.out.println("\tsb.capacity(): " + sb3.capacity()); // 56

        // trimToSize() - reduces capacity to match current length

        // toString() - converts sb to string
        
        // PTR: replace() at particular index, reverse(), setLength() and setCharAt() are not available in String.class.
        
        // Memory and Performance Tips:
        // 1) Use StringBuilder for multiple concatenations in loops or complex operations
        // 2) Initial Capacity: Set appropriate initial capacity to avoid frequent resizing
        // 3) Trim to Size: Reduce memory footprint after building
        //  - sb.trimToSize(); // Reduces capacity to current length
        // 4) Reuse StringBuilder: Clear and reuse for multiple operations
        //  - sb.setLength(0); // Clear content, keep capacity

        // | Feature           | String                  | StringBuilder                          | StringBuffer                         |
        // |------------------ |-------------------------|----------------------------------------|--------------------------------------|
        // | **Mutability**    | Immutable               | Mutable                                | Mutable                              |
        // | **Thread Safety** | Thread-safe             | Not thread-safe                        | Thread-safe                          |
        // | **Performance**   | Slow for concatenations | Fast                                   | Moderate                             |
        // | **Memory Usage**  | High (new obj creation) | Low                                    | Low                                  |
        // | **When to Use**   | Few modifications       | Single-threaded frequent modifications | Multi-threaded frequent modifications|


        /**
         * Definitions:
         * StringBuilder: A mutable sequence of characters that allows strings to be
         * modified without creating new objects for each change; it is not thread-safe
         * and is generally faster than StringBuffer.
         * 
         * StringBuffer: A mutable, thread-safe sequence of characters whose methods are
         * synchronized, making it suitable for use by multiple threads accessing the
         * same instance.
         */
    }
}

/**
 * Output:
 * -------
 * sb_default_const:
 * 	capacity:16
 * 	length:0
 * sb_with_string:
 * 	capacity:21
 * 	length:5
 * sb_with_capacity:
 * 	capacity:5
 * 	length:0
 * 
 * sb.append(): Hello World
 * . insert: Hello World.
 * sb.append().insert(): Hey! Hello World. Yo!!
 * 
 * sb.delete(): Hello World. Yo!!
 * sb.deleteCharAt().delete(): World. Yo!
 * sb.replace(): Hello. Yo!
 * sb.reverse(): !oY .olleH
 * 
 * 	StringBuilder(Hello).equals(StringBuilder(Hello)): false
 * 	StringBuilder(Hello).toString().equals(StringBuilder(Hello).toString()): true
 * 
 * 	StringBuilder(Hello).compareTo(StringBuilder(Hello)): 0
 * 	StringBuilder(hello).compareTo(StringBuilder(Hello)): 32
 * 
 * For string builder:Hello World
 * 	sb.charAt(0): H
 * 	sb.setCharAt(): hello World
 * 
 * indexOf() and lastIndexOf():
 * 	sb.indexOf("o"): 4
 * 	sb.indexOf("o", 5): 7
 * 	sb.lastIndexOf("o"): 7
 * 	sb.lastIndexOf("o", 6): 4
 * 
 * 	sb.substring(0,5): hello
 * 	sb.substring(6): World
 * 
 * 	sb.setLength(5): hello
 * 	sb.length(): 5
 * 	sb.capacity(): 27
 */
