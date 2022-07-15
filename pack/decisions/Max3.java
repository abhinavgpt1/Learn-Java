package pack.decisions;
public class Max3

{
	public int getMax3(int x,int y,int z)
	{
		if(x>y&&x>z)
		return x;
		else
			if(y>z)
				return y;
			else
			return z;
	}
};