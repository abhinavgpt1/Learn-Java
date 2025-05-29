import java.io.CharArrayReader;
import java.io.IOException;

public class CW44_00_CharArrayReader {
    public static void main(String[] args) {
        // Just like ByteArrayInputStream, we have CharArrayReader in character streams
        // constructors
		// new CharArrayReader(char[] chArr)
		// new CharArrayReader(char[] chArr, int offset, int N)

        char[] chArr = { 'a', 'b', 'c', 'd', 'e' };
        CharArrayReader car = new CharArrayReader(chArr);
        int ch;
        try {
            while((ch = car.read()) != -1) {
                System.out.println((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // No FileNotFoundException
    }
}
