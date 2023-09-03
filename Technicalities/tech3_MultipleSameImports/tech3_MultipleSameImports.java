package Technicalities.tech3_MultipleSameImports;
import static Technicalities.tech3_MultipleSameImports.pack.A.*;
import static Technicalities.tech3_MultipleSameImports.pack.A.fun;
import static Technicalities.tech3_MultipleSameImports.pack.A.fun;
import Technicalities.tech3_MultipleSameImports.pack.B;
import Technicalities.tech3_MultipleSameImports.pack.*;
import Technicalities.tech3_MultipleSameImports.pack.*;

public class tech3_MultipleSameImports
{
    public static void main(String[] args)
    {
        fun();
        otherfun();
        B objB = new B();
        System.out.println(objB.ins_X);
    }
    /**
     * Executing commands
     * ------------------
     * CW core java>javac .\Technicalities\tech3_MultipleSameImports\tech3_MultipleSameImports.java
     * CW core java>java Technicalities.tech3_MultipleSameImports.tech3_MultipleSameImports
     *
     * Output:
     * -------
     * inside A
     * duplicate of fun in A
     * 100
     *
     * Warning: Javadoc dangling comment comes because javadoc comment is supposed to be on top of class declaration, a method declaration, or a field declaration
     * https://stackoverflow.com/questions/43375177/warning-dangling-javadoc-comment
     */
}

