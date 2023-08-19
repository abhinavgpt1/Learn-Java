package Technicalities.packageAndMemberAccessCases.pkgPublic;
import Technicalities.packageAndMemberAccessCases.pkgPublic.pack.PublicClass;

public class AccessOutside extends PublicClass {
	public static void main(String[] args) {
		AccessOutside ao = new AccessOutside();
		PublicClass publicClass = new PublicClass();
		
		// test public member
		publicClass.instancePublic();
		staticPublic();

		// test protected member
		// publicClass.instanceProtected(); // .\pkgPublic\AccessOutside.java:14: error: instanceProtected() has protected access in PublicClass
		// Reason: this is just like public access, ref: https://stackoverflow.com/questions/20404977/accessing-a-protected-variable-from-a-subclass-outside-package
		ao.instanceProtected();
		staticProtected();
		
		// test PLA access members
		// publicClass.instancePLA(); // .\pkgPublic\AccessOutside.java:20: error: instancePLA() is not public in PublicClass; cannot be accessed from outside package
		// staticPLA(); // .\pkgPublic\AccessOutside.java:21: error: cannot find symbol
		
		// test private member
		// publicClass.instancePrivate(); // .\pkgPublic\AccessOutside.java:24: error: instancePrivate() has private access in PublicClass

		// Result: 
		// ------------------------------
		// Only public members are accessed via object creation. 
		// Only protected members can be accessed after extending
	}
}
/**
 * Execution Commands:
 * -------------------
 * CW core java> javac .\Technicalities\packageAndMemberAccessCases\pkgPublic\AccessOutside.java
 * CW core java> java Technicalities.packageAndMemberAccessCases.pkgPublic.AccessOutside
 * 
 * Output:
 * -------
 * inside public member
 * inside static public member
 * inside protected member
 * inside static protected member
 *
 * Warning: Javadoc dangling comment comes because javadoc comment is supposed to be on top of class declaration, a method declaration, or a field declaration
 * https://stackoverflow.com/questions/43375177/warning-dangling-javadoc-comment
 */
