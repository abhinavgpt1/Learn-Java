import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CW38_String5_utilFunctions {
    public static void main(String args[]) {
        // trim() and strip()
        // ------------------
        String str_trim = "  Hello World  ";
        System.out.println("\"  Hello World  \" trimmed: " + str_trim.trim());
        // PTR: use strip() to remove unicode whitespace 
        // eg. String unicodeString = "\u2005Hello, World!\u2005"; 
        // unicodeString.trim(): ' Hello, World! '
        // unicodeString.strip(): 'Hello, World!'
        
        // concat()
        // --------
        // New reference is created every time we concatenate since Strings are immutable.
        String hello = "Hello";
        String to = "To";
        String the = "The";
        String world = "World";
        System.out.println("Concatenated String: " + hello.concat(to).concat(the).concat(world));
        // String concatenation is very low performance because of creation and deallocation of multiple String objects.
        // using + operator acts as "syntactic sugar" for string concatenation.
        // In programming, "syntactic sugar" refers to syntax that makes code easier to read and write without changing its underlying functionality
        
        // Use StringBuilder in case of loops and Streams when high number of strings are to be concatenated.
        String listStr[] = new String[] { hello, to, the, world };
        String streamOutput1 = Arrays.stream(listStr).collect(Collectors.joining(","));
        System.out.println("Arrays.stream(listStr).collect(Collectors.joining(\",\")): " + streamOutput1);
        String streamOutput2 = Stream.of(listStr).collect(Collectors.joining(",")); // uses Arrays.stream internally
        System.out.println("Stream.of(listStr).collect(Collectors.joining(\",\")): " + streamOutput2);

        StringBuilder strbuilder_concat_append = new StringBuilder();
        for (String str : listStr) {
            strbuilder_concat_append.append(str).append(",");
        }
        System.out.println("strbuilder_concat_append: " + strbuilder_concat_append.toString()); // Hello,To,The,World, <-- note the trailing comma
        
        System.out.println();

        // contains()
        // ----------
        // syntax: public boolean contains(CharSequence chars)
        // - internally uses indexOf(chars) >= 0;
        String helloWorld = "Hello World";
        System.out.println("For string: " + helloWorld);
        System.out.println("\thelloWorld.contains(\"Hello\"): " + helloWorld.contains("Hello"));
        
        // startsWith() / endsWith()
        // -------------------------
        // syntax: public boolean startsWith(String chars)
        System.out.println("\thelloWorld.startsWith(\"Hello\"): " + helloWorld.startsWith("Hello")); //true
        System.out.println("\thelloWorld.startsWith(\"W\", 6): " + helloWorld.startsWith("W", 6)); // true
        System.out.println("\thelloWorld.endsWith(\"d\"): " + helloWorld.endsWith("d")); // true
        // helloWorld.endsWith("o", 4) // Invalid: endsWith has no offset overloaded function

        // replace() / replaceAll() / replaceFirst()
        // ------------------------------------------
        System.out.println("For string: " + hello);
        System.out.println("\tHello.replace(char, char): " + hello.replace('l', 'L')); // HeLLo // replace all occurrences of 'l' with 'L'
        System.out.println("\tHello.replace(\"ll\", \"LLB\"): " + hello.replace("ll", "LLB")); // HeLLBo // replace all occurrences of 'll' with 'LLB'
        System.out.println("\tHello.replaceAll(\"[aeiou]\", \"X\"): " + hello.replaceAll("[aeiou]", "X")); // HXllX // replace all occurrences of regex with 'X'
        System.out.println("\tHello.replaceFirst(\"[aeiou]\", \"X\"): " + hello.replaceFirst("[aeiou]", "X")); // HXllo // replace first occurrence of regex with 'X'
        // Note: replaceAll() and replaceFirst() are slower than replace() because they use regex.

        System.out.println();
        
        // toCharArray()
        // -------------
        char helloWorldCharArr[] = helloWorld.toCharArray();
        System.out.println("toCharArray(): " + Arrays.toString(helloWorldCharArr)); // [H, e, l, l, o,  , W, o, r, l, d]
        
        // getChars()
        // ----------
        char charAllAs[] = new char[10];
        Arrays.fill(charAllAs, 'a'); // fill with 'a'
        helloWorld.getChars(6, 11, charAllAs, 2); // copies src[6...10] "World" into charAllAs[2...6]
        System.out.println("getChars(): " + Arrays.toString(charAllAs)); // [a, a, W, o, r, l, d, a, a, a] // srcEnd not counted

        System.out.println();
        
        // format()
        // --------
        // Creates a formatted string using the specified format string and arguments. 
        // We can concatenate, format using options such as width, alignment, decimal places, and more.
        // https://www.geeksforgeeks.org/java/java-string-format-method-with-examples/
        
        // Example 1
        String formattedString = String.format("%.2f | %10s | %-5d|extra", 123.4567, "Hello", 42);
        System.out.println("Formatted string output 1: " + formattedString); // 123.46 |      Hello | 42   |extra
        // Explanation:
        // %.2f: The f specifier is for floating-point numbers, and .2 means the number
        // should be rounded to two decimal places.
        // %10s: The s specifier is for strings, and 10 means the string should be
        // right-aligned within a field of width 10. If the string is shorter than 10
        // characters, it will be padded with spaces on the left.
        // %-5d: The d specifier is for integers, and -5 means the integer should be
        // left-aligned within a field of width 5. If the integer is shorter than 5
        // digits, it will be padded with spaces on the right.

        // Example 2
        double d2 = 1200345.6789;
        // Format the price with thousands separator and two decimal places
        String s2 = String.format("%1$,10.2f", d2); // 1,200,345.68
        System.out.println("Formatted string output 2: " + s2);
        // Explanation:
        // %1$: It refers to the first argument (d), the price value.
        // ,: It groups digits with a comma as a thousands separator.
        // 10.2f: It ensures that the floating-point number takes at least 10 characters, with 2 decimal places.
        
        // Note: If the number is shorter than 10 characters, it will be right-aligned and padded with spaces on the left.

        // Example 3
        double d3 = 150.75;
        String s3 = "kilometers";
        String res = String.format("%1$,7.1f %2$s", d3, s3);
        System.out.println("Formatted string output 3:" + res); // one extra space before 150.8 since "150.8" is 6 characters long and we specified minimum width of 7
        // %1$: It refers to the first argument.
        // ,: Groups digits with a comma as a thousands separator.
        // 7.1f: This ensures that the floating-point number takes at least 7 characters in total, with 1 decimal place.
        // %2$s: Refers to the second argument i.e. "d" the distance. "s" is the format specifier for strings.

        System.out.println();

        // Extra examples
        // specifier for octals, hexadecimal
        // ----------------------------------
        int number = 255;
        String octalString = String.format("Octal representation of %d is: %o", number, number);
        String hexString = String.format("Hexadecimal representation of %d is: %x", number, number);
        System.out.println(octalString); // Octal representation of 255 is: 377
        System.out.println(hexString);   // Hexadecimal representation of 255 is: ff

        // specifier for date and time
        java.util.Date date = new java.util.Date();
        String dateString = String.format("Current date and time: %tF %tT", date, date);
        System.out.println(dateString); // Current date and time: 2024-06-15 12:34:56
        // Explanation:
        // %tF: Formats the date in ISO 8601 format (YYYY-MM-DD)
        // %tT: Formats the time in 24-hour format (HH:MM:SS)

        /**
         * Definitions:
         * String interpolation: A programming technique that allows embedding of variables or expressions directly within a string.
         * - It helps create dynamic, concise, and readable text output.
         */
        
        // Notable specifiers for format():
        // --------------------------------
        // d: integer
        // f: floating-point number
        // s: string
        // c: character
        // b: boolean
        // o: octal integer
        // x: hexadecimal integer
        // t: date/time
    }
}

/**
 * Output:
 * -------
 * "  Hello World  " trimmed: Hello World
 * Concatenated String: HelloToTheWorld
 * Arrays.stream(listStr).collect(Collectors.joining(",")): Hello,To,The,World
 * Stream.of(listStr).collect(Collectors.joining(",")): Hello,To,The,World
 * strbuilder_concat_append: Hello,To,The,World,
 * 
 * For string: Hello World
 * 	helloWorld.contains("Hello"): true
 * 	helloWorld.startsWith("Hello"): true
 * 	helloWorld.startsWith("W", 6): true
 * 	helloWorld.endsWith("d"): true
 * For string: Hello
 * 	Hello.replace(char, char): HeLLo
 * 	Hello.replace("ll", "LLB"): HeLLBo
 * 	Hello.replaceAll("[aeiou]", "X"): HXllX
 * 	Hello.replaceFirst("[aeiou]", "X"): HXllo
 * 
 * toCharArray(): [H, e, l, l, o,  , W, o, r, l, d]
 * getChars(): [a, a, W, o, r, l, d, a, a, a]
 * 
 * Formatted string output 1: 123.46 |      Hello | 42   |extra
 * Formatted string output 2: 1,200,345.68
 * Formatted string output 3:  150.8 kilometers
 * 
 * Octal representation of 255 is: 377
 * Hexadecimal representation of 255 is: ff
 * Current date and time: 2026-10-03 19:48:34
 */