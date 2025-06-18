import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Scanner;

public class CW46_StreamsMiscellaneous {
    public static void main(String[] args) {
        printStreamExample();
        printWriterExample();
        
        // theory: RandomAccessFile()
            // -> a random access file behaves as a lareg array of bytes stored in file system. You can traverse using seek().
            // -> read and write in bytes internally (Modes = read/read-write/synchronized mode read-write)
            // -> readUTF() and writeUTF() methods are used to read and write strings.
        
        // scannerExample(); //enable it, compile and run to interact.
        objectInputOutputStreamExample();
    }
    public static void printStreamExample() {
        System.out.println("----------------PrintStream Example-----------------");
        // ref: https://www.geeksforgeeks.org/java/java-io-printstream-class-java-set-1/
        // ref: https://www.geeksforgeeks.org/java/java-io-printstream-class-java-set-2/
        
        // PrintStream never throws IOException. flush() is invoked automatically after byte array is written.
        // Helps write formatted data to OutputStream. eg. primitive types liek int and long are formatted as text rather than bytes.
        // All characters printed by a PrintStream are converted into bytes using the platform's default character encoding
        // It can create a file if it doesn't exist
        // By default no buffering happens, unless the underlying OutputStream supports it.

        // constructors:
        // -------------
        // new PrintStream(File file)
        // new PrintStream(String fileName)
        // new PrintStream(OutputStream out)
        // new PrintStream(OutputStream out, boolean autoFlush) 
        // -> autoFlush is required say if OutputStream is BufferedOutputStream
        // -> You won’t see a difference on the console in most cases, but autoFlush matters for files, sockets, and real-time output scenarios.
        // qq-What if you don't flush?
            // -> Writing to files or network sockets: Data may not appear in the file or on the network until you flush.
            // -> Long-running programs: If you write with print() and don’t flush, output may be delayed.
       
        PrintStream ps = new PrintStream(System.out, true); // autoFlush is true

        // methods:
        // --------
        // ps.print(char ch / double d / boolean b, Object obj....any datatype)
            // -> for obj, it triggers the toString() method
            // -> not applicable for byte -> use write(int b) instead
        // ps.println()
        // ps.println(char ch / double d / boolean b, Object obj....any datatype)
        // ps.format(String formattedStr, Object args) -> returns PrintStream object
        // ps.printf(String formattedStr, Object args) -> returns PrintStream object
            // uses format() internally
        // ps.append(char ch / double d / boolean b, Object obj....any datatype) - appends to the stream
        // ps.write(int b) - writes a byte to the stream
        // ps.write(byte[] b) - writes a byte array to the stream
        // ps.write(byte[] b, int offset, int count)
        // ps.checkError() -> flushes first and then checks for any error in the stream

        ps.print(65);
        ps.println();
        ps.printf("this is %c", 'b');
        ps.println();
        ps.format("writing %s", "hello").append('!');        
        ps.println();
        // ps.close(); // do this for anything but System.out
    }
    public static void printWriterExample() {
        // ref: https://www.geeksforgeeks.org/java/java-io-printwriter-class-java-set-1/
        // ref: https://www.geeksforgeeks.org/java/java-io-printwriter-class-java-set-2/
        // same functionality and methods as PrintStream...it's just that PrintWriter is character-oriented while PrintStream is byte-oriented.
        
        // PrintWriter class prints formatted representations of objects to a text-output stream. 
        // It implements all of the print methods found in PrintStream. 
        // It does not contain methods for writing raw bytes.

        // checkError() has a counterpart setError() which sets error state of PrintWriter/PrintStream instance.
        // codes from GFG ref as good enough.
    }
    public static void scannerExample() {
        System.out.println("----------------Scanner Example-----------------");

        // ref: https://www.geeksforgeeks.org/scanner-class-in-java/
        // Allows user to read values of various types. 
        // Helps read numeric values from keyboard/file without having to convert them from string.

        // constructors:
        // -------------
        // new Scanner(InputStream in)
        // new Scanner(File file)
        // new Scanner(String source) != string path to file
        
        Scanner in = new Scanner("token1 token2 token3 token4");
        while (in.hasNext()) {
            String line = in.next(); // line separator regex = ".*(\r\n|[\n\r\u2028\u2029\u0085])|.+$"
            // \r\n — matches a Windows-style line ending (carriage return + line feed, i.e., CRLF).
            // [\n\r\u2028\u2029\u0085] — matches any one of the following single characters:
            // \n — Line Feed (LF, Unix/Linux/Mac line ending)
            // \r — Carriage Return (CR, old Mac line ending)
            // \u2028 — Unicode Line Separator
            // \u2029 — Unicode Paragraph Separator
            // \u0085 — Unicode Next Line (NEL)
            System.out.println(line);
        }
        in.close();
            
        Scanner sc = new Scanner(System.in);

        // PTR: 
        // 1. To read a single character, we use next().charAt(0)
        // -> hasNextInt(): This method is used to check if the token is an integer.
        // -> hasNextLine(): This method is used to check if there is an input in next line.
        // -> useDelimiter(): This method changes the default whitespace delimiter.
            // eg. sc.useDelimiter(",");
            // https://stackoverflow.com/questions/28766377/how-do-i-use-a-delimiter-with-scanner-usedelimiter-in-java

        // Sometimes, we have to check if the next value we read is of a certain type or if the input has ended (EOF marker encountered). 
        // Then, we check if the scanner's input is of the type we want with the help of hasNextDataType() functions where DataType is the type we are interested in. 
        // The function returns true if the scanner has a token of that type, otherwise false.

        // Sample code:
        // ------------
        int sum = 0;
        // works fine with whitespace and newline as delimiter
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                int x = sc.nextInt(); // works fine with whitespace and newline as delimiter
                System.out.println(x);
                sum += x;
            } else {
                String input = sc.next(); // works fine with whitespace and newline as delimiter
                System.out.println(input);
                if (input.equalsIgnoreCase("done")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer or type 'done' to finish.");
                }
            }
        }
        System.out.println(sum);
        sc.close();
        /*
         * > 1 2 3
         * 1
         * 2
         * 3
         * > 4 5
         * 4
         * 5
         * > done
         * done
         * 15
         */

        // qq-does in.close() close System.in?
        // ans- Yes, calling in.close() will also close System.in.
        // calling close() on the Scanner, closes the underlying input stream (System.in). 
        // After that, you cannot read from System.in again in your program.
    }
    public static void objectInputOutputStreamExample() {
        System.out.println("----------------ObjectInputStream and ObjectOutputStream Example-----------------");
        // OIS and OOS are used for reading and writing objects to/from streams.
        // writing objects to a stream (file/newtwork/disk) is called serialization.
        // reading objects from a stream is called deserialization.

        // PTR: 
        // SERIALIZATION = WRITING i.e. serialize an object and make it writable to file

        // use writeObject() and readObject() methods to write and read objects.
        // readObject() returns an Object type, so you need to cast it to the appropriate type.
            // throws ClassNotFoundException

        // internally OOS passes to FOS to write in bytes to the file/stream. Similarly for OIS (see diagram in book).

        // constructor (just 1):
        // --------------------
        // new ObjectOutputStream(OutputStream out)
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("CW46_OIS_OOS_example.txt"));
            oos.writeObject(new Student(1,"John Doe")); //written as bytes to the file...hence file isn't human readable.
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // constructor (just 1):
        // --------------------
        // new ObjectInputStream(InputStream in)
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("CW46_OIS_OOS_example.txt"));
            // following methods are available due to inheritance/implementation.
            // ois.read();
            // ois.readInt();
            // ois.read(byte[] b)
            // ois.readAllBytes()
            // ois.readUTF()
            Student student = (Student)(ois.readObject());
            System.out.println(student.rollNo + " " + student.name);
            ois.close();
            
            // qq -  What if the class is not serializable?
            // ans - If the class is not serializable, you will get a java.io.NotSerializableException at runtime when you try to serialize an instance of that class.
            // This exception indicates that the class does not implement the java.io.Serializable interface, which is required for serialization to work.

            // qq - Serializable is an empty interface, so why do we need it?
            // ans - The Serializable interface is a marker interface in Java, which means it does not contain any methods or fields.
            // Its purpose is to signal or provide metadata to Java runtime that the class is eligible for serialization (i.e., its objects can be converted to a byte stream and restored later).

            // qq - Use of transient keyword in serialization?
            // ans - The transient keyword in Java is a field modifier used in the context of object serialization. 
            // Its primary purpose is to indicate that a particular field of a class should not be serialized when an object of that class is written to a persistent storage or transmitted over a network. 

            // eg:
            // class User implements Serializable {
            //     private String username;
            //     private transient String password; // Marked as transient
            //     public User(String username, String password) {
            //         this.username = username;
            //         this.password = password;
            //     }
            // }
            // In this example, when a User object is serialized, the username field will be included in the byte stream, 
            // but the password field will be excluded due to the transient keyword.
            // Upon deserialization, the password field in the reconstructed User object will be null.
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
class Student implements Serializable { //this implementation is required for OIS and OOS to work, else NotSerializableException is thrown.
    int rollNo;
    String name;
    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }
}

/**
 * ----------------PrintStream Example-----------------
 * 65
 * this is b
 * writing hello!
 * 
 * <Scanner example is skipped, enable the function, compile and run to interact>
 * 
 * ----------------ObjectInputStream and ObjectOutputStream Example-----------------
 * 1 John Doe
 */