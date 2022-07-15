class B
{
	B()
	{
		System.out.println("Base Class");
	}
	B(int x)
	{
		System.out.println(x);
	}
};
class D extends B
{
	D()
	{
		System.out.println("Derived Class");
	}
	D(float f,int i)
	{
		//super();
		super(i);
		System.out.println(f);
	}
};
class Constructors
{
	public static void main(String[] args) 
	{
		D obj=new D();
		D obj1=new D(2.6f,6);
		System.out.println("Hello World!");
	}
}
