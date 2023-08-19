package Technicalities.packageAndMemberAccessCases.pkgPLA;
import Technicalities.packageAndMemberAccessCases.pkgPLA.pack.PLAClass; //.\pkgPLA\AccessOutside.java:2: error: PLAClass is not public in pkgPLA.pack; cannot be accessed from outside package

public class AccessOutside extends PLAClass { //.\pkgPLA\AccessOutside.java:4: error: PLAClass is not public in pkgPLA.pack; cannot be accessed from outside package
	public static void main(String[] args) {
		AccessOutside ao = new AccessOutside(); 
		PLAClass plaClass = new PLAClass(); // error: PLAClass is not public in pkgPLA.pack; cannot be accessed from outside package
		
		// no point of testing as package is inaccessible

		// // test public member
		// plaClass.instancePublic();
		// // staticPublic(); //.\pkgPLA\AccessOutside.java:12: error: cannot find symbol

		// // test protected member
		// plaClass.instanceProtected(); 
		// ao.instanceProtected(); // .\pkgPLA\AccessOutside.java:17: error: cannot find symbol
		// staticProtected();
		
		// // test PLA access members
		// plaClass.instancePLA(); //error
		// staticPLA(); //error
		
		// // test private member
		// plaClass.instancePrivate(); //error

		// Result: 
		// ------------------------------
		// PLA class can't be accessed from outside, not even after derivation, so no point of testing here
	}
}
/**
 * Execution Commands:
 * -------------------
 * CW core java> javac .\Technicalities\packageAndMemberAccessCases\pkgPLA\AccessOutside.java
 * 
 * Output:
 * -------
 * Errors of importing inaccessible type
 *
 * Warning: Javadoc dangling comment comes because javadoc comment is supposed to be on top of class declaration, a method declaration, or a field declaration
 * https://stackoverflow.com/questions/43375177/warning-dangling-javadoc-comment
 */