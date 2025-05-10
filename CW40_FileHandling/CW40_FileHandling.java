package CW40_FileHandling;
import java.io.File;
import java.io.IOException;
import java.util.Date;

// TIP for not using delete(): we could have created and deleted files in this program, but the problem with delete() is that .class can be executed anywhere,
// so there's a risk that it will delete files unintentinaally (wherever the requirement of package path or default package is met)

public class CW40_FileHandling {
    public static void main(String[] args) {
        String parent_location_path = ".\\CW40_FileHandling\\..\\CW40_FileHandling"; //w.r.t. core-java.git
        File parent_path_file = new File(parent_location_path);
        
        // Ways to init file class
        // Way 1
        File file_pathname = new File("example.txt"); // Using a relative path
        // Way 2
        File file_parent_path_as_str = new File(parent_location_path, "example.txt"); // Using parent and child where parent path is string
        // Way 3        
        File file_parent_path_as_file = new File(parent_path_file, "example.txt"); // Using parent and child where parent path is File
        
        // getPath(), getAbsolutePath(), getCanonicalPath()
        // getPath() returns the path as specified in the constructor, which may be relative or absolute
        // getAbsolutePath() returns the absolute path of the file, which may include symbolic links and relative paths
        // getCanonicalPath() returns path after resolving any symbolic links and relative paths
        
        // NOTE: This particular case will be w.r.t. \core-java.git i.e. current working directory
        // file_pathname output:
        System.out.println("Path: " + file_pathname.getPath()); 
        System.out.println("Absolute Path: " + file_pathname.getAbsolutePath()); //resolves relative path by adding location from drive...but doesn't resolve relative paths
        try {
            System.out.println("Canonical Path: " + file_pathname.getCanonicalPath()); //throws IOException which is checked exception
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        
        // file_parent_path_as_file output:
        System.out.println("Path: " + file_parent_path_as_file.getPath());
        System.out.println("Absolute Path: " + file_parent_path_as_file.getAbsolutePath());
        try {
            System.out.println("Canonical Path: " + file_parent_path_as_file.getCanonicalPath()); //throws IOException which is checked exception
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        // different methods of File class
        File file = file_parent_path_as_str;

        // getName(), exists(), createNewFile() & delete()
        // -----------------------------------------------
        if (file.exists()) {
            System.out.println("File exists: " + file.getName());
            // Uncomment the below lines to delete the file after creating it
            // ------------------ Refer TIP for not using delete()--------------

            // System.out.println("---------Deleting file: " + file.getName() + "---------");
            // boolean isFileDeleted = file.delete();
            // if(isFileDeleted)
            //     System.out.println("File deleted: " + file.getName());
            // else
            //     System.out.println("Eh! :(");
        } else {
            System.out.println("File does not exist: " + file.getName());
            try {
                System.out.println("---------Creating file: " + file.getName() + "---------");
                boolean isFileCreated = file.createNewFile(); //throws IOException which is checked exception
                if(isFileCreated)
                    System.out.println("File created: " + file.getName());
                else
                    System.out.println("File already exists: " + file.getName()); //say it gets created between the time file.exist() run and file.createNewFile() run
                
                // Uncomment the below lines to delete the file after creating it
                // ------------------ Refer TIP for not using delete()--------------

                // System.out.println("---------Deleting file: " + file.getName() + "---------");
                // boolean isFileDeleted = file.delete();
                // if(isFileDeleted)
                //     System.out.println("File deleted: " + file.getName());
                // else
                //     System.out.println("Eh! :(");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        System.out.println("getName() for directory returns name: " + parent_path_file.getName());

        System.out.println();

        // isFile() & isDirectory()
        // ------------------------
        System.out.println("filePath: isFile: " + file.isFile()); //true
        System.out.println("filePath: isDirectory: " + file.isDirectory());//false
        System.out.println("Directory: isDirectory: " + parent_path_file.isDirectory());//true
        System.out.println("Directory: isFile: " + parent_path_file.isFile());//false

        System.out.println();

        // list() & listFiles() & mkdir()
        // ------------------------------
        String[] files = parent_path_file.list(); //returns empty array if directory is empty
        System.out.println("list(): Files found in directory: \\" + parent_path_file.getName() + " : " + files.length); 
        //returns 3 since .class, .java and example.text are in existence for Output run 1
        //returns 4 since .class, .java, example.text and emptyDirectory are in existence for Output run 2
        
        String[] files_Null = file.list(); //returns null if File is not a directory
        System.out.println("list(): Files found in file: " + file.getName() + " : " + files_Null);

        System.out.println();

        File[] files_list = parent_path_file.listFiles(); //returns empty array if directory is empty
        System.out.println("listFiles(): Files found in directory: \\" + parent_path_file.getName() + " : " + files_list.length);

        File[] files_Null_again = file.listFiles(); //returns null if File is not a directory
        System.out.println("listFiles(): Files found in file: " + file.getName() + " : " + files_Null_again);

        String emptyDirectoryPath = parent_location_path + "\\emptyDirectory"; //w.r.t. core-java.git
        File empty_directory_file = new File(emptyDirectoryPath);
        if (empty_directory_file.mkdir()) { //creates a directory if it does not exist
            System.out.println("Directory created in \\" + parent_path_file.getName() + ": " + empty_directory_file.getName());
        } else {
            System.out.println("Directory already exists or failed to create: " + empty_directory_file.getName());
            // Uncomment the below lines to delete the file after creating it
            // ------------------ Refer TIP for not using delete()--------------
            
            // System.out.println("---------Deleting file: " + empty_directory_file.getName() + "---------");
            // boolean isDirDeleted = empty_directory_file.delete();
            // if(isDirDeleted)
            //     System.out.println("Directory deleted: " + empty_directory_file.getName());
            // else
            //     System.out.println("Eh! :(");
        }

        // mkdir for file's File object
        if(file.mkdir()){
            System.out.println("Oh no, mkdir() created when path points to file: " + file.getName());
        } else {
            try {
                System.out.println("mkdir() doesn't work for file's File object: " + file.getCanonicalPath());
            } catch (IOException e) {
                System.out.println("mkdir(): Error getting canonical path: " + e.getMessage());
            }
        }

        System.out.println();

        // miscellaneous methods
        // ---------------------
        System.out.println("getParent() path of file w.r.t. cwd: " + file_parent_path_as_file.getParent());
        System.out.println("getParentFile() object: " + file_parent_path_as_file.getParentFile()); //toString of File returns getPath()
            // better use getCanonicalPath() and getCanonicalFile, since above functions work on the path you provide -> it doesn't check actual path in directory
        // file_parent_path_as_str.renameTo(some file object ref)
        System.out.println("null for relative path File object on getParent(): " + file_parent_path_as_file.getParentFile().getParentFile().getParentFile().getParentFile().getParent());
        System.out.println("lastModified() returns long value in milliseconds: " + new Date(file_parent_path_as_str.lastModified()));
        System.out.println("length() for file (in bytes): " + file_parent_path_as_str.length()); 
            //bytes -> returns 5 if "Hello" is added to the file
        System.out.println("length() for directory doesn't make sense. Dependent on OS though: " + parent_path_file.length()); //bytes -> returns 0 for windows, might return metadata size in unix/linux
        // file_parent_path_as_str.canExecute()
        // file_parent_path_as_str.canRead()
        // file_parent_path_as_str.canWrite()
        // file_parent_path_as_str.getAbsoluteFile(); // counterpart of getAbsolutePath()
        // file_parent_path_as_str.getCanonicalFile(); // counterpart of getCanonicalPath()
        // file_parent_path_as_str.setExecutable(true); //set executable permission
        // file_parent_path_as_str.setReadable(true); //set readable permission
        // file_parent_path_as_str.setWritable(true); //set writable permission
        file_parent_path_as_str.setReadOnly();
        file_parent_path_as_str.setReadable(false, false); //set readable permission for owner and group

        System.out.println();

        // listRoots()
        // -----------
        File [] rootDrives = File.listRoots();
        System.out.println("Root drives found in system: " + rootDrives.length);
        for (File rootDrive : rootDrives) {
            System.out.println(rootDrive); // "C:\", and so on
        }
    }
}

/**
 * Execution commands:
 * ------------------
 * core-java.git> javac .\CW40_FileHandling\CW40_FileHandling.java
 * core-java.git> java CW40_FileHandling.CW40_FileHandling
 * 
 * Output (1st run):
 * -----------------
 * Path: example.txt
 * Absolute Path: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\example.txt
 * Canonical Path: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\example.txt
 * 
 * Path: .\CW40_FileHandling\..\CW40_FileHandling\example.txt
 * Absolute Path: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\.\CW40_FileHandling\..\CW40_FileHandling\example.txt
 * Canonical Path: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\CW40_FileHandling\example.txt
 * 
 * File does not exist: example.txt
 * ---------Creating file: example.txt---------
 * File created: example.txt
 * getName() for directory returns name: CW40_FileHandling
 * 
 * filePath: isFile: true
 * filePath: isDirectory: false
 * Directory: isDirectory: true
 * Directory: isFile: false
 * 
 * list(): Files found in directory: \CW40_FileHandling : 3
 * list(): Files found in file: example.txt : null
 * 
 * listFiles(): Files found in directory: \CW40_FileHandling : 3
 * listFiles(): Files found in file: example.txt : null
 * Directory created in \CW40_FileHandling: emptyDirectory
 * mkdir() doesn't work for file's File object: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\CW40_FileHandling\example.txt
 * 
 * getParent() path of file w.r.t. cwd: .\CW40_FileHandling\..\CW40_FileHandling
 * getParentFile() object: .\CW40_FileHandling\..\CW40_FileHandling
 * null for relative path File object on getParent(): null
 * lastModified() returns long value in milliseconds: Thu Apr 10 15:19:17 IST 2025
 * length() for file (in bytes): 0
 * length() for directory doesn't make sense. Dependent on OS though: 0
 * 
 * Root drives found in system: 3
 * C:\
 * F:\
 * W:\
 * 
 * 
 * Output (2st run - not uncommenting delete lines):
 * -------------------------------------------------
 * Path: example.txt
 * Absolute Path: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\example.txt
 * Canonical Path: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\example.txt
 * 
 * Path: .\CW40_FileHandling\..\CW40_FileHandling\example.txt
 * Absolute Path: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\.\CW40_FileHandling\..\CW40_FileHandling\example.txt
 * Canonical Path: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\CW40_FileHandling\example.txt
 * 
 * File exists: example.txt
 * getName() for directory returns name: CW40_FileHandling
 * 
 * filePath: isFile: true
 * filePath: isDirectory: false
 * Directory: isDirectory: true
 * Directory: isFile: false
 * 
 * list(): Files found in directory: \CW40_FileHandling : 4
 * list(): Files found in file: example.txt : null
 * 
 * listFiles(): Files found in directory: \CW40_FileHandling : 4
 * listFiles(): Files found in file: example.txt : null
 * Directory already exists or failed to create: emptyDirectory
 * mkdir() doesn't work for file's File object: W:\BCE (C,Cpp,Java,WebDev)\Java\core-java.git\CW40_FileHandling\example.txt
 * 
 * getParent() path of file w.r.t. cwd: .\CW40_FileHandling\..\CW40_FileHandling
 * getParentFile() object: .\CW40_FileHandling\..\CW40_FileHandling
 * null for relative path File object on getParent(): null
 * lastModified() returns long value in milliseconds: Thu Apr 10 15:19:17 IST 2025
 * length() for file (in bytes): 0
 * length() for directory doesn't make sense. Dependent on OS though: 0
 * 
 * Root drives found in system: 3
 * C:\
 * F:\
 * W:\
 * 
 */
