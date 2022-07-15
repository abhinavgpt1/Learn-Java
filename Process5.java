class Process5 
{
	public static void main(String[] args) 
	{
	RECT ref=new RECT();
	int a=ref.area(2,3);
	System.out.println("Area"+a);
	}
}
class RECT
{
int area(int l,int b)
{
int a=l*b;
return a;
}
};
