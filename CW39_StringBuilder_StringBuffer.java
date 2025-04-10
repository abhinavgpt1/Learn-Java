public class CW39_StringBuilder_StringBuffer {
    public static void main(String[] args) {
        // StringBuilder and StringBuffer functions are same. 
        // Only difference is that all methods in StringBuffer are synchronized and in StringBuilder it is not.
        // Initialization ways
        // -------------------
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

        // append, insert, delete, replace, reverse
        // ----------------------------------------
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World"); // append string to end of StringBuilder
        System.out.println("World append: " + sb); // Hello World
        sb.insert(11, "."); // insert string at index 11
        System.out.println(". insert: " + sb); // Hello World.

        sb.append(" ").append("Yo!!").insert(0, "Hey! ");// append and insert chained
        System.out.println("result after chaining append() and insert() together: " + sb); // Hey! Hello World. Yo!!
        
        System.out.println();

        System.out.println("delete(): " + sb.delete(0, 5)); // delete from index 0 to 5 (exclusive) // Hello World. Yo.
        sb.deleteCharAt(sb.length() - 1).delete(0, 6); // delete last character & delete from index 0 to 6 (exclusive)
        System.out.println("chaining deleteCharAt() and delete(): " + sb); // World. Yo!

        sb.replace(0, 5, "Hehe"); // replace from index 0 to 5 (exclusive) with "Hello"
        System.out.println("replace(): " + sb); // Hello. Yo!

        sb.reverse(); // reverse the string
        System.out.println("reverse(): " + sb); // !oY .olleH

        System.out.println();

        // equals() and compareTo()
        // ------------------------
        System.out.println("equals() in StringBuider compares references, not values -> so use them as String for comparison:");
        StringBuilder sb1 = new StringBuilder("Hello");
        StringBuilder sb2 = new StringBuilder("Hello");
        System.out.println("\tsb1.equals(sb2): " + sb1.equals(sb2)); // false, because StringBuilder doesn't override equals() method
        System.out.println("\tsb1.toString().equals(sb2.toString()): " + sb1.toString().equals(sb2.toString())); // true, because String class overrides equals() method
        
        System.out.println();

        System.out.println("compareTo():");
        System.out.println("\t" + sb1.compareTo(sb2)); // 0
        System.out.println("\t" + new StringBuilder("hello").compareTo(sb2)); // 32
        // System.out.println(new StringBuilder("hello").compareToIgnoreCase(sb2)); // variation not available
        // System.out.println(new StringBuilder("hello").equalsIgnoreCase(sb2)); // variation not available
        
        System.out.println();

        // charAt(), setCharAt()
        // ---------------------
        System.out.println("charAt() and setCharAt():");
        StringBuilder sb3 = new StringBuilder("Hello World");
        System.out.println("\tindex 0 char: " + sb3.charAt(0)); // H
        sb3.setCharAt(0, 'h'); // set character at index 0 to 'h'
        System.out.println("\tsetCharAt() at index 0: " + sb3); // hello World

        System.out.println();
        
        // indexOf(), lastIndexOf()
        // ------------------------
        System.out.println("indexOf() and lastIndexOf():");
        System.out.println("\tsb3.indexOf(\"o\"): " + sb3.indexOf("o")); // 4
        System.out.println("\tsb3.lastIndexOf(\"o\"): " + sb3.lastIndexOf("o")); // 7

        System.out.println();
        
        // substring()
        // -----------
        System.out.println("substring(startIndex, [non-inclusive endIndex]):");
        System.out.println("\t" + sb3.substring(0, 5)); // Hello
        System.out.println("\t" + sb3.substring(6)); // World
        
        System.out.println();

        // setLength()
        // -----------
        System.out.println("setLength():");
        sb3.setLength(5); // set length to 5, truncates the string to "Hello"
        System.out.println("\t length set to 5: " + sb3); // Hello
        System.out.println("\t length(): " + sb3.length()); // 5
        System.out.println("\t capacity (same): " + sb3.capacity()); // 21, capacity remains same

        // functions like - replace, reverse, setLength and setCharAt() are not available in String class.
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
 * World append: Hello World
 * . insert: Hello World.
 * result after chaining append() and insert() together: Hey! Hello World. Yo!!
 * 
 * delete(): Hello World. Yo!!
 * chaining deleteCharAt() and delete(): World. Yo!
 * replace(): Hehe. Yo!
 * reverse(): !oY .eheH
 * 
 * equals() in StringBuider compares references, not values -> so use them as String for comparison:
 * 	sb1.equals(sb2): false
 * 	sb1.toString().equals(sb2.toString()): true
 * 
 * compareTo():
 * 	0
 * 	32
 * 
 * charAt() and setCharAt():
 * 	index 0 char: H
 * 	setCharAt() at index 0: hello World
 * 
 * indexOf() and lastIndexOf():
 * 	sb3.indexOf("o"): 4
 * 	sb3.lastIndexOf("o"): 7
 * 
 * substring(startIndex, [non-inclusive endIndex]):
 * 	hello
 * 	World
 * 
 * setLength():
 * 	 length set to 5: hello
 * 	 length(): 5
 * 	 capacity (same): 27
 */
