import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CW38_String4_utilFunctions2 {
    public static void main(String args[]) {
        // refer for regex exmaples - https://www.baeldung.com/regular-expressions-java

        // split
        // -----
        System.out.println("split string (abc,abc,abc) via delimiter (,):");
        // split via delimiter
        String splitStrAbc3times = "abc,abc,abc";
        for (String s : splitStrAbc3times.split(","))
            System.out.println("\t- " + s);
        
        System.out.println("split string (abc,abc,abc) via regex ([aeiou]):");
        // split via regex
        for (String s : splitStrAbc3times.split("[aeiou]"))
            System.out.println("\t- " + s); // "" + "bc," + "bc," + "bc"
        
        System.out.println();
        
        // Split when delimiter is at beginning behaves differently
        System.out.println("RULE: By default, trailing empty strings are discarded (StackOverflow: 14602062).");
        System.out.println("aeiou split on regex[aeiou]: " + Arrays.toString("aeiou".split("[aeiou]"))); // []
        System.out.println("ya split on regex[aeiou]: " + Arrays.toString("ya".split("[aeiou]"))); // [y]
        System.out.println("ay split on regex[aeiou]: " + Arrays.toString("ay".split("[aeiou]"))); // [, y]
        System.out.println("aya split on regex[aeiou]: " + Arrays.toString("aya".split("[aeiou]"))); // [, y]
        // Old ref of an issue about regex removing trailing empty spaces, can't see it anymore
        // https://stackoverflow.com/questions/14602062/java-string-split-removed-empty-values#:~:text=have%20any%20length.-,If%20n%20is%20zero%20then%20the%20pattern%20will%20be%20applied%20as%20many%20times%20as%20possible%2C%20the%20array%20can%20have%20any%20length%2C%20and%20trailing%20empty%20strings%20will%20be%20discarded.,-Exception%3A

        // remove all empty strings using Arrays.stream and filter
        String strA2I = "abcdefghi";
        String[] splitStrAndFilter = 
            Arrays.stream(strA2I.split("[aeiou]"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new); // bcd, fgh
        System.out.println("Split string (abcdefghi) by regex ([aeiou]), filter empty strings using stream: " + Arrays.toString(splitStrAndFilter)); // [bcd, fgh]
        
        System.out.println();

        // join() : elements to join must be CharSequence
        // ----------------------------------------------
        // Syntax 1: public String join(CharSequence separator, CharSequence... elements)
        String joinedByListingElements = String.join(",", "abc", "def", "ghi"); // abc,def,ghi
        System.out.println("joined by listing elements: String.join(\",\", \"abc\", \"def\", \"ghi\"): " + joinedByListingElements); // abc,def,ghi

        // Syntax 2: public String join(CharSequence separator, Iterable elements)
        List<String> list = Arrays.asList("abc", "def", "ghi");
        String joinedByIterable = String.join(",", list); // abc,def,ghi
        System.out.println("joined by iterable: String.join(\",\", list): " + joinedByIterable); // abc,def,ghi

        // repeat()
        // --------
        String repeatedString = "abc".repeat(3); // abcabcabc
        System.out.println("repeated string (java11+): \"abc\".repeat(3): " + repeatedString); // abcabcabc
        // Arrays.fill()
        // -------------
        char charArray[] = new char[3];
        Arrays.fill(charArray, 'a');
        System.out.println("repeated char[]: " + Arrays.toString(charArray)); // [a, a, a]

        System.out.println();

        // substring(si, ei (optional and exclusive))
        // ------------------------------------------
        System.out.println("substring(startIndex, [endIndex EXCLUSIVE]):");
        String sentence = "base camp in ohio";
        System.out.println("\tsentence.substring(5): " + sentence.substring(5)); // camp in ohio
        System.out.println("\tsentence.substring(5,9) ~ sentence[5...8]: " + sentence.substring(5, 9)); // [8] is 'p' => camp
        try {
            System.out.println(sentence.substring(-1));
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("RULE: startIndex and endIndex in substring() should be +ve and within bounds unlike python");
        }
        
        System.out.println();

        // indexOf() / lastIndexOf() : returns -1 if not found
        // ----------------------------------------------------
        String strNumDelimited = "012,45,78";
        System.out.println("indexOf(',') : left-to-right: " + strNumDelimited.indexOf(',')); // 3
        System.out.println("indexOf(\",\") : as string: " + strNumDelimited.indexOf(",")); // 3
        System.out.println("indexOf(',', 4) : after 1st occurence: " + strNumDelimited.indexOf(',', strNumDelimited.indexOf(',') + 1)); // 6
        
        System.out.println("lastIndexOf(',') : " + strNumDelimited.lastIndexOf(',')); // 6
        System.out.println("lastIndexOf(',') : after 1st occurence from last: " + strNumDelimited.lastIndexOf(',', strNumDelimited.lastIndexOf(',') - 1)); // 3

        System.out.println("indexOf('*') : not found: " + strNumDelimited.lastIndexOf('*')); // -1
        System.out.println();

        // char <-> ASCII
        // --------------
        int ascii_A = (int) 'A'; // 65
        int ascii_a = (int) 'a'; // 97
        char ch_65 = (char) ascii_A; // A
        char ch_97 = (char) ascii_a; // a
        System.out.println("ASCII of A: " + ascii_A + ", ASCII of a: " + ascii_a); // 65, 97
        System.out.println("char of 65: " + ch_65 + ", char of 97: " + ch_97); // A, a
        System.out.println("diff of char (ch_97 - ch_65): " + (ch_97 - ch_65)); // 32
        
        System.out.println();

        // compareTo() & compareToIgnoreCase()
        // -----------------------------------
        // when str1 is not a substring of str2, compareTo() returns the difference of the first non-matching character in the two strings.
        // check defintion - compareToUTF16Values() returns len1 - len2 if no character mismatches
        System.out.println("abc vs def (a(97)-d(100)): " + "abc".compareTo("def")); // -3
        System.out.println("abc vs abcdefg (3-7): " + "abc".compareTo("abcdefg")); // -4
        
        // compareToIgnoreCase(): The comparison is based on the Unicode value of each character in the string converted to lower case.
        System.out.println("\"hello\".compareTo(\"HELLO\"): " + ("hello".compareTo("HELLO"))); // 32
        System.out.println("\"hello\".compareToIgnoreCase(\"HELLO\"): " + ("hello".compareToIgnoreCase("HELLO"))); // 0
        System.out.println("\"hello\".compareToIgnoreCase(\"HELLOLOG\"): " + ("hello".compareToIgnoreCase("HELLOLOG"))); // -3
    }

    /**
     * Output:
     * -------
     * split string (abc,abc,abc) via delimiter (,):
     *  - abc
     *  - abc
     *  - abc
     * split string (abc,abc,abc) via regex ([aeiou]):
     *  - 
     *  - bc,
     *  - bc,
     *  - bc
     * 
     * RULE: By default, trailing empty strings are discarded (StackOverflow: 14602062).
     * aeiou split on regex[aeiou]: []
     * ya split on regex[aeiou]: [y]
     * ay split on regex[aeiou]: [, y]
     * aya split on regex[aeiou]: [, y]
     * Split string (abcdefghi) by regex ([aeiou]), filter empty strings using stream: [bcd, fgh]
     * 
     * joined by listing elements: String.join(",", "abc", "def", "ghi"): abc,def,ghi
     * joined by iterable: String.join(",", list): abc,def,ghi
     * repeated string (java11+): "abc".repeat(3): abcabcabc
     * repeated char[]: [a, a, a]
     * 
     * substring(startIndex, [endIndex EXCLUSIVE]):
     * 	sentence.substring(5): camp in ohio
     * 	sentence.substring(5,9) ~ sentence[5...8]: camp
     * RULE: startIndex and endIndex in substring() should be +ve and within bounds unlike python
     * 
     * indexOf(',') : left-to-right: 3
     * indexOf(",") : as string: 3
     * indexOf(',', 4) : after 1st occurence: 6
     * lastIndexOf(',') : 6
     * lastIndexOf(',') : after 1st occurence from last: 3
     * indexOf('*') : not found: -1
     * 
     * ASCII of A: 65, ASCII of a: 97
     * char of 65: A, char of 97: a
     * diff of char (ch_97 - ch_65): 32
     * 
     * abc vs def (a(97)-d(100)): -3
     * abc vs abcdefg (3-7): -4
     * "hello".compareTo("HELLO"): 32
     * "hello".compareToIgnoreCase("HELLO"): 0
     * "hello".compareToIgnoreCase("HELLOLOG"): -3
     */
}