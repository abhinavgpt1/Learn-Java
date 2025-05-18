import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CW43_BufferedOutputStream {
    public static void main(String[] args) {
        // BufferedOutputStream improves the efficiency of writing data to and output stream by buffering data. Saves the per byte writing overhead as seen in looping byte-by-byte code in FileOutputStream

        // constructors
        // BufferedOutputStream(OutputStream out) // creates a buffered output stream with a default buffer size = 8192 bytes = 8KB
        // BufferedOutputStream(OutputStream out, int bufferSize) // creates a buffered output stream with a specified buffer size
            // unlike FileOutputStream, there is no option to open in append mode, which makes sense to include in FileOutputStream only,
            // since it is related to handling files, whereas BufferedOutputStream is general purpose O/P stream (not just for handling files, eg. network etc).

        // methods
        // void write(int b) // writes a single byte to the output stream
        // void write(byte[] b, int off, int len) // writes len bytes from the specified byte array b, starting at offset off
            // void write(byte[] b) // writes the entire byte array b to the output stream, but this doesn't belong to BufferedOutputStream, and is defined in FilterOutputStream
        // synchronized void flush() // forces any buffered data to be written to the underlying output stream

        try {
            FileOutputStream fos = new FileOutputStream("CW43_sampleBufferedOutput.txt");
            // FileOutputStream fos = new FileOutputStream("CW43_sampleBufferedOutput.txt", true); // append mode -> this param isn't available in BufferedOutputStream
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(97);

            byte[] b = { 98, 99, 100, 101, 102 };
            bos.write(b, 0, b.length); // this doesn't writes immediately. It rather waits for the underlying buffer to be filled, unless provoked by flush() or close().
            // Q- what happens if buffer is almost full, say 20 bytes left, and I try writing 50 bytes?
            // A- Immediately, write happens taking the 20 from 50. Rest 30 bytes are stored in buffer. Hence, it is a good practice to flush (atleast in c++ I see now).
            bos.flush();

            // --------say, here was a demand to have above data been sent already to downstream system, then flush() would be helpful--------

            bos.write(103); //g
            // fos.close(); // not recommended to just close the underlying stream, and not the outer streams
            bos.close();
            System.out.println("written abcdefg to file CW43_sampleBufferedOutput.txt");
            /**
             * When you close the underlying stream (fos), the BufferedInputStream (bos) will no longer be able to read from it, and further operations on bos may throw an exception.
             * However, the correct and safe way is to close the outermost stream (bos). 
             * bos calls the linked InputStream object to close -> just like a chain reaction eg. linked list deletion.
             * When you call bos.close(), it will automatically close the underlying stream (fos) as well.
             */

            // Q- Given, String s = "T".repeat(9000); What, if I try writing a very big string? Can windows crash?
            // A- Consider, it this way, can it happen if I write for infinty? No, right, we'll get OutOfMemory issue, but windows is reliable enough to not crash.
            /**
             * Gpt's answer:
             * If you try to create a very large object in memory (e.g., String s = "T".repeat(2_000_000_000);),
             * your program may crash with an OutOfMemoryError, but this will not crash Windows itself.
             * The file system may run out of disk space, causing an IOException, but again, this will not crash Windows.
             */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Output:
 * -------
 * 
 */