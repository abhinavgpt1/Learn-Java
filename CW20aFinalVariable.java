class B {
    // final int zp; // needs to be initialized in every constructor else error
    final int z;
    final int x = 6;

    // B(){ // error says z is not intialized here
    // must intialize all final variables
    // }
    B(final int y) { //LPFV: just for demonstration; not really a final is required to init z
        // y = 3; //won't work if final int y
        z = y; 
            //need not require "final int y" in constructor; z = 10; would have worked too. No need of final in constructor either.
            //once assigned to z, another assignment is not possible
        // z = 10;//initialization once only
    }

    void show() {
        // x = 11;//not allowed
        System.out.println(x + " " + z);
    }
}

class FinalVariable {

    public static void main(String args[]) {
        B obj = new B(7);
        System.out.println(obj.z);
        // obj.z = 100;//not possible since initialized in constr.
        obj.show();
    }
}