class fact
{
int fact(int n)
{
	int fact=1;
	for(int i=2;i<=n;i++)
		{
		fact=fact*i;
		}
		return fact;
}

};
class hw2 
{
	public static void main(String[] args) 
	{
	int n=4;
fact ref=new fact();
int f=ref.fact(n);
		System.out.println(f);
	}
}
