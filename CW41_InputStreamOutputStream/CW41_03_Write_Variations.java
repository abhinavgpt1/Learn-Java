import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CW41_03_Write_Variations {
	public static void main(String[] args) {
		// write(int b)
		// write(byte[] b)
		// write(byte[] b, int off, int len)
		exampleWriteByte();
		exampleWriteByteArray();
		exampleWriteByteArrayOffsetLength();

	}

	private static void exampleWriteByte() {
		// write(int b) => writes the byte to the output stream
		System.out.println("---------------Output: write(int b)---------------");
		try {
			OutputStream os = new FileOutputStream("03_write.txt"); // creates if not exists, else overwrites it if exists
			os.write(97); // a
			os.write(98); // b
			os.write(99); // c
			os.close();
			readFileUsingStream("03_write.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void exampleWriteByteArray() {
		byte[] b = new byte[5];
		b[0] = 97;
		b[1] = 98;
		b[2] = 99;
		b[3] = 100;
		b[4] = 101;
		// write(byte[] b) => writes the byte array to the output stream
		System.out.println("---------------Output: write(byte[] b)---------------");
		try {
			OutputStream os = new FileOutputStream("04_write.txt", true); // creates if not exist, and appends in the file if exists
			os.write(b); //writes all bytes in b to the file
			os.write((int)'\n'); //writes a new line
			os.write(b);
			os.close();
			// Output: 
			// abcde
			// abcde
			readFileUsingStream("04_write.txt"); //97,98,99,100,101, 10 , 97,98,99,100,101
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void exampleWriteByteArrayOffsetLength() {
		byte[] b = new byte[5];
		b[0] = 97;
		b[1] = 98;
		b[2] = 99;
		b[3] = 100;
		b[4] = 101;
		// write(byte[] b, int off, int len) => writes the byte array to the output stream from the offset=off for the length=len
		System.out.println("---------------Output: write(byte[] b, int off, int len)---------------");
		try {
			OutputStream os = new FileOutputStream("05_write.txt", true); // creates if not exist, and appends in the file if exists
			try {
				os.write(b, 2, 100); // exception, len exceeds the array size
			} catch (IndexOutOfBoundsException e) {
				System.out.println("os.write(b, 2, 100): IndexOutOfBoundsException: " + e.getMessage());
			}
			System.out.println("os.write(b, 2, 2):");
			os.write(b, 2, 2); // starts from index 2 and writes 2 bytes ahead (including index 2) //cd
			os.close();
			readFileUsingStream("05_write.txt");
			// if number of bytes to be written exceeds the length of b, then it throws IndexOutOfBoundsException
			// Exception in thread "main" java.lang.IndexOutOfBoundsException
			// eg. given, b.length = 5, os.write(b, 2, 3) works
			// eg. given, b.length = 5, os.write(b, 2, 100) throws IndexOutOfBoundsException
			
			// to formulate: [off + len <= b.length] should be obeyed
			System.out.println("to formulate: [off + len <= b.length] should be obeyed");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readFileUsingStream(String fileName) {
		try {
			InputStream is = new FileInputStream(fileName);
			byte[] bytesReadAtOnce = is.readAllBytes();
			for (int i = 0; i < bytesReadAtOnce.length; i++) {
				System.out.println(bytesReadAtOnce[i]); // a,b,c,d,e
			}
			is.close();
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
 * ---------------Output: write(int b)---------------
 * 97
 * 98
 * 99
 * ---------------Output: write(byte[] b)---------------
 * 97
 * 98
 * 99
 * 100
 * 101
 * 10 <- notice new line, and no carriage return
 * 97
 * 98
 * 99
 * 100
 * 101
 * ---------------Output: write(byte[] b, int off, int len)---------------
 * os.write(b, 2, 100): IndexOutOfBoundsException: null
 * os.write(b, 2, 2):
 * 99
 * 100
 * to formulate: [off + len <= b.length] should be obeyed
 */
