class Rect{
    int L = 5;
}
class Process{
    final Rect ref = new Rect();
}

class FinalObject{

    public static void main(String args[]){
        Process p = new Process();
        Rect obj = new Rect();
        // p.ref = obj;//cannot assign a value to final variable ref
        // p.ref = null;//cannot assign a value to final variable ref
        System.out.println(p.ref.L);
        p.ref.L = 100;//valid
        System.out.println(p.ref.L);
    }
}