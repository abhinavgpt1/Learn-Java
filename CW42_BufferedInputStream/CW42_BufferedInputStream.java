import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CW42_BufferedInputStream {
    public static void main(String[] args) {
        // src: https://www.geeksforgeeks.org/java-io-bufferedinputstream-class-java/
        // internal buffer array is created when the BufferedInputStream is created
        // this buffer is filled when bytes from stream are read

        // constructors
        // BufferedInputStream(InputStream in)
        // BufferedInputStream(InputStream in, int bufferSize) // default buffer size is 8192 bytes = 8KB

        // methods
        // int available() // returns the number of bytes that can be read from the input stream
        // int read() // reads a byte of data from the input stream
        // int read(byte[] b, int off, int len) // reads up to len bytes of data from the input stream into an array of bytes b, starting at offset off
        // int read(byte[] b) // this version of read doesn't belong to BufferedInputStream, and is defined in FilterInputStream -> doens't buffer
        // void mark(int readAheadLimit) // marks the current position in the input stream // readAheadLimit is the maximum number of bytes that you promise to read once mark(readAheadLimit) is called
            // not your traditional seek() method to jump to any position in the file
            // Use RandomAccessFile for that
        // boolean markSupported() // returns true if the input stream supports the mark() and reset() method. By default, markSupported() are not supported by InputStream
        // void reset() // resets the input stream to the last marked position. If you read more than readAheadLimit bytes after calling mark(), the mark is invalidated and reset() will throw an IOException.
        // int skip(long n) // skips n bytes of data from the input stream

        // common concrete functions from InputStream (abstract class) like readAllBytes(), readNBytes() are available

        basicFunctionsAndProgram();
        markAndReset();

        // PTR: 
        // To read I/O 
        // - use System.in (InputStream) directly in BufferedInputStream.
        // eg. new BufferedInputStream(System.in); // refer tech13_readIOUsingStream.java
    }

    public static void basicFunctionsAndProgram() {
        try {
            FileInputStream fis = new FileInputStream("CW42_sampleBufferedInput.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            
            System.out.println("Number of bytes to read:" + bis.available()); //6 char + 1 new line + 1 carriage return = 8 bytes
            bis.skip(2); // skips first 2 bytes
            System.out.println("Skipping \"ab\", the output of file is:");
            System.out.println("---------------------------------------");
            // byte by byte reading (slow). Use read(byte[] b) for faster reading.
            int ch = -1;
            while ((ch = bis.read()) != -1) {
                System.out.print((char) ch); // without typecasting, the unsigned byte value aka ASCII will be printed (in this case since .txt conatins [a-zA-Z])
            }
            System.out.println();

            // fis.close(); // not recommended to just close the underlying stream, and not the outer streams
            bis.close(); // close the outermost stream
            
            /**
             * When you close the underlying stream (fis), the BufferedInputStream (bis) will no longer be able to read from it, and further operations on bis may throw an exception.
             * However, the correct and safe way fis to close the outermost stream (bis). 
             * bis calls the linked InputStream object to close -> just like a chain reaction eg. linked list deletion.
             * When you call bis.close(), it will automatically close the underlying stream (fis) as well.
             */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void markAndReset(){
        // mark(readAheadLimit) <- readAheadLimit is the maximum number of bytes that you promise to read once mark function is called
        try {
            FileInputStream fis = new FileInputStream("CW42_sampleBufferedInput.txt");
            BufferedInputStream bis = new BufferedInputStream(fis, 2);
            /**
             * Q- Why is the mark(readAheadLimit) not getting invalid for DEFAULT_SIZE BufferedInputStream object?
             * A- BufferedInputStream maintains an internal buffer (default size is 8192 bytes).
             * The definition says, the mark will only become invalid if you read more than the specified readAheadLimit from the buffer after marking.
             * However, as long as you do not read more than the buffer size (or the stream does not refill the buffer), the mark remains valid.
             * In your case, the buffer is large enough to accommodate your reads, so the mark does not get invalidated even though you set a small read limit (1).
             * 
             * Key Point:
             * The mark becomes invalid only if you read more than the specified readAheadLimit and the buffer needs to be refilled from the underlying stream.
             * In short, if you breach read operations more than max(readAheadLimit, buffer size) then error will come
             */
            System.out.println("mark() & reset():");
            System.out.println("-----------------");
            // when readAheadLimit is less than the buffer size, the mark will not be invalidated unless read > buffer size
            System.out.println("Mark = 1, buffer size = 2:");
            System.out.println("--------------------------");
            bis.mark(1);
            for (int i = 0; i < 2; i++) { // output: "ab"
                System.out.print((char) bis.read());
            }
            System.out.println();
            bis.reset(); // no error
            try {
                for (int i = 0; i < 3; i++) {
                    bis.read();
                }
                bis.reset();
            } catch (IOException e) {
                System.out.println("IOException: Resetting to invalid mark -> due to read 3 times when buffer was 2, and mark was 1");
            }
            
            System.out.println();

            System.out.println("Mark = 3, buffer size = 2:");
            System.out.println("--------------------------");
            bis.mark(3);
            for (int i = 0; i < 3; i++) { // output: "abc"
                System.out.print((char) bis.read());
            }
            System.out.println();
            bis.reset(); // no error
            try {
                for (int i = 0; i < 4; i++) {
                    bis.read();
                }
                bis.reset();
            } catch (IOException e) {
                System.out.println("IOException: Resetting to invalid mark -> due to read 4 times when buffer was 2, and mark was 3");
            }
            bis.close();

            // PTR:
            // 1. mark() can be applied even after some characters were ready already. But there won't be any way to go back to the start of the file.
            // 2. mark() != seek(pos) -> seek (c++, or in general) helps to go to any position
            // 3. bis.markSupported() // always true for BufferedInputStream
            // 4. mark() doesn't rely only on readAheadLimit to invalidate, but also on buffer to be refilled -> basically, if num of reads exceeds max(readAheadLimit, buffer size) before reset(), then exception will be thrown
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
 * Number of bytes to read:14
 * Skipping "ab", the output of file is:
 * ---------------------------------------
 * cdef
 * ghijkl
 * mark() & reset():
 * -----------------
 * Mark = 1, buffer size = 2:
 * --------------------------
 * ab
 * IOException: Resetting to invalid mark -> due to read 3 times when buffer was 2, and mark was 1
 * 
 * Mark = 3, buffer size = 2:
 * --------------------------
 * def
 * IOException: Resetting to invalid mark -> due to read 4 times when buffer was 2, and mark was 3
 */