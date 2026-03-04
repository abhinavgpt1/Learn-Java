import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class CW81_TricksForDSA {
    public static void main(String[] args) {
        /* unsigned right shift (>>>): it shifts bits right and fills the leftmost bits with 0 (ignores the sign bit). */
        // - int mid = (low + high) >>> 1; // useful even if low + high overflow.
        System.out.println("Unsigned right shift of Integer.MIN_VALUE >>> 1: 2^30: " + (Integer.MIN_VALUE >>> 1)); // 1073741824
        
        /* removeIf(Predicate) */
        // - applicable for any Collection. FYI, not Map (directly). For Map, we can use entrySet(), values() and then removeIf on that.
        // - predicate can't be null; else NPE.
        Set<String> set = new HashSet<>();
        set.add("hello");
        set.add("world, ");
        set.add("what's up");
        set.removeIf(s -> s.startsWith("w"));
        System.out.println(set);

        /* create Map.Entry */
        // - Way 1: Map.entry (Java 9+)
        Map.Entry<String, Integer> e1 = Map.entry("key", 1); // immutable
        // e1.setValue(90); // UnsupportedOperationException
        // - Way 2: AbstractMap.SimpleEntry
        Map.Entry<String, Integer> e2 = new AbstractMap.SimpleEntry<>("key", 1);
        // - Way 3: new Map.Entry<>() { ... } (anonymous class) by overriding getKey(), getValue() and setValue() methods.
        // - Way 4: AbstractMap.SimpleImmutableEntry

        // IMP: PTR: Map.Entry interface doesn't provide setKey().
        // IMP: PTR: Map.Entry interface doesn't provide setKey() since Map (like a
        // HashMap) depends on the key's hash code to determine its storage location. If
        // you could change the key in-place, the Map would likely "lose" the object
        // becoz it would still be stored in a bucket corresponding to the old hash code
        // - map.put(newKey, map.remove(oldKey)); // use this to effectively update the key in map

        /* Iterate string by char in for-each loop : for(char c: str) doesn't work like cpp */
        String str1 = "abcdefg";
        for(char c: str1.toCharArray()){
            System.out.print(c + ",");
        }

        System.out.println();

        /* int -> binary/octal/hexal string : Use Integer.toString(int, radix) or Integer.toBinaryString(int), Integer.toOctalString(int), Integer.toHexString(int) */
        int i1 = 10;
        String bin1 = Integer.toString(i1, 2); // int -> binary
        String bin2 = Integer.toBinaryString(i1); // int -> binary
        // Or use a custom method getRadixString
        String bin3 = getRadixString(i1, 2);
        System.out.println("int(10) to bin1: " + bin1 + ", bin2: " + bin2 + ", bin3: " + bin3);
        if (!bin1.equals(bin2)) { throw new AssertionError("bin1 and bin2 should be same"); }
        if (!bin1.equals(bin3)) { throw new AssertionError("bin1 and bin3 should be same"); }
        
        String binMinus1 = Integer.toBinaryString(-1);
        System.out.println("int(-1) using toBinaryString: " + binMinus1); // 32 1's in binary since -1 is represented as all bits set to 1 in two's complement.
        String binMin1_2 = getRadixString(-1, 2);
        System.out.println("int(-1) using getRadixString: " + binMin1_2); // 111..32 times
        if (!binMinus1.equals(binMin1_2)) { throw new AssertionError("binMinus1 and binMin1_2 should be same"); }
        if (!getRadixString(Integer.MIN_VALUE, 2).equals(Integer.toBinaryString(Integer.MIN_VALUE))) { throw new AssertionError("getRadix(INT_MIN) and Integer.toBinaryString(-1) should be same"); }

        /* binary/octal/hexal string -> int : Use Integer.valueOf(str, radix) */
        // PTR: You cannot directly use Integer.valueOf() or Integer.parseInt() to parse
        // a two's complement binary string into a negative int, because these methods treat the input as a
        // positive unsigned value in the specified radix (base 2) and will throw a
        // NumberFormatException for large values.
        // String bin1 = "10000000000000000000000000000001"; // error in Integer.valueOf(bin1, 2) since it exceeds Integer.MAX_VALUE
        int i2 = Integer.valueOf(bin1, 2); // binary string -> int
        System.out.println("binary str(1010) to i2: " + i2);
        if (i1 != i2) { throw new AssertionError("i1 and i2 should be same"); }

        /* String <-> ArrayList */
        String fruitsDelimited = "apple,banana,grapes";
        List<String> fruitsList = Arrays.stream(fruitsDelimited.split(",")).toList();
        
        // Way 1: String.join()
        String fruitsListStr = String.join(",", fruitsList);
        if (!fruitsDelimited.equals(fruitsListStr)) { throw new AssertionError("fruitsDelimited and fruitsListStr should be same"); }
        // Way 2: stream().reduce()
        String fruitsListStr2 = fruitsList.stream().reduce((a, b) -> (a + "," + b)).get();
        if (!fruitsDelimited.equals(fruitsListStr2)) { throw new AssertionError("fruitsDelimited and fruitsListStr2 should be same"); }
        // Way 3: stream().collect(Collectors.joining())
        String fruitsList2Str = fruitsList.stream().collect(Collectors.joining(","));
        if (!fruitsDelimited.equals(fruitsList2Str)) { throw new AssertionError("fruitsDelimited and fruitsList2Str should be same"); }

        /* Character <-> String */
        Character character = 'a';
        // - Way 1: Character.toString() and String.charAt(0)
        String characterStr = character.toString(); // Or use String.valueOf(character); // internally it calls Object.toString().
        // Character.parseCharacter(characterStr); // parse isn't available for Character but for every other wrapper class.
        Character character2 = characterStr.charAt(0);
        if (!character.equals(character2)) { throw new AssertionError("character and character2 should be same"); }
        
        /* Character <-> char */
        char char1 = character; // or use character.charValue()
        
        Character character3 = Character.valueOf(char1); // auto-boxing
        if (!character.equals(character3)) { throw new AssertionError("character and character3 should be same"); }

        /* char <-> String */
        String charStr = String.valueOf(char1);
        char char2 = charStr.charAt(0);
        if (char1 != char2) { throw new AssertionError("char1 and char2 should be same"); }

        /* Set<Character> <-> String */
        Set<Character> charSet = new LinkedHashSet<>(); charSet.add('a'); charSet.add('b'); charSet.add('c');
        
        // String.join(",", charSet); // fails since charSet isn't CharSequence.
        // Way 1: stream().map() + Collectors.joining()
        String stringCharSet = charSet.stream().map(c -> String.valueOf(c)).collect(Collectors.joining(",")); // c -> String.valueOf(c) is same as String::valueOf
        // Way 2: StringBuilder and for-each loop
        StringBuilder sb1 = new StringBuilder();
        for(char c: charSet){ sb1.append(c + ",");}
        sb1.setLength(sb1.length() - 1); // Remove the trailing comma
        if (!sb1.toString().equals(stringCharSet)) { throw new AssertionError("sb1 and stringCharSet should be same"); } // stringCharSet order might be different in case of HashSet.

        // PTR: joining() doesn't add the delimiter at the end.
        
        System.out.println();

        /* Min/Max of a collection */
        // - Way 1: Collections.min() and Collections.max() with or without custom comparator.
        // - Way 2: stream().min() and stream().max() with or without custom comparator.
         List<Integer> nums = Arrays.asList(5, 2, 8, 1, 4);
         int min1 = Collections.min(nums);
         int min2 = nums.stream().min(Integer::compareTo).get();
         if (min1 != min2) { throw new AssertionError("min1 and min2 should be same"); }

        /* Print array[] elements */
        int[] arr = {1, 2, 3, 4, 5};
        int[][] arr2D = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println("arr[]: " + arr); // [I@6d6f6e28
        // To print something meaningful, we can use Arrays.toString() for 1D array and Arrays.deepToString() for multi-dimensional array.
        System.out.println("Arrays.toString(arr): " + Arrays.toString(arr)); // [1, 2, 3, 4, 5]
        System.out.println("Arrays.deepToString(arr2D): " + Arrays.deepToString(arr2D)); // [[1, 2], [3, 4], [5, 6]]

        /* Constant string of size n */
        String repeaterString = "0";
        String constantString = repeaterString.repeat(10); // "0000000000"
        System.out.println("N length string using str.repeat(n): " + constantString);

        /* ArrayList of same elements of size n */
        List<Integer> repeaterList = Collections.nCopies(10, 0); // [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println("N sized list using nCopies(n, value): " + repeaterList);

        /* Reverse a string */
        String str2 = "hello";
        // Way 1: StringBuilder/StringBuffer reverse() method
        String reversedStr1 = new StringBuilder(str2).reverse().toString();
        // Way 2: String to list and use Collections.reverse()
        List<String> str2List = Arrays.asList(str2.split(""));
        Collections.reverse(str2List);
        String reversedStr2 = String.join("", str2List);
        if (!reversedStr1.equals(reversedStr2)) { throw new AssertionError("reversedStr1 and reversedStr2 should be same"); }

        /* int[] <-> List<Integer> */
        int[] intArray = {1, 2, 3, 4, 5};
        List<Integer> intList = Arrays.stream(intArray).boxed().toList();
        int[] intArray2 = intList.stream().mapToInt(Integer::intValue).toArray();
        if (!Arrays.equals(intArray, intArray2)) { throw new AssertionError("intArray and intArray2 should be same"); }
        // PTR: For char[] <-> List<Character>, we can use String as an intermediate since there's no CharStream in Arrays.
        char arrChar[] = {'a', 'b', 'c'};
        String arrCharStr = new String(arrChar);
        List<Character> arrCharList = arrCharStr.chars().mapToObj(c -> (char)c).collect(Collectors.toList());

        // PTR Trick: LinkedList<>().get(index) works in O(n), but it searches from either
        // start or end depending on whether index is in first half or second half of the list.
        
        System.out.println();

        /* Math.class */
        // PTR: abs(), max(), min() accept int, long, float and double and return the same type as input. 
        System.out.println("Math.abs(-5): " + Math.abs(-5));
        System.out.println("Math.max(5, 10): " + Math.max(5, 10));
        System.out.println("Math.min(5, 10): " + Math.min(5, 10));
        
        // PTR: sqrt(), pow(), log(), log10(), exp(), ceil(), floor() accept double and return double.
        System.out.println("Math.sqrt(16): " + Math.sqrt(16));
        System.out.println("Math.pow(2, 3) ~ 2^3: " + Math.pow(2, 3));
        System.out.println("Math.log(10) ~ natural log (base e): " + Math.log(10));
        System.out.println("Math.log10(100): " + Math.log10(100));
        System.out.println("Custom cal. of log(16) base 2 ~ Math.log(n)/Math.log(2):" + Math.log(16)/Math.log(2));
        System.out.println("Math.exp(2) ~e^2: " + Math.exp(2));
        System.out.println("Math.ceil(2.3): " + Math.ceil(2.3));
        System.out.println("Math.floor(2.7): " + Math.floor(2.7));
        
        // PTR: round() accept double and return long.
        System.out.println("Math.round(2.3): " + Math.round(2.3));
        System.out.println("Math.round(2.7): " + Math.round(2.7));
        
        // PTR: sin(), cos(), tan() accept double (in radians) and return double.
        System.out.println("Math.sin(Math.PI / 2): " + Math.sin(Math.PI / 2));
        System.out.println("Math.cos(Math.PI): " + Math.cos(Math.PI));
        System.out.println("Math.tan(Math.PI / 4): " + Math.tan(Math.PI / 4));

        // Constants:
        System.out.println("Math.E: " + Math.E);
        System.out.println("Math.PI: " + Math.PI);
        System.out.println("Math.TAU: " + Math.TAU); // Tau = 2*Pi

        // random() : returns double E [0.0, 1.0)
        System.out.println("Math.random(): " + Math.random());
        System.out.println("int random in range [min=5, max=10): " + (int)(Math.random() * (10 - 5) + 5)); // [5, 10)
        
        // Formula: closed range random: (int)(Math.random() * (max - min + 1)) + min; // [min, max]
        System.out.println("int random in range [min=5, max=10]: " + (int)(Math.random() * (10 - 5 + 1) + 5)); // [5, 10]

        /* Random.class : use methods like nextInt(), nextDouble(), nextBoolean(), nextLong() */
        Random random = new Random();
        System.out.println("Random.nextInt() ~ till INT_MAX: " + random.nextInt());
        System.out.println("Random.nextInt(10) ~ [0, 9]: " + random.nextInt(10));
        System.out.println("Random.nextInt(5, 10) ~ [5, 9]: " + (random.nextInt(5, 10)));
        // Trick: To have an inclusive max, we can use random.nextInt(min, max+1) OR min + random.nextInt(max - min + 1)

        exercises();

        // ----------------can skip----------------
        /* Integer[] <-> List<Integer> */
        Integer[] intWrapperArray = {1, 2, 3, 4, 5};
        List<Integer> intList2 = Arrays.asList(intWrapperArray);
        List<Integer> intList3 = new ArrayList<>();
        Collections.addAll(intList3, intWrapperArray);
        if (!intList2.equals(intList3)) { throw new AssertionError("intList2 and intList3 should be same"); }
        Integer[] intWrapperArray2 = intList2.toArray(new Integer[0]);
        if (!Arrays.equals(intWrapperArray, intWrapperArray2)) { throw new AssertionError("intWrapperArray and intWrapperArray2 should be same"); }

    }

    public static String getRadixString(int n, int radix) {
        // Basic binary conversion
        /**
         * 2 | 10
         * 2 | 5 ... 0
         * 2 | 2 ... 1
         * 2 | 1 ... 0
         * 2 | 0 ... 1
         * => 1010
         */

        // similarly for octal and hexal
        /**
         * 8 | 10
         * 8 | 1 ... 2
         * 8 | 0 ... 1
         * => 12
         */
        if(n == 0) return "0";
        if(n == Integer.MIN_VALUE) return "1" + "0".repeat(31);

        boolean isNegative = false;
        if (n < 0) {
            n = -n;
            isNegative = true;
        }

        String ans = "";
        while (n != 0) {
            ans += String.valueOf(n % radix);
            n = n / radix;
        }
        if (isNegative) {
            // take 2's complement and add 1 -> valid only for radix 2
            // -8 in octal = -10, -81 in octal = -121 or in short -0o121, -8 in hexal = -8, -81 in hexal = -51 or in short -0x51.

            // PTR: at this point ans is not reversed yet.
            if (radix == 2) {
                StringBuilder sb = new StringBuilder("1".repeat(32));
                for (int i = 0; i < ans.length(); i++) {
                    sb.setCharAt(i, ans.charAt(i) == '0' ? '1' : '0');
                }
                // add 1 to the 2's complement
                for (int i = 0; i < sb.length(); i++) {
                    if (sb.charAt(i) == '0') {
                        sb.setCharAt(i, '1');
                        break;
                    } else {
                        sb.setCharAt(i, '0');
                    }
                }
                ans = sb.toString();
            } else {
                ans += "-";
            }
        }
        return new StringBuilder(ans).reverse().toString();
    }

    public static void exercises() {
        System.out.println("\n-------------Exercises-------------");
        exercise1();
        exercise2();
    }
    
    public static void exercise1() {
        // ref: https://stackoverflow.com/questions/5911174/finding-key-associated-with-max-value-in-a-java-map
        // GFG: https://www.geeksforgeeks.org/problems/tie-breaker/1
        // Q1: Given a map, find the key with the maximum value. 
        // If there are multiple keys with the same maximum value, return the lexicographically smallest key among them.
        // Soln:
        // Conventional way
        Map<String, Integer> mp = new HashMap<>();
        mp.put("key1", 1);
        mp.put("key2", 2);
        mp.put("key3", 3);
        mp.put("key4", 4);
        mp.put("key5", 5);

        int max_value = Integer.MIN_VALUE;
        String ans1 = "";
        for (Map.Entry<String, Integer> e : mp.entrySet()) {
            if (e.getValue() > max_value) {
                ans1 = e.getKey();
                max_value = e.getValue();
            } else if (e.getValue() == max_value) {
                ans1 = ans1.compareTo(e.getKey()) < 0 ? ans1 : e.getKey();
            }
        }
        // Using stream
        String ans2 = mp.entrySet().stream().max((e1, e2) -> {
            if (e1.getValue().equals(e2.getValue())) {
                return e1.getKey().compareTo(e2.getKey());
            }
            return e1.getValue() - e2.getValue();
        }).map(Map.Entry::getKey).orElse(null); // instead of .map(), using .get().getKey() will give NoSuchElementException if the stream is empty.

        String ans3 = mp.entrySet().stream()
                .max(Comparator.comparing(Map.Entry<String, Integer>::getValue)
                        .thenComparing(Comparator.comparing(Map.Entry<String, Integer>::getKey).reversed())).map(Map.Entry::getKey).orElse(null);

        String ans4 = mp.entrySet().stream().reduce((m1, m2) -> {
            if (m1.getValue() > m2.getValue())
                return m1;
            if (m1.getValue() < m2.getValue())
                return m2;
            return m1.getKey().compareTo(m2.getKey()) > 0 ? m1 : m2;
        }).map(Map.Entry::getKey).orElse(null);

        if (!ans1.equals(ans2) || !ans1.equals(ans3) || !ans1.equals(ans4)) {
            throw new AssertionError("ans1, ans2, ans3 and ans4 should be same");
        }
    }
    public static void exercise2() {
        // Q2: 
    }
}

/**
 * Output:
 * -------
 * Unsigned right shift of Integer.MIN_VALUE >>> 1: 2^30: 1073741824
 * [hello]
 * a,b,c,d,e,f,g,
 * int(10) to bin1: 1010, bin2: 1010, bin3: 1010
 * int(-1) using toBinaryString: 11111111111111111111111111111111
 * int(-1) using getRadixString: 11111111111111111111111111111111
 * binary str(1010) to i2: 10
 * 
 * arr[]: [I@610455d6
 * Arrays.toString(arr): [1, 2, 3, 4, 5]
 * Arrays.deepToString(arr2D): [[1, 2], [3, 4], [5, 6]]
 * N length string using str.repeat(n): 0000000000
 * N sized list using nCopies(n, value): [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * 
 * Math.abs(-5): 5
 * Math.max(5, 10): 10
 * Math.min(5, 10): 5
 * Math.sqrt(16): 4.0
 * Math.pow(2, 3) ~ 2^3: 8.0
 * Math.log(10) ~ natural log (base e): 2.302585092994046
 * Math.log10(100): 2.0
 * Custom cal. of log(16) base 2 ~ Math.log(n)/Math.log(2):4.0
 * Math.exp(2) ~e^2: 7.38905609893065
 * Math.ceil(2.3): 3.0
 * Math.floor(2.7): 2.0
 * Math.round(2.3): 2
 * Math.round(2.7): 3
 * Math.sin(Math.PI / 2): 1.0
 * Math.cos(Math.PI): -1.0
 * Math.tan(Math.PI / 4): 0.9999999999999999
 * Math.E: 2.718281828459045
 * Math.PI: 3.141592653589793
 * Math.TAU: 6.283185307179586
 * Math.random(): 0.4083565631654592
 * int random in range [min=5, max=10): 5
 * int random in range [min=5, max=10]: 10
 * Random.nextInt() ~ till INT_MAX: -1379248069
 * Random.nextInt(10) ~ [0, 9]: 4
 * Random.nextInt(5, 10) ~ [5, 9]: 9
 * 
 * -------------Exercises-------------
 */