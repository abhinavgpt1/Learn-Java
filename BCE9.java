class Product
{
int p=5,q=10;
void bill()
{
int amt =p*q;
System.out.println(amt);
}
};
class MALL
{ 
Product pen=new Product ();
static int code=11;
static Product lap= new Product ();
};
class BCE9 
{
	public static void main(String args[]) 
	{
	MALL mm =new MALL ();
	System.out.println(mm.pen.p);
	mm.pen.bill();
	System.out.println(MALL.code);
	MALL.lap.bill();
	}
}
