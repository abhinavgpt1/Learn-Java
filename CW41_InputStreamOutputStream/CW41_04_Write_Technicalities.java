import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CW41_04_Write_Technicalities {
    public static void main(String[] args) {
        // 1. How to store and retrieve negative bytes?
        // 2. Why is it said that byte stream support ASCII from 0 to 255, when byte
        // range is [-128, 127]?
        // 3. Why read() returns int instead of byte or short?
           // no solid reason on short, but we needed a negative value return to denote EOF
        // https://stackoverflow.com/questions/4659659/why-does-inputstreamread-return-an-int-and-not-a-byte
        // https://stackoverflow.com/questions/21062744/why-does-inputstream-read-return-an-int-and-not-a-short
        negativeBytesStorageAndRetrieval();
        
        // 4. How OutputStream writes when parent path doesn't exist?
        outputStreamWhenParentPathIsAbsent();
    }

    private static void negativeBytesStorageAndRetrieval() {
        byte[] b = new byte[1];
        b[0] = -97;
        try {
            /*
             * How FileOutputStream Writes Bytes
             *  Byte Representation:
             *      In Java, the byte type is signed, i.e. it can hold values from -128 to 127.
             *      When you write a byte to a file using FileOutputStream, it writes the raw binary representation of the byte to the file.
             * 
             *  Example: Writing 97:
             *  97 in binary (8 bits) is 01100001.
             *  When written to the file, the exact binary value 01100001 is stored.
             *  
             *  Example: Writing -97:
             *  -97 in binary (8 bits, two's complement) is 10011111.
             *  When written to the file, the exact binary value 10011111 is stored.
             */
            File saveNegative97 = new File("saveNegative97.txt");
            FileOutputStream fos = new FileOutputStream(saveNegative97);
            fos.write(b); //saved as 10011111
            fos.close();
            
            /*
             * How FileInputStream Reads Bytes
             *  Reading as int:
             *      The FileInputStream.read() method reads one byte at a time and returns it as an int in the range 0 to 255.
             *      This is because the read() method treats the byte as unsigned when converting it to an int.
             *  Example: Reading 97:
             *  The binary value 01100001 is read from the file.
             *  It is interpreted as 97 (unsigned), so read() returns 97.
             * 
             *  Example: Reading -97:
             *  The binary value 10011111 is read from the file.
             *  It is interpreted as 159 (unsigned). It's our responsibility to convert it back to byte.
             * 
             * -128 = 10000000, is read back as 128 (unsigned) = 10000000
             * -127 = 10000001, is read back as 129 (unsigned) = 10000001
             * -126 = 10000010, is read back as 130 (unsigned) = 10000010
             * ...
             * -1 = 01111111, is read back as 255 (unsigned) = 01111111
             * 
             * => all non-negative bytes are stored and retrieved as is
             * => all negative bytes (=8bits) are stored in their two's complement form and read back forming a range of 128 to 255
             * => read() returns [0, 255]
             */

            FileInputStream fis = new FileInputStream(saveNegative97);
            int i = 0;
            while ((i = fis.read()) != -1) {
                System.out.println("-97 read as " + i + " => so handle it yourself"); // 159
                System.out.println((byte) i); // your responsibility to convert it back to byte
            }
            fis.close();

            System.out.println("saveNegative97 file deleted: " + saveNegative97.delete()); // delete the file after use
            System.out.println("---------------------------------");
            /*
             * Q - why doesn't read() return byte?
             * Answer:
             * Short answer: EOF distinction.
             * 
             * Long answer:
             * 1. To Distinguish End-of-File (EOF)
             * The read() method needs a way to signal the end of the file. 
             * It does this by returning -1 when there are no more bytes to read.
             * 
             * If read() returned a byte, it would be impossible to distinguish between a
             * valid -1 value (which is a valid byte value [-128 to 127]) and the EOF signal.
             * 
             * By returning an int, the method can use the range 0 to 255 for valid byte values and -1 to indicate EOF.
             * 
             * 2. Unsigned Byte Handling ~= Total byte coverage
             * In Java, byte is signed. When reading in an unsigned manner, 
             * it covers all bytes, and also, not conflicting with -1.
             * 
             * 3. Flexibility for Processing
             * Returning an int provides more flexibility for processing the data. For
             * example, you can directly use the int value in calculations or comparisons
             * without needing to handle signed-to-unsigned conversions manually.
             * eg. compare -97 as 159, but you need to be cognizant => hence risky, yet flexible handling
             
             // Example of "Flexibility for Processing"
             // You can do direct comparisons with other integers (e.g., 0 to 255) without worrying about signed-to-unsigned conversion.
             int byteValue;
             try (FileInputStream fis2 = new FileInputStream("saveNegative97.txt")) {
                 int count255 = 0;
                 // Read each byte and count occurrences of 255
                 while ((byteValue = fis2.read()) != -1) {
                     if (byteValue == 255) { // Direct comparison with unsigned value
                         count255++;
                     }
                 }
                 System.out.println("Number of occurrences of 255: " + count255);
             } catch (IOException e) {
                 e.printStackTrace();
             }
            */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputStreamWhenParentPathIsAbsent() {
        // 4. How OutputStream writes when parent path doesn't exist?
        File testFolder = new File(".\\write_technical_test_folder");
        if (testFolder.exists()) { // returns false if path doesn't exist ~= parent path has faulty directory
                                   // structure eg. filePathIncorrect2 without "\\06_write.txt"
            return;
        }

        testFolder.mkdir();
        System.out.println("test folder created/exists");
        try {
            // String filePath = ".\\write_technical_test_folder\\06_write.txt"; // code
            // works for this
            String filePathIncorrect = ".write_technical_test_folder\\DNE\\06_write.txt";
            String filePathIncorrect2 = ".write_technical_test_folder\\..\\DNE\\06_write.txt";
            OutputStream os = new FileOutputStream(filePathIncorrect); // exception
            // OutputStream os = new FileOutputStream(filePathIncorrect2); // exception
            os.write(97);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("test folder deleted:" + testFolder.delete()); // delete the folder
    }
}

/**
 * Output:
 * -------
 * -97 read as 159 => so handle it yourself
 * -97
 * saveNegative97 file deleted: true
 * ---------------------------------
 * test folder created/exists
 * FileNotFoundException: .write_technical_test_folder\DNE\06_write.txt (The system cannot find the path specified)
 * test folder deleted:true
 * 
 */
