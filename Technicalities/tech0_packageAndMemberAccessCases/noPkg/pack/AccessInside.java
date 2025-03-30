public class AccessInside {
	public static void main(String[] args) {
		// PublicClass is undetectable if you compile driver class as |> javac .\Tech..\..\AccessInside
		//	compilation works if done like |> javac ".\Tech..\..\AccessInside"

		// If these conditions are satisfied, then PublicClass can be accessed
		// 1. both classes need to be under default package
		// 2. Driver class must be executed from its parent location in terminal.
		// If not, then compile as javac ".\Technicalities\tech0_packageAndMemberAccessCases\noPkg\pack\AccessInside.java"
		PublicClass publicClass = new PublicClass();
		System.out.println(publicClass.pub);
		System.out.println(publicClass.pro);
		System.out.println(publicClass.pla);
		// System.out.println(publicClass.priv); //not visible

		// same results for PLA class
	}
}
/**
 * Classes Formed:
 * 	-> AccessInside.class
 * 	-> PublicClass.class
 *
 * Execution Commands:
 * -------------------
 * CW core java\Technicalities\tech0_packageAndMemberAccessCases\noPkg\pack> javac AccessInside.java
 * CW core java\Technicalities\tech0_packageAndMemberAccessCases\noPkg\pack> java AccessInside
 * 
 * Output:
 * -------
 * 9
 * 91
 * 911
 *
 * Warning: Javadoc dangling comment comes because javadoc comment is supposed to be on top of class declaration, a method declaration, or a field declaration
 * https://stackoverflow.com/questions/43375177/warning-dangling-javadoc-comment
 */