package Technicalities.tech0_packageAndMemberAccessCases.noPkg;
import Technicalities.tech0_packageAndMemberAccessCases.noPkg.pack.*;

public class AccessOutside extends PublicClass{ //.\noPkg\AccessOutside.java:4: error: cannot access PublicClass
	public static void main(String[] args) {
		AccessOutside ao = new AccessOutside();
		PublicClass publicClass = new PublicClass();
		// bad source file: .\noPkg\pack\PublicClass.java
		// file does not contain class noPkg.pack.PublicClass
		// Please remove or make sure it appears in the correct subdirectory of the sourcepath.
		
		// no point in testing as package in undetectable
		// same will be the result for PLA class
		
		// Result:
		// -------
		// default package can't be accessed from outside
	}
}
/**
 * Execution Commands:
 * -------------------
 * CW core java> javac .\Technicalities\tech0_packageAndMemberAccessCases\noPkg\AccessOutside.java
 * 
 * Output:
 * -------
 * Errors
 *
 * Warning: Javadoc dangling comment comes because javadoc comment is supposed to be on top of class declaration, a method declaration, or a field declaration
 * https://stackoverflow.com/questions/43375177/warning-dangling-javadoc-comment
 */