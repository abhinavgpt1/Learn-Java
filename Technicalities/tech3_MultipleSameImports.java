package Technicalities;
import static Technicalities.packTech3.A.*;
import static Technicalities.packTech3.A.fun;
import static Technicalities.packTech3.A.fun;
import Technicalities.packTech3.B;
import Technicalities.packTech3.*;
import Technicalities.packTech3.*;

class tech3_MultipleSameImports
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
     * CW core java> javac .\Technicalities\tech3_MultipleSameImports.java
     * CW core java> java Technicalities.tech3_MultipleSameImports
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

