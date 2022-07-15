class PRODUCT
{
	int p=5,q=10;
	void bill()
	{
	int amt=p*q;
	System.out.println(amt);
	}
};
class MALL
{
void billing()
{
Product pen=new Product ();
pen.bill();
static int code=11;
}
};
class BCE10 
{
	public static void main(String[] args) 
	{
	MALL mm=new MALL();
	mm.bill();

	}
}
