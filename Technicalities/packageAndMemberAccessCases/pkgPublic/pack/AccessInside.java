package Technicalities.packageAndMemberAccessCases.pkgPublic.pack;

public class AccessInside extends PublicClass {
	public static void main(String[] args) {
		// no need of import when there is PLA
		AccessInside ao = new AccessInside();
		PublicClass publicClass = new PublicClass();

		// test public member
		publicClass.instancePublic();
		staticPublic();
		
		// test protected member
		publicClass.instanceProtected();
		ao.instanceProtected();
		staticProtected();
		
		// test PLA access members
		publicClass.instancePLA();
		staticPLA();
		ao.instancePLA();
		
		// test private member
		// publicClass.instancePrivate(); // .\pkgPublic\pack\AccessInside.java:24: error: instancePrivate() has private access in PublicClass

		// Result: 
		// ------------------------------
		// All members except private are accessible inside package including when extending
	}
}
/**
 * Execution Commands:
 * -------------------
 * CW core java> javac .\Technicalities\packageAndMemberAccessCases\pkgPublic\pack\AccessInside.java
 * CW core java> java Technicalities.packageAndMemberAccessCases.pkgPublic.pack.AccessInside
 * 
 * Output:
 * -------
 * inside public member
 * inside static public member
 * inside protected member
 * inside protected member
 * inside static protected member
 * inside PLA member
 * inside static PLA member
 * inside PLA member
 *
 * Warning: Javadoc dangling comment comes because javadoc comment is supposed to be on top of class declaration, a method declaration, or a field declaration
 * https://stackoverflow.com/questions/43375177/warning-dangling-javadoc-comment
 */