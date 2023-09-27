import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CW38_StringClass {
    public static void main(String args[]) {

        String s1 = "BCE";
        String s2 = "BCE";
        String s3new = new String("BCE");
        String s4new = new String(s2);

        // s1 and s2 should have same reference and no one else
        System.out.println("w.r.t. s1");
        System.out.println(". " + (s1 == s2));
        System.out.println(". " + (s1 == s3new));
        System.out.println(". " + (s1 == s4new));
        System.out.println("w.r.t. s2");
        System.out.println(". " + (s2 == s3new));
        System.out.println(". " + (s2 == s4new));
        System.out.println("w.r.t. s3");
        System.out.println(". " + (s3new == s4new));

        callHeading("toUpperCase() and toLowerCase() anomaly:");
        String s5 = s1.toUpperCase();
        System.out.println(s5 == s1); // as s1 is already in uppercase
        String s5converted = s1.toUpperCase().toLowerCase().toUpperCase();
        System.out.println(s5converted == s1); // as s1.toUpperCase().toLowerCase() returns a newly created string

        String s6 = s1.toLowerCase();
        String s7 = s6.toLowerCase();
        System.out.println(s6 == s7); // same cases with toLowerCase()
        String s7converted = s6.toLowerCase().toUpperCase().toLowerCase();
        System.out.println(s7converted == s6);

        callHeading("String functions");
        String x = "qwertyqwertyqwerty";
        String xnew = new String(x);
        System.out.println(x.length());// 18
        System.out.println(x.equals(xnew));// true

        // to compare not equals don't use !=
        // https://stackoverflow.com/questions/8484668/java-does-not-equal-not-working
        callHeading("Note: Compare strings via equals() - for case equal and not equal scenarios");
        System.out.println(xnew != x);// should be false, but coming true
        System.out.println(!xnew.equals(x));// false

        callHeading("Split function");
        // split via delimiter
        String splitStr = "qwe,qwr,oqwrti,oeqwri";
        for (String s : splitStr.split(","))
            System.out.println(s);
        // split via regex
        for (String s : splitStr.split("[aeiou]"))
            System.out.println("on separate line via regex:" + s);

        String splitStrNoRecord = "yiea";
        System.out.println("no partition at last if delimiter is at end: " + splitStrNoRecord.split("[aeiou]").length); // 1

        callHeading("indexOf and lastIndexOf with fromIndex");
        System.out.println("indexOf ',': " + splitStr.indexOf(','));
        System.out.println("indexOf \",\" i.e. as string: " + splitStr.indexOf(","));
        System.out.println("indexOf ',' from first ',': " + splitStr.indexOf(',', splitStr.indexOf(',') + 1));
        System.out.println("lastIndexOf : " + splitStr.lastIndexOf(','));
        System.out.println("lastIndexOf from last ',' : " + splitStr.lastIndexOf(',', splitStr.lastIndexOf(',') - 1));
        System.out.println("indexOf if not found (-1): " + splitStr.lastIndexOf('-')); // -1

        callHeading("String of size 10 and character d");
        System.out.println(generateStringOfLengthNCharacters(10, 'd'));
        callHeading("Concatenation via stream with delimiter");
        System.out.println(concatenateStrings(splitStr.split(","), "[=+=]"));

        callHeading("substring functions");
        String base = "base camp in ohio";
        System.out.println(base.substring(5));
        System.out.println(base.substring(5, 9));// [8] is 'p'
        try {
            System.out.println(base.substring(-1));
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("beginIndex and endIndex in substring() should be +ve and within bounds unlike python");
        }

        callHeading("charAt");
        System.out.println("charAt(5): " + base.charAt(5) + " charAt(6): " + base.charAt(6) + " charAt(7): "
                + base.charAt(7) + " charAt(8): " + base.charAt(8));

        callHeading("String to char[]");
        char[] charArr = new char[10];
        Arrays.fill(charArr, 'q');
        base.getChars(5, 9, charArr, 3); // srcEnd not counted
        System.out.println("chars imported:" + new String(charArr));

        callHeading("isEmpty vs isBlank for whitespace string(len=5)");
        System.out.println("isBlank: " + "    ".isBlank());
        System.out.println("isEmpty: " + "    ".isEmpty());
        System.out.println("length: " + "     ".length());

        callHeading("compareTo - lex or length");
        System.out.println("abc vs jef (a(97)-j(106)): " + "abc".compareTo("jef"));
        System.out.println("abc vs abcdefg (3-7): " + "abc".compareTo("abcdefg"));
        System.out.println("ascii of a: " + (int) 'a' + ", ascii of j: " + (int) 'j');
    }

    public static void callHeading(String title) {
        System.out.println();
        System.out.println(title);
        String dashedLine = generateStringOfLengthNCharacters(title.length(), '-');
        System.out.println(dashedLine);
    }

    public static String generateStringOfLengthNCharacters(int n, char ch) {
        char charArray[] = new char[n];
        Arrays.fill(charArray, ch);
        return new String(charArray);
    }

    public static String concatenateStrings(String arg[], String delimiter) {
        // low performance and high memory usage
        // we can use stream or string builder
        // String concatenatedString = "";
        // for(String str : arg){
        // concatenatedString += str + delimiter;
        // }
        // return concatenatedString;
        return Stream.of(arg).collect(Collectors.joining(delimiter));
    }

    /**
     * Output:
     * -------
     * w.r.t. s1
     * . true
     * . false
     * . false
     * w.r.t. s2
     * . false
     * . false
     * w.r.t. s3
     * . false
     *
     * toUpperCase() and toLowerCase() anomaly:
     * ----------------------------------------
     * true
     * false
     * true
     * false
     *
     * String functions
     * ----------------
     * 18
     * true
     *
     * Note: Compare strings via equals() - for case equal and not equal scenarios
     * ---------------------------------------------------------------------------
     * true
     * false
     *
     * Split function
     * --------------
     * qwe
     * qwr
     * oqwrti
     * oeqwri
     * on separate line via regex:qw
     * on separate line via regex:,qwr,
     * on separate line via regex:qwrt
     * on separate line via regex:,
     * on separate line via regex:
     * on separate line via regex:qwr
     * no partition at last if delimiter is at end: 1
     *
     * indexOf and lastIndexOf with fromIndex
     * --------------------------------------
     * indexOf ',': 3
     * indexOf "," i.e. as string: 3
     * indexOf ',' from first ',': 7
     * lastIndexOf : 14
     * lastIndexOf from last ',' : 7
     * indexOf if not found (-1): -1
     *
     * String of size 10 and character d
     * ---------------------------------
     * dddddddddd
     *
     * Concatenation via stream with delimiter
     * ---------------------------------------
     * qwe[=+=]qwr[=+=]oqwrti[=+=]oeqwri
     *
     * substring functions
     * -------------------
     * camp in ohio
     * camp
     * beginIndex and endIndex in substring() should be +ve and within bounds unlike
     * python
     *
     * charAt
     * ------
     * charAt(5): c charAt(6): a charAt(7): m charAt(8): p
     *
     * String to char[]
     * ----------------
     * chars imported:qqqcampqqq
     *
     * isEmpty vs isBlank for whitespace string(len=5)
     * -----------------------------------------------
     * isBlank: true
     * isEmpty: false
     * length: 5
     *
     *
     * compareTo - lex or length
     * -------------------------
     * abc vs jef (a(97)-j(106)): -9
     * abc vs abcdefg (3-7): -4
     * ascii of a: 97, ascii of j: 106
     */
}