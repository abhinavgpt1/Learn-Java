import java.util.ArrayList;
import java.util.List;

public class tech12_splitFunction {
    public static String[] splitFun(String splitStr, String delimiter) {
        int prevMarkedIndex = 0, indexOfDelimiter = 0;
        List<String> ans = new ArrayList<>();
        while (indexOfDelimiter != -1) {
            indexOfDelimiter = splitStr.indexOf(delimiter, prevMarkedIndex);
            if (indexOfDelimiter == -1) {
                ans.add(splitStr.substring(prevMarkedIndex));
                break;
            } else {
                ans.add(splitStr.substring(prevMarkedIndex, indexOfDelimiter));
                prevMarkedIndex = indexOfDelimiter + delimiter.length();
            }
        }
        return ans.toArray(new String[0]);
        // https://www.w3docs.com/snippets/java/convert-list-to-array-in-java.html#:~:text=To%20convert%20a%20List%20to,%3B%20String%5B%5D%20array%20%3D%20list.
    }

    public static void main(String[] args) {
        String splitStr = "qwe,qwer,qwert,qwerty,noloop";
        System.out.println(splitStr);

        System.out.println();
        System.out.println("Delimiter ','");
        System.out.println("-------------");
        for (String s : splitFun(splitStr, ","))
            System.out.println(s);
        
        System.out.println();
        System.out.println("Delimiter \",q\"");
        System.out.println("--------------");
        System.out.println();
        for (String s : splitFun(splitStr, ",q"))
            System.out.println(s);
    }

    /**
     * Output:
     * qwe,qwer,qwert,qwerty,noloop
     *
     * Delimiter ','
     * -------------
     * qwe
     * qwer
     * qwert
     * qwerty
     * noloop
     * 
     * Delimiter ",q"
     * --------------
     * 
     * qwe
     * wer
     * wert
     * werty,noloop
     */
}
