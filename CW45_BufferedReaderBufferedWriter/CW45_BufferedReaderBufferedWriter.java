import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CW45_BufferedReaderBufferedWriter {
    public static void main(String[] args) {
        // constructors
        // BufferedWriter(Writer obj)
        // BufferedWriter(Writer obj, int bufferSize)
        try {
            FileWriter fw = new FileWriter("CW45_write.txt", true);// append mode
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello");
            // bw.newLine(); // newline character (platform-specific) -> equivalent to System.lineSeparator()
            bw.newLine(); // IMP. method (Not present in BufferedInputStream)
            bw.write("World");
            
            // bw.flush(); // to flush the buffer immediately
            System.out.println("Write completed");

            // fw.close(); // not recommended to just close the underlying stream, and not the outer streams
            bw.close(); // close the outermost stream
            /**
             * When you close the underlying stream (fis), the BufferedInputStream (bis) will no longer be able to read from it, and further operations on bis may throw an exception.
             * However, the correct and safe way fis to close the outermost stream (bis). 
             * bis calls the linked InputStream object to close -> just like a chain reaction eg. linked list deletion.
             * When you call bis.close(), it will automatically close the underlying stream (fis) as well.
             */
        } catch (IOException e) {
            e.printStackTrace();
        }

        // constructors
        // BufferedReader(Reader obj)
        // BufferedReader(Reader obj, int bufferSize)

        try {
            System.out.println("Read initiated");
            FileReader fr = new FileReader("CW45_write.txt");
            BufferedReader br = new BufferedReader(fr);
            // br.read(); // reads a single character -> returns int value of the character
            // br.read(char [] chArr); //reads into char array -> returns number of characters read
            // br.read(char [] chArr, offset, length); // reads into char array from offset to length -> returns number of characters read

            // br.readLine(); // reads a line of text (sans newline char) until a newline character is encountered
            // A line is considered to be terminated by any one of 
            // - a line feed ('\n')
            // - a carriage return ('\r'), 
            // - a carriage return followed immediately by a line feed
            // - by reaching the end-of-file (EOF).
            // br.readLine() returns null when EOF is reached

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // PTR: 
        // To read write I/O 
        // - use InputStreamReader and OutputStreamWriter since their constructor support System.in (InputStream) and System.out (PrintStream aka OutputStream).
        // - Pass them to BufferedReader and BufferedWriter. // refer tech13_readIOUsingStream.java

        // Methods like reset(), mark(), skip() and markSupported are available in BufferedReader.
        // read: https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/

        // These read(char[]) and write(char[]) methods belong to parent classes and don't follow buffering.
    }
}
/**
 * Output
 * ------
 * Write completed
 * Read initiated
 * Hello
 * World
 */