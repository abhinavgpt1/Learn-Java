import java.util.Scanner;

public class hw11 {

	public static void main(String[] args) {
		Scanner cin =new Scanner(System.in);
		System.out.println("Input file path");
		String s=cin.nextLine();
		int last=s.lastIndexOf('\\');
				
		String file=s.substring(last+1);
		
		int secondLast=s.lastIndexOf('\\',last-1);
		String folder=s.substring(secondLast+1,last);
		System.out.println("file="+file);
		System.out.println("folder="+folder);
		
	}

}
