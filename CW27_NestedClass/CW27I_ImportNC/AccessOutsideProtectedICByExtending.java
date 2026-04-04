package CW27_NestedClass.CW27I_ImportNC;

import CW27_NestedClass.CW27I_ImportNC.pack.PublicOC;

public class AccessOutsideProtectedICByExtending extends PublicOC {
    void accessProtectIC(){
        class MyClass extends PublicOC.ProtectedIC {
            void accessProtectedICFields() {
                System.out.println("pubx:" + pubx);
                System.out.println("protx:" + protx);
            }
        }
        
        MyClass myClass = new MyClass();
        System.out.println("MyClass pubx:" + myClass.pubx); // 50
        // System.out.println("MyClass protx:" + myClass.protx); // protected members aren't visible outside. Had these 2 classes been in default package, then this would work.
        myClass.accessProtectedICFields();
    }
    public static void main(String[] args) {
        // Task: try running this code with protected constructor in ProtectedIC
        new AccessOutsideProtectedICByExtending().accessProtectIC();
    }
}

/**
 *
 * Executing Command
 * -----------------
 * Learn-Java.git> javac .\CW27_NestedClass\CW27I_ImportNC\AccessOutsideProtectedICByExtending.java
 * Learn-Java.git> java CW27_NestedClass.CW27I_ImportNC.AccessOutsideProtectedICByExtending
 * 
 * Output
 * ------
 * MyClass pubx:50
 * pubx:50
 * protx:60
 */
