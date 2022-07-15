interface Shape
{
	void draw();
}
class Rect implements Shape
{
	public void draw()
	{
		System.out.println("Area of Rect");
	}
};
class Circle implements Shape
{
	public void draw()
	{
		System.out.println("Area of Circle");
	}
};
class Robot
{
	void DrawNaaa(Shape Ref)
	{
		Ref.draw();
	}
	Shape getObject(String what)
	{
		if(what.equals("dibba"))
		{
			Rect rRef=new Rect();
			return rRef;

		}
		else
			if(what.equals("gola"))
			{
				return new Circle();

			}
			else
			return null;
	}

};
class  PAPR
{
	public static void main(String[] args) 
	{
		Robot robo=new Robot();
		Rect rObj=new Rect();
		Circle cObj=new Circle();
		robo.DrawNaaa(rObj);
		robo.DrawNaaa(cObj);
		//-----------------------
		Rect rc=(Rect)robo.getObject("dibba");
		rc.draw();
		Shape sRef=robo.getObject("gola");
		sRef.draw();
		
		Circle cRef=(Circle)sRef;
		cRef.draw();
		//System.out.println("Hello World!");
	}
}
