package CW27_NestedClass.CW27I_ImportNC;

import static CW27_NestedClass.CW27I_ImportNC.pack.PublicOC.PublicSNC;

// Test: if IC and SNC can be accessed without importing OC
public class AccessOutsideWithoutImportOC {
    public static void main(String[] args) {
        // IC cannot be instantiated as it is strongly connected to OC
        // PublicOC.PublicIC publicIC = new PublicOC().new PublicIC(); // error: PublicOC cannot be accessed without import
            // this will work if both classes are in default package - refer tech0_packageAndMemberAccessCases > noPkg scenario
        // PublicOC.PublicSNC publicSNC = new PublicOC.PublicSNC();
            // this will also not work as PublicOC is not imported
        PublicSNC publicSNC = new PublicSNC(); // this works as only static member is imported instead of whole class
        System.out.println(publicSNC.pubx);

        //similarly if we import IC as "import CW27I_ImportNC.pack.PublicOC.PublicIC;" then too it won't work without importing+initializing object of OC
        // PublicOC.PublicIC publicIC = publicOC.new PublicIC(); // error: needs enclosing instance

        // using fully qualified name will still require object initialization of OC
        // CW27I_ImportNC.pack.PublicOC.PublicIC publicIC = new PublicIC(); // error: needs enclosing instance

        // RECALL: why PublicOC classes come into existence even when there is no import
        // it was an observation that class compiles when
            // class explictly imported or used (during On-demand declaration)
            // static member(s) imported
    }
}

/**
 * Executing Command
 * -----------------
 * Learn-Java.git> javac .\CW27_NestedClass\CW27I_ImportNC\AccessOutsideWithoutImportOC.java
 * Learn-Java.git> java CW27_NestedClass.CW27I_ImportNC.AccessOutsideWithoutImportOC
 * 
 * Output
 * ------
 * 10
 */
