package HW.HW13_CountFilesWithDifferentExtension;
import java.io.File;
import java.util.HashMap;

// TIP for not using delete(): we could have created and deleted files in this program, but the problem with delete() is that .class can be executed anywhere,
// so there's a risk that it will delete files unintentinaally (wherever the requirement of package path or default package is met)

public class HW13_CountFilesWithDifferentExtension {
	// To do this recursively i.e. to count files in Dir1, use DFS approach.
	public static void main(String[] args) {
		HashMap<String, Integer> fileExtensionMap = new HashMap<String, Integer>();

		File file = new File(".\\HW\\HW13_CountFilesWithDifferentExtension"); //w.r.t. core-java.git i.e. path in terminal
		if(!file.isDirectory()){
			System.out.println("Path provided is not of directory.");
			return;
		}
		File[] filesInDirectory = file.listFiles(); //counts directory too
		for(File f: filesInDirectory) {
			if(f.isFile()) {
				String fileName = f.getName(); //get name of file
				String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
				fileExtensionMap.merge(extension, 1, Integer::sum); //if extension is not present in map, add it with value 1, else increment the value by 1
			}
		}
		System.out.println("Extension map:");
		fileExtensionMap.forEach((k, v) -> System.out.println(k + " : " + v));
	}
	/**
	 * Execution command:
	 * ------------------
	 * javac .\HW\HW13_CountFilesWithDifferentExtension\HW13_CountFilesWithDifferentExtension.java
	 * java HW.HW13_CountFilesWithDifferentExtension.HW13_CountFilesWithDifferentExtension
	 * 
	 * Output:
	 * -------
	 * Extension map:
     * txt : 2
     * java : 1
     * lld : 1
     * class : 1
	 */
}
