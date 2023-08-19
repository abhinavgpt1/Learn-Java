package Technicalities.packageAndMemberAccessCases.noPkg.pack;

public class AccessInside extends PublicClass { //.\noPkg\pack\AccessInside.java:3: error: cannot access PublicClass
	public static void main(String[] args) {
		// no point in testing as pkg is undetectable - default package can't be used inside package
		// same results for PLA class
	}
}
/**
 * Execution Commands:
 * -------------------
 * CW core java> javac .\Technicalities\packageAndMemberAccessCases\noPkg\pack\AccessInside.java
 * 
 * Output:
 * -------
 * Errors
 *
 * Warning: Javadoc dangling comment comes because javadoc comment is supposed to be on top of class declaration, a method declaration, or a field declaration
 * https://stackoverflow.com/questions/43375177/warning-dangling-javadoc-comment
 */