class Professor
{
	String name;
};
class Department
{
	Professor HOD;
	void setHOD(Professor ref)
	{	
		HOD=ref;
	}
	void showHOD()
	{	
		System.out.println(HOD.name);
	}
};
class  University
{
	public static void main(String[] args) 
	{
		Professor Prof=new Professor();
		Prof.name="PKGPade";
		Department CSE=new Department();
		CSE.setHOD(Prof);
		CSE.showHOD();
		
		Department ECE=new Department();
		ECE.setHOD(Prof);
		ECE.showHOD();
		ECE=null;
		CSE=null;
		System.out.println(Prof.name);
	}
}
