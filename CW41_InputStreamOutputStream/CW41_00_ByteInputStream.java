import java.io.ByteArrayInputStream;

public class CW41_00_ByteInputStream {
	public static void main(String[] args) {
		// ByteArrayInputStream is a class in Java's java.io package that allows you to
		// read bytes from a byte array in memory as if it were an input stream.
		// It is useful for cases where you want to treat a byte array as an input
		// source, similar to how you would read from a file or network stream.
		
		// Summary:
		// Use ByteArrayInputStream for in-memory byte data.
		// Use FileInputStream for reading from files on disk.

		// constructors
		// new ByteArrayInputStream(byte[] b)
		// new ByteArrayInputStream(byte[] b, int offset, int N)
		
		byte[] data = { 65, 66, 67 }; // ASCII for 'A', 'B', 'C'
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int b;
		while ((b = bis.read()) != -1) {
			System.out.print((char) b); // prints: ABC
		}
		// No IOException
		// No FileNotFoundException
	}
}

/**
 * Output:
 * -------
 * 
 */
