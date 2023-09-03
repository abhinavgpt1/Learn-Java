/**
 * Classes formed:
 *  -> OC.class
 *  -> OC$IC.class
 *  -> OC$SNC.class
 * Command:
 * --------
 * > java OC$IC
 * Output> Commencing code from IC
 * > java OC$SNC
 * Output> Commencing code from SNC
 */
class OC {
    class IC {
        public static void main(String[] args){
            System.out.println("Commencing code from IC"); // wasn't possible in versions < Java 16
        }
    }

    static class SNC {
        public static void main(String[] args){
            System.out.println("Commencing code from SNC");
        }
    }
}