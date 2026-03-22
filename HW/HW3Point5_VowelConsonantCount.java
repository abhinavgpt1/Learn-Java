public class HW3Point5_VowelConsonantCount {
    public static void main(String[] args) {
        // Input: Provide characters as no-space string
        int vowel = 0, consonant = 0;
        char arr[] = args[0].toCharArray();
        for(char c: arr) {
            if(isVowel(c))
                vowel++;
            else
                consonant++;
        }
        System.out.println("Vowels: " + vowel + "\nConsonants: " + consonant);
    }
    public static boolean isVowel(char ch) {
        if(!isChar(ch))
            throw new IllegalArgumentException("Not a character");

        ch = Character.toLowerCase(ch);
            switch (ch) {
                case 'a', 'e', 'i', 'o', 'u':
                    return true;
                default:
                    return false;
            }
    }

    public static boolean isChar(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }
}
/**
 * Input:
 * java Test qwertyuiop
 * 
 * Output:
 * -------
 * Vowels: 4
 * Consonants: 6
 */