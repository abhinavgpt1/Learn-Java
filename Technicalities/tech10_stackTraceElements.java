// ref: https://www.tutorialspoint.com/java/lang/java_lang_stacktraceelement.htm
/**
 * 1. stackTraceELement
 * 2. exception gets propogated from caller function to calling function if unhandled
 * 3. 1/0.0f or 1/0.0 is not an exception - it is Infinity <- a macro defined in Float and Double
 */
package Technicalities; 
public class tech10_stackTraceElements {
    void dependentFunction() {
        int x = 1/0;
    }
    void function1() {
        float noExceptionHere = 1/0.0f;
        if(noExceptionHere == Float.POSITIVE_INFINITY){
            System.out.println("no exception here");
        }
        dependentFunction();
    }
    public static void main(String[] args) {
        tech10_stackTraceElements obj = new tech10_stackTraceElements();
        try {
            obj.function1();
        } catch(Exception ex) {
            System.out.println("exception propogates across stack frames");
            for(StackTraceElement ste: ex.getStackTrace()) {
                System.out.println(ste.toString());
            }
        }
    }
    /**
     * Execution Command:
     * ------------------
     * CW core java>javac .\Technicalities\tech10_stackTraceElements.java
     * CW core java>java Technicalities.tech10_stackTraceElements
     *
     * Output:
     * -------
     * no exception here
     * exception propogates across stack frames
     * Technicalities.tech10_stackTraceElements.dependentFunction(tech10_stackTraceElements.java:10)
     * Technicalities.tech10_stackTraceElements.function1(tech10_stackTraceElements.java:17)
     * Technicalities.tech10_stackTraceElements.main(tech10_stackTraceElements.java:22)
     */
}