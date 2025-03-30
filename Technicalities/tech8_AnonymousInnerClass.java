// ref: https://www.geeksforgeeks.org/anonymous-inner-class-java/
abstract class Base{
    int bx = 10;
    abstract void absMethod();
    void nonAbsMethod(){
        System.out.println("inside base class non-abs method");
    }
    void bogusFunction(){}
}
public class tech8_AnonymousInnerClass {
    public static void main(String[] args) {
        System.out.println("Initial statements before static block call in AIC");
        
        Base bref = new Base() { //implicit "extends" => cannot extend/implement more than one class or interface
            int norefOutside = 10; //warning: due to object slicing this won't be referred outside class
            static int norefstatOutside = 10; //warning: due to object slicing this won't be referred outside class
            void absMethod(){
                System.out.println("implementing abs method");
            }
            void nonAbsMethod(){
                System.out.println("overriding definition in AIC");
            }
            
            // no restriction on static initializer or interface declaration, said otherwise by docs
            interface norefinterface{}
            class norefclass implements norefinterface{}
            static {
                System.out.println("-------------static block works by bogus function call-------------");
                norefstatOutside = 90;
            }
            void norefdirect(){
                System.out.println("norefOutside: "+norefOutside);
                System.out.println("norefstatOutside: "+norefstatOutside + " <- IMP.");
            }
            void bogusFunction(){ //overriding and introducing class level members' usage
                System.out.println("-------------inside bogus-------------");
                norefdirect();
            }
        };
        // System.out.println(bref.norefOutside); //error: cannot be resolved
        // System.out.println(bref.norefstatOutside); //error: cannot be resolved
        bref.absMethod();
        bref.nonAbsMethod(); //runtime polymorphism
        // bref.norefdirect(); //error: undefined for type Base
        bref.bogusFunction();


        // Notice:
        // name of class formed in LIC and AIC differs as:
            // <class where LIC/AIC is written>$1 -> AIC since its anonymous
            // <class where LIC/AIC is written>$1<LIC name> -> appending LIC name
    }
    /**
     * Classes formed:
     *  -> Base.class
     *  -> tech8_AnonymousInnerClass.class
     *  -> tech8_AnonymousInnerClass$1.class
     *  -> tech8_AnonymousInnerClass$1@norefclass.class     --not able to use, but it is formed
     *  -> tech8_AnonymousInnerClass$1@norefinterface.class --not able to use, but it is formed
     * Output:
     * -------
     * Initial statements before static block call in AIC
     * -------------static block works by bogus function call-------------
     * implementing abs method
     * overriding definition in AIC
     * -------------inside bogus-------------
     * norefOutside: 10
     * norefstatOutside: 90 <- IMP.
     */
}