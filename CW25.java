/*
import pack.decisions.Max2;
import pack.decisions.Max2;
*/
import pack.decisions.*;
import pack.*;
import pack.loops.SumN;
import static pack.Shapes.*;
class UsePack extends Max2
{
	public static void main(String[] args) 
	{
		line();
		Max2 m=new Max2();
		int mx2=m.getMax2(33,55);
		System.out.println("Greatest of 2:"+mx2);
		UsePack up=new UsePack();
		int mx21=up.getMax2(55,88);
		System.out.println("Greatest of 2:"+mx21);
		Shapes.line();
		Max3 m3=new Max3();
		int mx3=m3.getMax3(33,55,66);
		System.out.println("Greatest of 3:"+mx3);
		SumN sn=new SumN();
		int sum=sn.getSum(7);
		System.out.println("Sum:"+sum);
		Shapes.line();
		pack.loops.FactN fn=new pack.loops.FactN();
		int fact=fn.getFact(4);
		System.out.println("Factorial:"+fact);
	}
}
