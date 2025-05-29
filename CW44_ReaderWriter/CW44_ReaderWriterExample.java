import java.io.CharArrayReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CW44_ReaderWriterExample {
    public static void main(String[] args) {
        // ----------Character stream----------
        // byte streams write ASCII 0-255. therefore english language can be
        // read/written using byte stream
        // we need character stream to handle unicode chars so as to handle langs apart
        // from eng.

        // adv of character stream
        // 1. Character streams automatically handle the conversion between bytes and
        // characters using a character encoding (like UTF-8, UTF-16, etc.). By default,
        // they use the system's default encoding, but you can specify another encoding
        // if needed.
        // 2. efficient than byte stream since byte stream follows a byte-at-a-time
        // read/write operation, whereas here, buffer-at-a-time read/write operations
        // are used.

        // ------Reader class--------
        // constructors for read()
        // -----------------------
        // fr.read() -> returns int i.e. character as int read
        // fr.read(char[] chArr) -> reads into chArr
        // fr.read(char[] chArr, int startIndex, int N) -> reads into chArr starting from startIndex for char of len=N. Meanwhile this should not breach size of chArr
            // ---formula--> startIndex + N < chArr.length

        // -----------Writer class---------
        // constructors for write()
        // ------------------------
        // fw.write(int c) -> writes first 16bits from c
        // fw.write(char[] chArr) -> writes from chArr
        // fw.write(char[] chArr, int offset, int N) -> writes from chArr starting from offset for chars = N. Meanwhile chArr size shouldn't be breached
            // ---formula--> startIndex + N < chArr.length
        // fw.write(String str)
        // fw.write(String str, int offset, int N)
            // ---formula--> startIndex + N < chArr.length

        // Additional methods
        // ------------------
        // fw.append(char ch) vs fw.write(ch)
        // append()
        // - Appends the specified character or sequence to the file.
        // - Returns the FileWriter itself, so you can chain calls.
        // write()
        // - Writes characters, arrays, or strings to the file.

        // flush()
        // close() - clears the output stream to get the resources free for reuse. This
        // automatically flushes the stream too.

        // RECALL: file connection is established by FileReader, FileWriter, and not by File.class object.

        readFromFile();
        writeToFile();
    }
    
    private static void readFromFile() {
        // constructors
        // FileReader(String str)
        // FileReader(File obj)
        // FileReader(FileDescriptor obj)
        System.out.println("---------Reader class Example---------");
        try {
            FileReader fr = new FileReader("CW44_read.txt");
            // ASCII 13 (carriage return) executes first before 10 (line feed = newline)
            int i = 0;
            while ((i = fr.read()) != -1) {
                System.out.println("char:" + (char) i + "-ascii:" + i);
                // PTR: to skip newline use -  if (i != 13 && i != 10) {continue}
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeToFile(){
        // constructors
        // FileWriter(String str)
        // FileWriter(String str, boolean append)
        // FileWriter(File obj)
        // FileWriter(File obj, boolean append)
        // FileWriter(FileDescriptor obj)

        System.out.println("---------Writer class Example---------");
        try {
            FileWriter fw = new FileWriter("CW44_write.txt", true); // opened in append mode
            fw.write(97); //a
            fw.write("bcd"); //bcd
            fw.write("abcdefghi", 4, 2); //ef - 0-based indexing
    
            char[] chArr = {'f', 'g', 'h'};
            fw.write(chArr, 1, 2); //off + len <= chArr.len
            System.out.println("abcdefgh written to file");
            fw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        // No FileNotFoundException
    }
}
/**
 * Output
 * ------
 * ---------Reader class Example---------
 * char:a-ascii:97
 * char:b-ascii:98
 * char:
 * -ascii:13
 * char:
 * -ascii:10
 * char:c-ascii:99
 * char:d-ascii:100
 * ---------Writer class Example---------
 * abcdefgh written to file
 * 
 */