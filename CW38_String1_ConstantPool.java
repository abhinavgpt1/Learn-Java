public class CW38_String1_ConstantPool {
    public static void main(String args[]) {
        String s1_literal = "BCE";
        String s2_literal = "BCE";
        String s3_dynamic_obj = new String("BCE");
        String s4_dynamic_obj = new String(s2_literal);

        // RULE: String Constant Pool is valid only and only for string literals. No dynamic object can be a part of it.

        // s1 and s2 are in constant pool (have same reference), s3 and s4 are stored separately.
        // Therefore, there are 3 different references in memory for 4 strings, but all have the same content.

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

        // IMP: hashcode != memory address
        // hashCode() for string is overridden as is content based. 
        System.out.println("Using hashcode to compare values:");
        System.out.println("\ts1_literal.hashCode(): " + s1_literal.hashCode()); // 65572
        System.out.println("\ts2_literal.hashCode(): " + s2_literal.hashCode()); // 65572
        System.out.println("\ts3_dynamic_obj.hashCode(): " + s3_dynamic_obj.hashCode()); // 65572
        System.out.println("\ts4_dynamic_obj.hashCode(): " + s4_dynamic_obj.hashCode()); // 65572

        /**
         * Definitions:
         * String Constant Pool: A special area of the JVM heap where string literals
         * are stored and reused to avoid creating duplicate String objects with the
         * same value.
         * 
         * hashCode(): A method inherited from the Object class that returns an integer
         * hash value which is used by hash-based collections, such as HashMap and
         * HashSet, to organize and locate objects efficiently.
         */
    }
}
/**
 * Output:
 * -------
 * Using == operator to compare references:
 * 	s1_literal == s2_literal: true
 * 	s1_literal == s3_dynamic_obj: false
 * 	s1_literal == s4_dynamic_obj: false
 * 	s2_literal == s3_dynamic_obj: false
 * 	s2_literal == s4_dynamic_obj: false
 * 	s3_dynamic_obj == s4_dynamic_obj: false
 * 
 * Using equals() to compare values:
 * 	s1_literal.equals(s2_literal): true
 * 	s1_literal.equals(s3_dynamic_obj): true
 * 	s1_literal.equals(s4_dynamic_obj): true
 * 	s2_literal.equals(s3_dynamic_obj): true
 * 	s2_literal.equals(s4_dynamic_obj): true
 * 	s3_dynamic_obj.equals(s4_dynamic_obj): true
 * 
 * Using hashcode to compare values:
 * 	s1_literal.hashCode(): 65572
 * 	s2_literal.hashCode(): 65572
 * 	s3_dynamic_obj.hashCode(): 65572
 * 	s4_dynamic_obj.hashCode(): 65572
 */