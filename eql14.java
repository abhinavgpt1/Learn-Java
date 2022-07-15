class STUDENT
{
int per,tot;
STUDENT(int per,int t)
{
this.per=per;
tot=t;
}
};
class eql 
{
	public static void main(String[] args) 
	{
		STUDENT amn=new STUDENT(10,20);
		STUDENT rmn=new STUDENT(10,20);

		System.out.println(amn==rmn);
		System.out.println(amn.equals(rmn));
		String S1=new String("BCE");
		String S2=new String("BCE");
		System.out.println(S1==S2);
		System.out.println(S1.equals(S2));
	}
}
