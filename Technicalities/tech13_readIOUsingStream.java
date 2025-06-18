import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class tech13_readIOUsingStream {
    public static void main(String[] args) {
        // console I/O operations using Byte and Character Streams
        usingByteStream();
        System.out.println(); // OutputStream needs to be active throughout the program
        // Q- why is not possible to reopen a closed stream in Java?
        // A- That's simply the nature of the underlying operating system constructs that Java streams represent. A stream is essentially a data conduit. Once you close it, it no longer exists.
        // https://stackoverflow.com/questions/33555283/why-is-not-possible-to-reopen-a-closed-standard-stream
        usingCharStream();
        
        // usingPrintStream(); -> use System.out 
        // eg. PrintStream ps = new PrintStream(System.out, true); // see CW46_StreamMiscellaneous.java@printStreamExample
    }
    public static void usingByteStream() {
        // FilterInputStream, BufferedInputStream and DataInputStream accepts InputStream aka System.in
        // FilterOutputStream, BufferedOutputStream and DataOutputStream accepts OutputStream ~= PrintStream aka System.out
        DataInputStream dis = new DataInputStream(System.in);
        System.out.print("Enter a line of text: ");
        String line = null;
        try {
            line = dis.readLine(); //readUTF() works weirdly
            /*
             * When you use readUTF() on console input, the console does not provide the length prefix or the expected format. 
             * As a result, readUTF() waits for the correct format, never finds it, and appears to "hang" or run infinitely, waiting for input that matches the expected structure.
             * Summary:
             * readLine() works because it just reads until a newline.
             */
            System.out.println("You entered: " + line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // dis.close(); // Avoid when bw wraps System.in

        System.out.println("STARTED: writing to console using DataOutputStream");
        DataOutputStream dos = new DataOutputStream(System.out);
        try {
            // dos.writeUTF(System.lineSeparator); //gives weird output for newline
            dos.writeChars("line1");
            dos.writeChars("\n"); //not ideal way to write new line
            dos.writeChars(line);
            dos.writeChars(System.lineSeparator()); //System.lineSeparator() is the ideal choice
            dos.writeChars("line3");
            dos.writeChars(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // dos.close(); // Avoid when bw wraps System.out
        System.out.println("ENDED: writing to console using DataOutputStream");
        
        // PTR: No sout here (if there exists dos.close() here)
        // When you close dos, it also closes the underlying System.out stream.
        // Any further attempt to write to System.out (including System.out.println()) will throw a java.io.IOException: Stream closed.

        // Recommendation:
        // Do not close streams (DataOutputStream, BufferedWriter, etc.) that wrap System.out or System.in in console applications.
        // Only close them if you opened a file or a custom stream.

        // PTR: ALWAYS use System.lineSeparator() instead of "\n" for new line.
        // PTR: For console I/O -> use Char Stream

        /**
         * Output:
         * -------
         * Enter a line of text: hello there from a deprecated function readLine()
         * You entered: hello there from a deprecated function readLine()
         * STARTED: writing to console using DataOutputStream
         * line1
         * hello there from a deprecated function readLine()
         * line3
         */

        // using BufferedInputStream will become inefficient from competitive coding perspective, as buffering won't help differentiate newLine() => so we need to read char by char
        // BufferedInputStream bis = new BufferedInputStream(System.in);
        // System.out.print("Enter a line of text: ");
        // StringBuilder sb = new StringBuilder();
        // try {
        //     int ch;
        //     while ((ch = bis.read()) != -1 && ch != '\n') { // read until newline
        //         sb.append((char) ch); // append character to StringBuilder -> this is correct as far as input is english character/integer (48-57, 97-122) aka lies in ASCII [0-127]. Was the input beyond this, then collaboration of bytes as single entity need to be done
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } finally {
        //     try {
        //         bis.close(); // close the BufferedInputStream
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }

    }
    public static void usingCharStream() {
        // Using InputStreamReader and OutputStreamReader. Not available straightaway from BufferedReader and BufferedWriter
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.print("Enter a line of text:");
        String line = null;
        try {
            line = br.readLine(); // reads a line of text
            System.out.println("You entered: " + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // br.close(); // Avoid when bw wraps System.in
            // this is valid for Scanner(System.in) as well

        System.out.println("STARTED: writing to console using BufferedWriter");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            bw.write("line1");
            bw.newLine();
            bw.write(line);
            bw.newLine();
            bw.write("line3");
            bw.newLine();
            bw.flush(); // make sure to flush :) otherwise the output may not appear (this happened actually)
        } catch (IOException e) {
            e.printStackTrace();
        }
        // bw.close(); // Avoid when bw wraps System.out
        System.out.println("ENDED: writing to console using BufferedWriter");

        // PTR: No sout here (if there exists bw.close() here)
        // When you close bw, it also closes the underlying System.out stream.
        // Any further attempt to write to System.out (including System.out.println()) will throw a java.io.IOException: Stream closed.

        // Recommendation:
        // Do not close streams (DataOutputStream, BufferedWriter, etc.) that wrap System.out or System.in in console applications.
        // Only close them if you opened a file or a custom stream.

        /**
         * Output:
         * -------
         * Enter a line of text:hello there!!
         * You entered: hello there!!
         * STARTED: writing to console using BufferedWriter
         * line1
         * hello there!!
         * line3
         */
    }
}
/**
 * Output:
 * -------
 * Enter a line of text: hello
 * You entered: hello
 * STARTED: writing to console using DataOutputStream
 * line1
 * hello
 * line3
 * ENDED: writing to console using DataOutputStream
 * 
 * Enter a line of text:there
 * You entered: there
 * STARTED: writing to console using BufferedWriter
 * line1
 * there
 * line3
 * ENDED: writing to console using BufferedWriter
 */