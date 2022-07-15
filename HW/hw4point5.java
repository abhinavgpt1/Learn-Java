import java.util.Scanner;
class hw4point5
{

	public static void main(String[] args) 
	{
		int x=100;
		String sx=String.valueOf(x);
		System.out.println(sx);
		//-----------------
		boolean b=true;
		String sb=String.valueOf(b);
		
		System.out.println(sb);
		//-----------------
		String pr="44.55";
		pr=pr+10;
		System.out.println(pr);
		//--------------------
		float fpr=Float.parseFloat(pr);
		fpr=fpr+10;
		System.out.println(fpr);
		//-----------------
		String is="67";
		int iis=Integer.parseInt(is);
		System.out.println(iis);
		
		Scanner cin=new Scanner(System.in);
		System.out.println("Enter ur name:");
		String name = cin.nextLine();
		System.out.println("Enter roll no.:");
		int roll=cin.nextInt();
		System.out.println("Enter percentage:");
		float per=cin.nextFloat();
		System.out.println(name+" "+per+" "+roll);
		
	}
	

}
