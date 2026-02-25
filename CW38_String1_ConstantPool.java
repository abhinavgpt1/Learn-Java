public class CW38_String1_ConstantPool {
    public static void main(String args[]) {
        String s1_literal = "BCE";
        String s2_literal = "BCE";
        String s3_dynamic_obj = new String("BCE");
        String s4_dynamic_obj = new String(s2_literal);

        System.out.println("RULE: String Constant Pool is valid only and only for string literals - no dynamic object can be a part of constant pool");
        // s1 and s2 have same reference and no one else.
        System.out.println("S1 and S2 are in constant pool, s3 and s4 are stored separately");
        System.out.println("Using == operator to compare references:");
        System.out.println("\ts1_literal == s2_literal: " + (s1_literal == s2_literal)); // true
        System.out.println("\ts1_literal == s3_dynamic_obj: " + (s1_literal == s3_dynamic_obj)); // false
        System.out.println("\ts1_literal == s4_dynamic_obj: " + (s1_literal == s4_dynamic_obj)); // false
        System.out.println("\ts2_literal == s3_dynamic_obj: " + (s2_literal == s3_dynamic_obj)); // false
        System.out.println("\ts2_literal == s4_dynamic_obj: " + (s2_literal == s4_dynamic_obj)); // false
        System.out.println("\ts3_dynamic_obj == s4_dynamic_obj: " + (s3_dynamic_obj == s4_dynamic_obj)); // false

        System.out.println();
        
        System.out.println("Using equals() to compare values:");
        System.out.println("\ts1_literal.equals(s2_literal): " + s1_literal.equals(s2_literal)); // true
        System.out.println("\ts1_literal.equals(s3_dynamic_obj): " + s1_literal.equals(s3_dynamic_obj)); // true
        System.out.println("\ts1_literal.equals(s4_dynamic_obj): " + s1_literal.equals(s4_dynamic_obj)); // true
        System.out.println("\ts2_literal.equals(s3_dynamic_obj): " + s2_literal.equals(s3_dynamic_obj)); // true
        System.out.println("\ts2_literal.equals(s4_dynamic_obj): " + s2_literal.equals(s4_dynamic_obj)); // true
        System.out.println("\ts3_dynamic_obj.equals(s4_dynamic_obj): " + s3_dynamic_obj.equals(s4_dynamic_obj)); // true

        System.out.println();

        // The hashCode() is same for all, but again hashcode != memory address
        System.out.println("Hashcode for all strings is same, since they contain same content (see definition)");
        System.out.println("\ts1_literal.hashCode(): " + s1_literal.hashCode());
        System.out.println("\ts2_literal.hashCode(): " + s2_literal.hashCode());
        System.out.println("\ts3_dynamic_obj.hashCode(): " + s3_dynamic_obj.hashCode());
        System.out.println("\ts4_dynamic_obj.hashCode(): " + s4_dynamic_obj.hashCode());
    }

    /**
     * Output:
     * -------
     * RULE: String Constant Pool is valid only and only for string literals - no dynamic object can be part of constant pool
     * S1 and S2 are in constant pool, s3 and s4 are stored separately
     * Using == operator to compare references:
     * s1_literal == s2_literal: true
     * s1_literal == s3_dynamic_obj: false
     * s1_literal == s4_dynamic_obj: false
     * s2_literal == s3_dynamic_obj: false
     * s2_literal == s4_dynamic_obj: false
     * s3_dynamic_obj == s4_dynamic_obj: false
     * 
     * Using equals() to compare values:
     * s1_literal.equals(s2_literal): true
     * s1_literal.equals(s3_dynamic_obj): true
     * s1_literal.equals(s4_dynamic_obj): true
     * s2_literal.equals(s3_dynamic_obj): true
     * s2_literal.equals(s4_dynamic_obj): true
     * s3_dynamic_obj.equals(s4_dynamic_obj): true
     * 
     * Hashcode for all strings is same, since they contain same content (see definition)
     * s1_literal.hashCode(): 65572
     * s2_literal.hashCode(): 65572
     * s3_dynamic_obj.hashCode(): 65572
     * s4_dynamic_obj.hashCode(): 65572
     */
}