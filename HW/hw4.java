class Height
{
	int m,cm;
	void setHeight(int M,int c)
	{
		m=M;
		cm=c;
	}
	Height diff(Height h)
	{
		Height pitaji=new Height();
		pitaji.cm=Math.abs((m*100+cm)-(h.m*100+h.cm));
		pitaji.m=pitaji.cm/100;
		pitaji.cm=pitaji.cm%100;
		return pitaji;
				
	}
	void show()
	{
		System.out.println(m+"  "+cm);
	}
}
class hw4
{
	public static void main(String[] args) 
	{
		Height h1=new Height();
		h1.setHeight(1,2);
		Height h2=new Height();
		h2.setHeight(3,4);

		Height d=h1.diff(h2);
		d.show();
		
	}
}
