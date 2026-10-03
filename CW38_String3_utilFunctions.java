public class CW38_String3_utilFunctions {
    public static void main(String args[]) {
        // string initialization methods
        // -----------------------------
        String s1_literal = "abc"; // literal
        String s2_noChar = new String(); // default constructor
        String s3_dynamic = new String("abc"); // constructor with string literal
        String s4 = new String(s1_literal); // constructor with string object

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
        System.out.println("s1_literal.length(): " + s1_literal.length());
        // equals compares reference (==), but in String it is overridden to check content.

        // Note: Precedence matters in sout
        // System.out.println("s1_literal == s3_dynamic: " + s1_literal==s3_dynamic); 
        //  -> "s1_literal == s3_dynamic: abc" == s3_dynamic => so use brackets in comparison below
        System.out.println("\ts1_literal == s3_dynamic: " + (s1_literal==s3_dynamic)); // false
        System.out.println("\ts1_literal.equals(s3_dynamic): " + s1_literal.equals(s3_dynamic)); // true

        System.out.println("equals vs equalsIgnoreCase:");
        System.out.println("\t\"abc\".equals(\"aBc\"): " + "abc".equals("aBc")); // false, case sensitive
        System.out.println("\t\"abc\".equalsIgnoreCase(\"aBc\"): " + "abc".equalsIgnoreCase("aBc")); // true, case insensitive

        System.out.println();
        // https://stackoverflow.com/questions/8484668/java-does-not-equal-not-working
        // RULE: Use equals for strings. 
        // !equals() : content inequality :: vs != : ref inequality

        // empty : size :: blank : non-space characters
        // --------------------------------------------
        System.out.println("empty vs blank:");
        System.out.println("\ts2_noChar.isEmpty(): " + s2_noChar.isEmpty()); // true, empty string
        System.out.println("\t\"   \".isEmpty(): " + "    ".isEmpty()); // false
        System.out.println("\t\"   \".isBlank(): " + "    ".isBlank()); // true
        System.out.println("\t\"\0\".isEmpty(): " + "\0".isEmpty()); // false
        System.out.println("\t\"\0\".isBlank(): " + "\0".isBlank()); // false
        
        // charAt
        // ------
        // s1_literal[0] is not allowed, use charAt() for String.class
        System.out.println("\ts1_literal.charAt(0): " + s1_literal.charAt(0)); // 'a'
        try {
            System.out.println("\ts1_literal.charAt(3): " + s1_literal.charAt(3)); // exception
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Catching purposeful StringIndexOutOfBoundsException for s1_literal.charAt(3): " + e);
        }
        System.out.println();
        
        // Workaround for charAt()
        // ------------------------
        // RULE: String.class is immutable. There is no set function. Use StringBuilder for in-place modification.
        
        // Task: set s[1] as 'z'
        int indexAffected = 1;
        String abc_str = "abc";
        String abc_str_mod = abc_str.substring(0, indexAffected) + 'z' + abc_str.substring(indexAffected + 1);
        System.out.println("Using substring, abc_str: " + abc_str + ", abc_str_mod: " + abc_str_mod); // abc, azc
        System.out.println();

        // toUpperCase, toLowerCase: read more in CW38_String2_toUpper_toLower.java
        // -------------------------------------------------------------------------
        String s_upper = s1_literal.toUpperCase();
        String s_lower = s1_literal.toLowerCase();
        
        // valueOf() and toString()
        // ------------------------
        String s_integer = String.valueOf("1234"); // "1234"
        String s_float = String.valueOf(1234.56f); // "1234.56"
        String s_double = String.valueOf(1234.56); // "1234.56"
        String s_char = String.valueOf('a'); // "a"
        String s_boolean = String.valueOf(true); // "true"
        String s_object = String.valueOf(new Object()); // "java.lang.Object@<hashcode>"
        
        // Upon String.valueOf(x) and sout(x) of any Object (x), toString() is called.
        // In case of Object, it is the class name and hashcode. For String, it is the string itself.
        class Student {
            String name;
            int age;
            Student(String name, int age) {
                this.name = name;
                this.age = age;
            }
        }
        Student student = new Student("John", 20);
        System.out.println("String.valueOf(new Student()): " + String.valueOf(student)); // CW38_String3_utilFunctions$1Student@14dad5dc
    }

    /**
     * Output:
     * -------
     * s1_literal.length(): 3
     * 	s1_literal == s3_dynamic: false
     * 	s1_literal.equals(s3_dynamic): true
     * equals vs equalsIgnoreCase:
     * 	"abc".equals("aBc"): false
     * 	"abc".equalsIgnoreCase("aBc"): true
     * 
     * empty vs blank:
     * 	s2_noChar.isEmpty(): true
     * 	"   ".isEmpty(): false
     * 	"   ".isBlank(): true
     * 	"nul".isEmpty():false
     * 	"nul".isBlank():false
     * s1_literal.charAt(0): a
     * Catching purposeful StringIndexOutOfBoundsException for s1_literal.charAt(3): java.lang.StringIndexOutOfBoundsException: Index 3 out of bounds for length 3
     * 
     * Using substring, abc_str: abc, abc_str_mod: azc
     * 
     * String.valueOf(new Student()): CW38_String3_utilFunctions$1Student@14dad5dc
     */
}