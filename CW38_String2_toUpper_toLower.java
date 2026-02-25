public class CW38_String2_toUpper_toLower {
    public static void main(String args[]) {
        String s1_upper = "BCE"; // ref1
        String s2_upper = new String("BCE"); // ref2
        System.out.println("RULE: toUpper and toLower return same string object if on operation the result is same as original string");
        System.out.println("This is valid for both dynamic and literal string:");
        String s3_upper = s1_upper.toUpperCase(); // ref1
        String s4_upper = s2_upper.toUpperCase(); // ref2
        System.out.println("\ts1_upper == s3_upper: " + (s1_upper == s3_upper)); // true, same reference
        System.out.println("\ts2_upper == s4_upper: " + (s2_upper == s4_upper)); // true, same reference
        String s5_upper_s4_upper = s4_upper.toUpperCase(); // ref2
        System.out.println("\ts2_upper == s5_upper_s4_upper: " + (s2_upper == s5_upper_s4_upper)); // true, same reference
        // Are ref 1 & ref 2 also same? Never
        System.out.println("\tAre ref1 and ref2 same ever? No. (s1==s2), (s1==s4), (s1==s5): " + (s1_upper == s2_upper) + ", " + (s1_upper == s4_upper) + ", " + (s1_upper == s5_upper_s4_upper));
        
        System.out.println("Ref changes as soon as content or casing changes:");
        String s5_upper_lower_upper = s1_upper.toUpperCase().toLowerCase().toUpperCase(); // ref3
        System.out.println("\ts1_upper == s5_upper_lower_upper: " + (s1_upper == s5_upper_lower_upper)); // false, different reference
        String s6_upper_lower_upper = s2_upper.toUpperCase().toLowerCase().toUpperCase(); // ref4
        System.out.println("\ts2_upper == s6_upper_lower_upper: " + (s2_upper == s6_upper_lower_upper)); // false, different reference

        System.out.println();

        System.out.println("Same cases with toLowerCase():");
        String s1_lower = s1_upper.toLowerCase(); // ref5
        String s2_lower = s2_upper.toLowerCase(); // ref6
        String s3_lower = s1_lower.toLowerCase(); // ref5
        String s4_lower = s2_lower.toLowerCase(); // ref6
        String s5_lower_upper_lower = s1_lower.toLowerCase().toUpperCase().toLowerCase(); // ref7
        String s6_lower_upper_lower = s2_lower.toLowerCase().toUpperCase().toLowerCase(); // ref8
        System.out.println("\ts1_lower == s3_lower: " + (s1_lower == s3_lower)); // true, same reference
        System.out.println("\ts2_lower == s4_lower: " + (s2_lower == s4_lower)); // true, same reference
        System.out.println("\ts1_lower == s5_lower_upper_lower: " + (s1_lower == s5_lower_upper_lower)); // false, different reference
        System.out.println("\ts2_lower == s6_lower_upper_lower: " + (s2_lower == s6_lower_upper_lower)); // false, different reference

        // PTR: for strings, use equals() to compare.
    }

    /**
     * Output:
     * -------
     * RULE: toUpper and toLower return same string object if on operation the result is same as original string
     * This is valid for both dynamic and literal string:
     * 	s1_upper == s3_upper: true
     * 	s2_upper == s4_upper: true
     * 	s2_upper == s5_upper_s4_upper: true
     * 	Are ref1 and ref2 same ever? No. (s1==s2), (s1==s4), (s1==s5): false, false, false
     * Ref changes as soon as content or casing changes:
     * 	s1_upper == s5_upper_lower_upper: false
     * 	s2_upper == s6_upper_lower_upper: false
     * 
     * Same cases with toLowerCase():
     * 	s1_lower == s3_lower: true
     * 	s2_lower == s4_lower: true
     * 	s1_lower == s5_lower_upper_lower: false
     * 	s2_lower == s6_lower_upper_lower: false
     */
}