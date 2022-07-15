package pack.loops;
public class FactN
{
	public int getFact(int n)
	{
		int fact=1;
		for(int i=2;i<=n;i++)
		{
			fact*=i;
		}
		return fact;
	}
};