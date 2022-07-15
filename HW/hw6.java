import java.util.Scanner;
class Matrix
{
	public static void main(String[] args) 
	{
		Scanner cin = new Scanner(System.in);
		int a[][]=formMat(cin);
		int b[][]=formMat(cin);
		int [][]add=add(a,b);
		showMat(add);		
	}
	static int [][]formMat(Scanner cin) 
	{
		int m,n;
		System.out.println("Enter order of matrix:");
		m=cin.nextInt();
		n=cin.nextInt();
		int[][] a=new int[m][n];
		for(int i=0;i<a.length;i++)
		{	
			for(int j=0;j<a[i].length;j++)
			{	
				System.out.println("Enter data at ["+i+"]["+j+"]:");
				a[i][j]=cin.nextInt();
			}
		}
		return a;
		
	}
	static void showMat(int [][]ref)
	{
		for(int i=0;i<ref.length;i++)
		{	
			for(int j=0;j<ref[0].length;j++)
			{	
				System.out.print(ref[i][j]);
			}
			System.out.println();
		}
	}
	static int [][]add(int [][]a,int[][]b)
	{
		if(a.length!=b.length||a[0].length!=b[0].length)
		{
			System.out.println("Wrong Orders");
			return null;
		}
		else
		{
			int[][] add=new int[a.length][];
			for(int i=0;i<a.length;i++)
			{	
				add[i]=new int[a[i].length];
				for(int j=0;j<a[i].length;j++)
				{	
					add[i][j]=a[i][j]+b[i][j];
				}
			}
			return add;
		}
		
	}
}

