import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CW41_02_Read_Technicalities {
	public static void main(String[] args) {
		// On reading new line using byte stream, we see bytes with ASCII 10 = new line and 13 = carriage
		// every OS has a way to represent a new line. For example, in Windows, it's \r\n (carriage return + new line) = 13 + 10
		// whereas in Linux, it's just \n (new line) = 10

		// This is just for info, we should use utility functions to read line, go to next, etc. which interact with OS's line separator implementation
		try {
			InputStream is = new FileInputStream("02_read.txt"); //ab<new line>cd
			int i = 0;
			while ((i = is.read()) != -1) {
				System.out.println(i); // a,b,13,10,c,d
                // 13->carriage return puts the cursor at the beginning of the line
                // 10->new line moves the cursor to the next line
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
 * 97
 * 98
 * 13
 * 10
 * 99
 * 100
 * 
 */
