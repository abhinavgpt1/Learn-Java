import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

// Make sure there exist no .class in HW folder (to match output) -> else execute "del /S *.class"
// This program can be extnded to find different kind of extensions by maintaining a Set over every file found.
public class HW14_GetFolderStructureFullInfo {
	public static void main(String[] args) throws IOException {
		// Can take input from user for folder path
		// Scanner cin = new Scanner(System.in);
		// System.out.print("Input canonical folder path: ");
		// String folderPath = cin.nextLine();
		// cin.close();
		File root_folder = new File(".");
		// File root_folder = new File("..\\Technicalities"); // example 2 - there are a lot of getName() output with same name eg. pack
		if (!root_folder.isDirectory()) {
			System.out.println("Path provided is not of directory.");
			return;
		}
		System.out.println("Directory structure for: " + root_folder.getCanonicalPath());
		System.out.println("-------------------------------------------------------------");

		// BFS to get info of all files and folders in the directory
		Queue<File> pending = new LinkedList<>();
		pending.add(root_folder);

		while (!pending.isEmpty()) {
			File folder = pending.remove();

			System.out.println("For " + folder.getName() + ":"); // suggestion for example 2: for proper directory path, maintain parent path in Queue<Pair<File, String>>
			int fileCount = 0, folderCount = 0;
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					pending.add(file);
					folderCount++;
				} else {
					// System.out.println("\t" + file.getName()); // considers HW14.class as file if File root_folder = new File(".");
					fileCount++;
				}
			}
			System.out.println("\t files: " + fileCount);
			System.out.println("\t folders: " + folderCount);
		}
	}
}

/**
 * Output:
 * -------
 * Directory structure for: V:\BCE Coding\Java\learn-java.git\HW
 * -------------------------------------------------------------
 * For .:
 * 	 files: 16
 * 	 folders: 4
 * For HW13_CountFilesWithDifferentExtension:
 * 	 files: 4
 * 	 folders: 1
 * For HW15_CopyAndCreateImage:
 * 	 files: 2
 * 	 folders: 0
 * For HW16_CutAndMergeRingtones:
 * 	 files: 4
 * 	 folders: 0
 * For HW17_DataInputStreamDataOutputStream:
 * 	 files: 2
 * 	 folders: 0
 * For Dir1:
 * 	 files: 1
 * 	 folders: 0
 */