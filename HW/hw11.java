// Get filename and parent folder name from filepath input as string
import java.util.Scanner;

public class hw11 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.print("Input file path: ");
		String filepath = cin.nextLine();
		cin.close();
		int bkslshIndexBeforeFileName = filepath.lastIndexOf('\\');
		// lastIndexOf operates from right to left
		// hence, adding (bkslshIndexBeforeFileName - 1) would start search from there towards [0]
		int bkslshIndexBeforeParentDir = filepath.lastIndexOf('\\', bkslshIndexBeforeFileName - 1);

		// Result
		String filename = filepath.substring(bkslshIndexBeforeFileName + 1);
		// end index is non-inclusive
		String parentDirectoryName = filepath.substring(bkslshIndexBeforeParentDir + 1, bkslshIndexBeforeFileName);
		System.out.println("file = " + filename);
		System.out.println("Parent directory name = " + parentDirectoryName);
	}
	/**
	 * Output:
	 * -------
	 * Input file path: Q:\Users\randomUser\archive\myphoto.jpg
	 * file = myphoto.jpg
	 * Parent directory name = archive
	 */
}
