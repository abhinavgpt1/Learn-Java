package Technicalities.tech0_packageAndMemberAccessCases.pkgPLA.pack;

public class AccessInside extends PLAClass {
	public static void main(String[] args) {
		// no need of import when there is PLA
		AccessInside ao = new AccessInside();
		PLAClass plaClass = new PLAClass();

		// test public member
		plaClass.instancePublic();
		staticPublic();
		
		// test protected member
		plaClass.instanceProtected();
		ao.instanceProtected();
		staticProtected();
		
		// test PLA access members
		plaClass.instancePLA();
		staticPLA();
		ao.instancePLA();
		
		// test private member
		// plaClass.instancePrivate(); // .\pkgPLA\pack\AccessInside.java:24: error: instancePrivate() has private access in PLAClass

		// Result: 
		// ------------------------------
		// All members except private are accessible inside package including when extending
	}
}
/**
 * Execution Commands:
 * -------------------
 * CW core java> javac .\Technicalities\tech0_packageAndMemberAccessCases\pkgPLA\pack\AccessInside.java
 * CW core java> java Technicalities.tech0_packageAndMemberAccessCases.pkgPLA.pack.AccessInside
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