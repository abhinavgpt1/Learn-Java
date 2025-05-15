import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CW41_01_Read_Variations {
	public static void main(String[] args) {
		// read() => returns the byte read
		exampleRead();

		// read(byte[] b) => returns the number of bytes read. Reads into b.
		exampleReadByteArray();
		
		// read(byte[] b, int off, int len) => returns len (i.e. bytes read). Reads into b from index off, and reads len bytes.
		exampleReadByteArrayOffsetLength();
		
		// readAllBytes() => returns the input stream as byte array at once. You don't need to loop through the stream.
		exampleReadAllBytes();
		
		// readNBytes(int len) => return first n bytes of the input stream as byte array, otherwise max. bytes present if len > capacity of is
		exampleReadNBytes();
		
		// readNBytes(byte[] b, int off, int len) => same as read(byte[] b, int off, int len), it's just that here, byte[] is returned for what's read
		exampleReadNBytesByteArrayOffsetLength();
	}

	private static void exampleRead() {
		// returns the byte read
		System.out.println("---------------Output: read()---------------");
		try {
			InputStream is = new FileInputStream("01_read.txt"); // throws FNFEx
			int byteAsInt = 0; //[0, 255] 
			// it's our job to extract correct byte -> by typecasting (byte) since byte range in [-128, 127]
			// it's on us to store and retrieve ASCII from 0 to 255 (if we want) -> but be aware what's happening BTS
			while ((byteAsInt = is.read()) != -1) {
				System.out.println(byteAsInt); // a,b,c,d,e is what we see in .txt as human readable form, but for byteStream it's merely a byte
			}
			is.close(); // throws IOEx
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private static void exampleReadByteArray() {
		// returns the number of bytes read, and not the byte itself
		System.out.println("---------------Output: read(byte[] b)---------------");
		System.out.println("Example 1: when byte array length is less than InputStream capacity");
		byte[] b = new byte[3];
		b[0] = 48; // 0
		b[1] = 49; // 1
		b[2] = 50; // 2
		try {
			InputStream is = new FileInputStream("01_read.txt"); // contains abcde
			int bytesRead = is.read(b); // is had capacity of 5 bytes. But we read 3 bytes due to b.length
			for (int i = 0; i < b.length; i++) {
				System.out.println(b[i]); // 0->a, 1->b, 2->c
			}
			System.out.println("num bytes read: " + bytesRead); // = min(capacity of is, b.length) = min(5,3)
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Example 2: when byte array length is more than InputStream capacity");
		byte [] b2 = new byte[7];
		b2[0] = 48; // 0
		b2[1] = 49; // 1
		b2[2] = 50; // 2
		b2[3] = 51; // 3
		b2[4] = 52; // 4
		b2[5] = 53; // 5
		b2[6] = 54; // 6
		try {
			InputStream is = new FileInputStream("01_read.txt"); // contains abcde
			int bytesRead = is.read(b2); // is has capacity of 5 bytes, we read that much and rest of b2 is unaffected
			for (int i = 0; i < b2.length; i++) {
				System.out.println(b2[i]); // 0->a, 1->b, 2->c, 3->d, 4->e, 5 (unchanged), 6 (unchanged)
			}
			System.out.println("num bytes read: " + bytesRead); // = min(capacity of is, b.length) = min(5,7)
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void exampleReadByteArrayOffsetLength() {
		// returns len (i.e. bytes read)
		System.out.println("---------------Output: read(byte[] b, int off, int len)---------------");
		byte [] b = new byte[7];
		b[0] = 48; // 0
		b[1] = 49; // 1
		b[2] = 50; // 2
		b[3] = 51; // 3
		b[4] = 52; // 4
		b[5] = 53; // 5
		b[6] = 54; // 6
		try {
			InputStream is = new FileInputStream("01_read.txt"); // contains abcde
			int bytesRead = is.read(b, 4, 3); // from index 4, insert 3 bytes from is
			for (int i = 0; i < b.length; i++) {
				System.out.println(b[i]); // 0, 1, 2, 2, 4->a, 5->b, 6->c
			}
			System.out.println("num bytes read: " + bytesRead); // = len specified to read from input stream (is)
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// if number of bytes to be read into b exceeds the length of b, then it throws IndexOutOfBoundsException
		// Exception in thread "main" java.lang.IndexOutOfBoundsException
		// eg. given, b.length = 7, is.read(b, 4, 5)
		// eg. given, b.length = 7, is.read(b, 4, 100)
		
		// to formulate: [off + len <= b.length] should be obeyed
		System.out.println("to formulate: [off + len <= b.length] should be obeyed");
	}

	private static void exampleReadAllBytes() {
		// returns the input stream as byte array at once. You don't need to loop through the stream.
		System.out.println("---------------Output: readAllBytes()---------------");
		// readAllBytes() is a blocking call. It will block until all bytes are read or an error occurs.
		// readAllBytes() is not recommended for large files as it may consume a lot of memory.
		// readAllBytes() is available in Java 9 and later versions.
		try {
			InputStream is = new FileInputStream("01_read.txt"); // contains abcde
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
		// alternatives to readAllBytes() are:
		// 1. BufferedInputStream
		// 2. Files.newInputStream -> Java NIO
		// 3. Files.lines() -> Java NIO

		// Java NIO (New Input/Output) is a collection of Java APIs introduced in Java 1.4 to provide efficient and scalable I/O operations. 
		// It is designed to handle large-scale data processing and high-performance applications, such as servers or file processing systems
	}

	private static void exampleReadNBytes() {
		// returns first n bytes of the input stream as byte array, otherwise max. bytes present if len > capacity of is
		System.out.println("---------------Output: readNBytes(int len)---------------");
		try {
			InputStream is = new FileInputStream("01_read.txt"); // contains abcde
			byte[] bytesRead = is.readNBytes(3); // reads 3 bytes from is
			// byte[] bytesRead = is.readNBytes(100); // reads all bytes present in is, if len > capacity of is
			for (int i = 0; i < bytesRead.length; i++) {
				System.out.println(bytesRead[i]); // a,b,c
			}
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void exampleReadNBytesByteArrayOffsetLength() {
		// same as read(byte[] b, int off, int len), it's just that, here byte[] is returned for what's read
		System.out.println("---------------Output: readNBytes(byte[] b, int off, int len)---------------");
		byte [] b = new byte[7];
		b[0] = 48; // 0
		b[1] = 49; // 1
		b[2] = 50; // 2
		b[3] = 51; // 3
		b[4] = 52; // 4
		b[5] = 53; // 5
		b[6] = 54; // 6
		try {
			InputStream is = new FileInputStream("01_read.txt"); // contains abcde
			int bytesRead = is.readNBytes(b, 4, 3); // from index 4, insert 3 bytes from is
			for (int i = 0; i < b.length; i++) {
				System.out.println(b[i]); // 0, 1, 2, 2, 4->a, 5->b, 6->c
			}
			System.out.println("num bytes read: " + bytesRead); // = len specified to read from input stream (is)
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
 * ---------------Output: read()---------------
 * 97
 * 98
 * 99
 * 100
 * 101
 * ---------------Output: read(byte[] b)---------------
 * Example 1: when byte array length is less than InputStream capacity
 * 97
 * 98
 * 99
 * num bytes read: 3
 * 
 * Example 2: when byte array length is more than InputStream capacity
 * 97
 * 98
 * 99
 * 100
 * 101
 * 53
 * 54
 * num bytes read: 5
 * ---------------Output: read(byte[] b, int off, int len)---------------
 * 48
 * 49
 * 50
 * 51
 * 97
 * 98
 * 99
 * num bytes read: 3
 * to formulate: [off + len <= b.length] should be obeyed
 * ---------------Output: readAllBytes()---------------
 * 97
 * 98
 * 99
 * 100
 * 101
 * ---------------Output: readNBytes(int len)---------------
 * 97
 * 98
 * 99
 * ---------------Output: readNBytes(byte[] b, int off, int len)---------------
 * 48
 * 49
 * 50
 * 51
 * 97
 * 98
 * 99
 * num bytes read: 3
 * 
 */
