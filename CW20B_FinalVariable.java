class B {
    // final int zp; // needs to be initialized in every constructor else error
    final int z;
    final int x = 6;

    // B() {} // error says z is not intialized here must intialize all final variables

    B(final int y) { // Local Primitive Final Variable (LPFV)
        // y = 3; // won't work if final int y
        z = y;
        // Not necessary to use "final int y" in constructor; z = 10; would have worked too.
        // No need of final variable in constructor either. LPV would have worked too.
        // Once z is initialized, another assignment is not possible. eg. z = 10; // error: variable z might already have been assigned
    }

    void show() {
        // x = 11; // error: cannot assign a value to final variable x
        System.out.println(x + " " + z);
    }
}

class CW20B_FinalVariable {
    public static void main(String args[]) {
        B obj = new B(7);
        System.out.println(obj.z);
        // obj.z = 100; // error: not possible since initialized in constructor and is final
        obj.show();
    }
}
/**
 * Output:
 * -------
 * 7
 * 6 7
 */