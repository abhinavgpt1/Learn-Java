public class CW38_String3_utilFunctions1 {
    public static void main(String args[]) {
        // string initialization methods
        // -----------------------------
        String s1 = "abc"; // literal
        String s2 = new String(); // default constructor
        String s3 = new String("abc"); // constructor with string literal
        String s4 = new String(s1); // constructor with string object

        char c[] = {'a', 'b', 'c', 'd', 'e'};
        String s5 = new String(c); // constructor with char array => "abcde"
        String s6 = new String(c, 2, 3); // constructor with char array and offset and length (not end index) => "cde"
        String s7 = "t".repeat(3); // string with character/string repeated n times (valid java 11+) => "ttt"
        
        // Other ways
        // - String(StringBuilder)
        // - String(StringBuffer)
        // - String(byte [] bytes)
        // - String(byte[] bytes, int offset, int length)

        // length, equals, equalsIgnoreCase
        // --------------------------------
        System.out.println("s1.length(): " + s1.length());
        System.out.println("equals compares reference (==), but in String it is overridden to check content:");
        // --------Precedence matters-------
        // System.out.println("s1 (literal) == s3 (dynamic object): " + s1==s3); 
        //  -> "s1 (literal) == s3 (dynamic object): abc" == s3 => so use brackets in comparison below
        System.out.println("\ts1 (literal) == s3 (dynamic object): " + (s1==s3)); // false, different reference
        System.out.println("\ts1 (literal) equals s3 (dynamic object): " + s1.equals(s3)); // true, although different reference

        System.out.println("equals vs equalsIgnoreCase:");
        System.out.println("\t\"abc\".equals(\"aBc\"): " + "abc".equals("aBc")); // false, case sensitive
        System.out.println("\t\"abc\".equalsIgnoreCase(\"aBc\"): " + "abc".equalsIgnoreCase("aBc")); // true, case insensitive

        System.out.println();
        // https://stackoverflow.com/questions/8484668/java-does-not-equal-not-working
        System.out.println("RULE: Use equals for strings. !equals() vs != :: content (for string only) vs ref inequality.");
        System.out.println();

        // empty, blank :: size vs non-space characters
        // --------------------------------------------
        System.out.println("empty vs blank:");
        System.out.println("\ts2.isEmpty(): " + s2.isEmpty()); // true, empty string
        System.out.println("\t\"   \".isEmpty(): " + "    ".isEmpty()); // false, blank string with spaces
        System.out.println("\t\"   \".isBlank(): " + "    ".isBlank()); // true, empty string
        System.out.println("\t\"\0\".isEmpty(): " + "\0".isEmpty()); // false
        System.out.println("\t\"\0\".isBlank(): " + "\0".isBlank()); // false
        
        // charAt
        // ------
        System.out.println("there is no s1[0], use charAt() for String.class:");
        System.out.println("\ts1.charAt(0): " + s1.charAt(0)); // 'a', first character
        try {
            System.out.println("\ts1.charAt(3): " + s1.charAt(3)); // exception
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Catching purposeful runtime exception of string index out of bound access" + e); // exception, index out of range
        }
        
        System.out.println();
        
        // set charAt(): workaround
        // ------------------------
        System.out.println("RULE: no set function for String. Use StringBuilder for in-place modification.");
        // set charAt(1) as 'z'
        int indexAffected = 1;
        String sampleStrToBeModified = "abc";
        String modifiedString = sampleStrToBeModified.substring(0, indexAffected) + 'z' + sampleStrToBeModified.substring(indexAffected + 1);
        System.out.println("(Using substring): sampleStrToBeModified: " + sampleStrToBeModified + ", modifiedString: " + modifiedString); // abc, azc
        System.out.println();

        // toUpperCase, toLowerCase: read more in CW38_String2_toUpper_toLower.java
        // -------------------------------------------------------------------------
        String s_upper = s1.toUpperCase();
        String s_lower = s1.toLowerCase();
        
        // valueOf() and toString()
        // ------------------------
        String s_integer = String.valueOf("1234"); // "1234"
        String s_float = String.valueOf(1234.56f); // "1234.56"
        String s_double = String.valueOf(1234.56); // "1234.56"
        String s_char = String.valueOf('a'); // "a"
        String s_boolean = String.valueOf(true); // "true"
        String s_object = String.valueOf(new Object()); // "java.lang.Object@<hashcode>"
        
        System.out.println("On String.valueOf() and sout() of any Object, toString() is called. The definition can be overridden in the class though.");
        System.out.println("In case of Object, it is the class name and hashcode. For String, it is the string itself.");
        System.out.println("String.valueOf(new Object()): " + s_object); // java.lang.Object@<hashcode>
    }

    /**
     * Output:
     * -------
     * s1.length(): 3
     * equals compares reference (==), but in String it is overridden to check content:
     * 	s1 (literal) == s3 (dynamic object): false
     * 	s1 (literal) equals s3 (dynamic object): true
     * equals vs equalsIgnoreCase:
     * 	"abc".equals("aBc"): false
     * 	"abc".equalsIgnoreCase("aBc"): true
     * 
     * RULE: Use equals for strings. !equals() vs != :: content (for string only) vs ref inequality.
     * 
     * empty vs blank:
     * 	s2.isEmpty(): true
     * 	"   ".isEmpty(): false
     * 	"   ".isBlank(): true
     * 	"nul".isEmpty():false
     * 	"nul".isBlank():false
     * there is no s1[0], use charAt() for String.class:
     * 	s1.charAt(0): a
     * Catching purposeful runtime exception of string index out of bound accessjava.lang.StringIndexOutOfBoundsException: Index 3 out of bounds for length 3
     * 
     * RULE: no set function for String. Use StringBuilder for in-place modification.
     * (Using substring): sampleStrToBeModified: abc, modifiedString: azc
     * 
     * On String.valueOf() and sout() of any Object, toString() is called. The definition can be overridden in the class though.
     * In case of Object, it is the class name and hashcode. For String, it is the string itself.
     * String.valueOf(new Object()): java.lang.Object@8bcc55f
     */
}