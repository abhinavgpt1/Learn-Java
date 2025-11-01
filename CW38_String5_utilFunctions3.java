import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CW38_String5_utilFunctions3 {
    public static void main(String args[]) {
        
        // trim
        // ----
        String strHelloWorldWithSpaces = "  Hello World  ";
        System.out.println("Trimmed String: " + strHelloWorldWithSpaces.trim());
        
        // concat of strings
        // -----------------
        // using concat()
        // new reference/object is created every time we concatenate strings
        String strHello = "Hello";
        String strTo = "To";
        String strThe = "The";
        String str2World = "World";
        System.out.println("Concatenated String: " + strHello.concat(strTo).concat(strThe).concat(str2World));
        // using + operator : syntactic sugar for string concatenation: low performance
        // In programming, "syntactic sugar" refers to syntax that makes code easier to read and write without changing its underlying functionality
        
        // Use StringBuilder in case of loops; Streams when high no of strings are to be concatenated
        String listStr[] = new String[] { strHello, strTo, strThe, str2World };
        String streamOutput1 = Arrays.stream(listStr).collect(Collectors.joining(","));
        System.out.println("Concatenated String using Arrays.stream: " + streamOutput1); // Hello,To,The,World1
        String streamOutput2 = Stream.of(listStr).collect(Collectors.joining(","));
        System.out.println("Concatenated String using Stream.of (uses Arrays.stream internally): " + streamOutput2); // Hello,To,The,World

        StringBuilder stringBuilderOutput = new StringBuilder();
        for (String str : listStr) {
            stringBuilderOutput.append(str).append(",");
        }
        System.out.println("Concatenated String using StringBuilder: " + stringBuilderOutput.toString()); // Hello,To,The,World, <-- note the trailing comma
        
        System.out.println();

        // contains
        // --------
        // syntax: public boolean contains(CharSequence chars)
        // internally uses indexOf(chars) >= 0;
        String strHelloWorld = "Hello World";
        System.out.println("Contains: " + strHelloWorld.contains("Hello"));
        
        // startsWith, endsWith
        // --------------------
        // syntax: public boolean startsWith(String chars)
        System.out.println("Starts with: " + strHelloWorld.startsWith("Hello")); //true
        System.out.println("Starts with (using offset): " + strHelloWorld.startsWith("W", 6)); // true
        System.out.println("Ends with (internally uses startsWith()): " + strHelloWorld.endsWith("d")); //true
        // System.out.println("Ends with (using offset): " + strHelloWorld.endsWith("o", 4)); // this variation isn't possible

        // replace, replaceAll, replaceFirst
        // ---------------------------------
        System.out.println("Replaced string using replace(char, char): " + strHello.replace('l', 'L')); // HeLLo // replace all occurrences of 'l' with 'L'
        System.out.println("Replaced string using replace(string, string): " + strHello.replace("ll", "LLB")); // HeLLBo // replace all occurrences of 'll' with 'LLB'
        System.out.println("Replaced string using replaceAll(regex, string): " + strHello.replaceAll("[aeiou]", "X")); // HXllX // replace all occurrences of regex with 'X'
        System.out.println("Replaced string using replaceFirst(regex, string): " + strHello.replaceFirst("[aeiou]", "X")); // HXllo // replace first occurrence of regex with 'X'
        // Note: replaceAll() and replaceFirst() are slower than replace() because they use regex.

        System.out.println();
        
        // toCharArray() and getChars()
        // ------------------------------
        System.out.println("toCharArray(): " + Arrays.toString(strHelloWorld.toCharArray())); // [H, e, l, l, o,  , W, o, r, l, d]
        char charAllAs[] = new char[10];
        Arrays.fill(charAllAs, 'a'); // fill with 'a'
        strHelloWorld.getChars(6, 11, charAllAs, 2); // copies "World" into charAllAs[2] to charAllAs[6]
        System.out.println("getChars(): " + Arrays.toString(charAllAs)); // [a, a, W, o, r, l, d, a, a, a] // srcEnd not counted

        System.out.println();
        
        // format
        // ------
        // Theory - creates a formatted string using the specified format string and arguments. We can concatenate, format using options such as width, alignment, decimal places, and more.
        // src - https://www.geeksforgeeks.org/java/java-string-format-method-with-examples/

        // Example 1
        String formattedString = String.format("%.2f | %10s | %-5d|extra", 123.4567, "Hello", 42);
        System.out.println("Formatted string output 1:" + formattedString); // 123.46 |      Hello | 42
        // Explanation:
        // %.2f: The f specifier is for floating-point numbers, and .2 means the number should be rounded to two decimal places.
        // %10s: The s specifier is for strings, and 10 means the string should be right-aligned within a field of width 10. If the string is shorter than 10 characters, it will be padded with spaces on the left.
        // %-5d: The d specifier is for integers, and -5 means the integer should be left-aligned within a field of width 5. If the integer is shorter than 5 digits, it will be padded with spaces on the right.

        // Example 2
        double d2 = 1200345.6789;
        // Format the price with thousands separator and two decimal places
        String s2 = String.format("%1$,10.2f", d2); // 1,200,345.68
        System.out.println("Formatted string output 2: " + s2);
        /*
         * Explanation:
         * %1$: It refers to the first argument (d), the price value.
         * ,: It groups digits with a comma as a thousands separator.
         * 10.2f: It ensures that the floating-point number takes at least 10 characters, with 2 decimal places. 
         * 
         * Note: If the number is shorter than 10 characters, it will be right-aligned and padded with spaces on the left.
         */

        // Example 3
        double d3 = 150.75;
        String s3 = "kilometers";
        String res = String.format("%1$,7.1f %2$s", d3, s3);
        System.out.println("Formatted string output 3:" + res); // one extra space before 150.8 since "150.8" is 6 characters long and we specified minimum width of 7
        /**
         * %1$: It refers to the first argument.
         * ,: Groups digits with a comma as a thousands separator.
         * 7.1f: This ensures that the floating-point number takes at least 7 characters in total, with 1 decimal place.
         * %2$s: Refers to the second argument i.e. "d" the distance. "s" is the format specifier for strings.
         */

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
        // %tF: Formats the date in ISO 8601 format (YYYY-MM-DD).
        // %tT: Formats the time in 24-hour format (HH:MM:SS).
    }

    /**
     * Output:
     * -------
     * Trimmed String: Hello World
     * Concatenated String: HelloToTheWorld
     * Concatenated String using Arrays.stream: Hello,To,The,World
     * Concatenated String using Stream.of (uses Arrays.stream internally): Hello,To,The,World
     * Concatenated String using StringBuilder: Hello,To,The,World,
     * 
     * Contains: true
     * Starts with: true
     * Starts with (using offset): true
     * Ends with (internally uses startsWith()): true
     * Replaced string using replace(char, char): HeLLo
     * Replaced string using replace(string, string): HeLLBo
     * Replaced string using replaceAll(regex, string): HXllX
     * Replaced string using replaceFirst(regex, string): HXllo
     * 
     * toCharArray(): [H, e, l, l, o,  , W, o, r, l, d]
     * getChars(): [a, a, W, o, r, l, d, a, a, a]
     * 
     * Formatted string output 1:123.46 |      Hello | 42   |extra   
     * Formatted string output 2: 1,200,345.68
     * Formatted string output 3:  150.8 kilometers
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