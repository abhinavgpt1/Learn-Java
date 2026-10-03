import pack.decisions.*; // imports Max2, Max3
import pack.*; // imports Shapes
import pack.loops.SumN;
import static pack.Shapes.*;
import pack.decisions.Max2; // allowed by compiler, but redundant

class CW25_Packages extends Max2 {
	public static void main(String[] args) {
		Max2 maxOf2 = new Max2();
		System.out.println("Greatest(33, 55): " + maxOf2.getMax2(33, 55));
		line();

		CW25_Packages mainClass = new CW25_Packages();
		System.out.println("Greatest(55, 88): " + mainClass.getMax2(55, 88));
		Shapes.line();

		Max3 maxOf3 = new Max3();
		System.out.println("Greatest(33, 55, 66): " + maxOf3.getMax3(33, 55, 66));
		System.out.println();

		SumN sumOfN = new SumN();
		System.out.println("Sum(7): " + sumOfN.getSum(7));
		System.out.println();

		pack.loops.FactN factN = new pack.loops.FactN(); // using fully qualified name
		int fact = factN.getFact(4);
		System.out.println("Factorial(4): " + fact);

		// A Fully Qualified Name uniquely identifies a single class or package across
		// your entire project, while a Fully Qualified Path points to the physical
		// location of that file on your computer's storage.
		// - com.example.project.Main
		// - /Users/username/IdeaProjects/MyProject/src/com/example/project/Main.java

		// Types of imports:
		// 1. Single Type Import: import pack.decisions.Max2;
		// 2. On Demand Import: import pack.decisions.*;
		// 3. Static Import: import static pack.decisions.Max2;
		// 4. Static On Demand Import: import static pack.decisions.*;
		/**
		 * Definitions:
		 * Single Type Import: A single type import statement imports a single class or
		 * interface from a package. It allows you to use the class or interface without
		 * specifying its fully qualified name.
		 *
		 * On Demand Import: An on-demand import statement imports all the classes and
		 * interfaces from a package. It allows you to use the classes and interfaces
		 * without specifying their fully qualified names.
		 *
		 * Static Import: A static import statement imports static members (fields and
		 * methods) from a class or interface. It allows you to use the static members
		 * without specifying the class or interface name.
		 *
		 * Static On Demand Import: A static on-demand import statement imports all the
		 * static members from a class or interface. It allows you to use the static
		 * members without specifying the class or interface name.
		 */
	}
}
/**
 * Output:
 * Greatest(33, 55): 55
 * =-=-=-=-=-=-=
 * Greatest(55, 88): 88
 * =-=-=-=-=-=-=
 * Greatest(33, 55, 66): 66
 * 
 * Sum(7): 28
 * 
 * Factorial(4): 24
 */