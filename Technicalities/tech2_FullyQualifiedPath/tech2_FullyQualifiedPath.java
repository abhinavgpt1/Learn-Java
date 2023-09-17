package Technicalities.tech2_FullyQualifiedPath;

public class tech2_FullyQualifiedPath {
    public static void main(String[] args) {
        // A.fun();//error, as these aren't imported, only their static functions are
        // fun();//ambiguity due to import of B.fun()
        // B.fun();//error, as these aren't imported, only their static functions are
        // this makes above imports useless
        Technicalities.tech2_FullyQualifiedPath.pack.A.fun();
        Technicalities.tech2_FullyQualifiedPath.pack.B.fun();

        // Suggestion: can import one and use fully qualified path for the other
    }
    /**
     * Executing commands
     * ------------------
     * CW core java>javac .\Technicalities\tech2_FullyQualifiedPath\tech2_FullyQualifiedPath.java
     * CW core java>java Technicalities.tech2_FullyQualifiedPath.tech2_FullyQualifiedPath
     *
     * Output:
     * -------
     * inside A
     * inside B
     *
     * Warning: Javadoc dangling comment comes because javadoc comment is supposed
     * to be on top of class declaration, a method declaration, or a field
     * declaration
     * https://stackoverflow.com/questions/43375177/warning-dangling-javadoc-comment
     */
}
