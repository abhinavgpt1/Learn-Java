public class CW38_String2_toUpperCase_toLowerCase {
    public static void main(String args[]) {
        String s1_upper = "BCE"; // say, ref1
        String s2_upper = new String("BCE"); // say, ref2

        // RULE: toUpper() and toLower() return same string reference if the result is
        // same as original string irrespective of string literal or dynamic object.

        String s3_upper = s1_upper.toUpperCase(); // ref1
        String s4_upper = s2_upper.toUpperCase(); // ref2
        System.out.println("s1_upper == s3_upper: " + (s1_upper == s3_upper)); // true
        System.out.println("s2_upper == s4_upper: " + (s2_upper == s4_upper)); // true

        String s5_upper = s4_upper.toUpperCase(); // ref2
        System.out.println("s2_upper == s5_upper: " + (s2_upper == s5_upper)); // true
        System.out.println();

        // QQ: Are ref 1 & ref 2 also same?
        System.out.println("Are ref1 (string literal) and ref2 (dynamic object) same ever? No. (s1==s2), (s1==s4), (s1==s5): " + (s1_upper == s2_upper)
                + ", " + (s1_upper == s4_upper) + ", " + (s1_upper == s5_upper));

        // Ref changes as soon as content or casing changes
        String s5_upper_lower_upper = s5_upper.toUpperCase().toLowerCase().toUpperCase(); // ref3
        System.out.println("s5_upper == s5_upper_lower_upper: " + (s5_upper == s5_upper_lower_upper)); // false

        String s2_upper_lower_upper = s2_upper.toUpperCase().toLowerCase().toUpperCase(); // ref4
        System.out.println("s2_upper == s2_upper_lower_upper: " + (s2_upper == s2_upper_lower_upper)); // false

        System.out.println();

        System.out.println("Same cases with toLowerCase():");
        String s1_lower = s1_upper.toLowerCase(); // ref5
        String s2_lower = s2_upper.toLowerCase(); // ref6
        String s3_lower = s1_lower.toLowerCase(); // ref5
        String s4_lower = s2_lower.toLowerCase(); // ref6
        String s5_lower = s1_lower.toLowerCase().toUpperCase().toLowerCase(); // ref7
        String s6_lower = s2_lower.toLowerCase().toUpperCase().toLowerCase(); // ref8
        System.out.println("s1_lower == s3_lower: " + (s1_lower == s3_lower)); // true
        System.out.println("s2_lower == s4_lower: " + (s2_lower == s4_lower)); // true
        System.out.println("s1_lower == s5_lower: " + (s1_lower == s5_lower)); // false
        System.out.println("s2_lower == s6_lower: " + (s2_lower == s6_lower)); // false

        // PTR: for strings, use equals() to compare.
    }
}

/**
 * Output:
 * -------
 * s1_upper == s3_upper: true
 * s2_upper == s4_upper: true
 * s2_upper == s5_upper: true
 * 
 * Are ref1 (string literal) and ref2 (dynamic object) same ever? No. (s1==s2), (s1==s4), (s1==s5): false, false, false
 * s5_upper == s5_upper_lower_upper: false
 * s2_upper == s2_upper_lower_upper: false
 * 
 * Same cases with toLowerCase():
 * s1_lower == s3_lower: true
 * s2_lower == s4_lower: true
 * s1_lower == s5_lower: false
 * s2_lower == s6_lower: false
 */