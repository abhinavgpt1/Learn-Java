package Technicalities.tech9_ImportNC.pack;

public class PublicOC {
    public class PublicIC {
        public int pubx = 10;
        protected int protx = 20;
        int plax = 30;
        private int privx = 40;
    }
    public static class PublicSNC {
        public int pubx = 10;
        protected int protx = 20;
        int plax = 30;
        private int privx = 40;
    }
    protected class ProtectedIC {
        public int pubx = 50;
        protected int protx = 60;
        int plax = 70;
        private int privx = 80;
        //constructor is default protected. For protected inner class to be instantiated outside package, we need to specify public constructor
        public ProtectedIC(){}
    }
    protected static class ProtectedSNC {
        public int pubx = 50;
        protected int protx = 60;
        int plax = 70;
        private int privx = 80;
        public ProtectedSNC(){}
    }
    class PlaIC {
        public int pubx = 90;
        protected int protx = 100;
        int plax = 110;
        private int privx = 120;
    }
    static class PlaSNC {
        public int pubx = 90;
        protected int protx = 100;
        int plax = 110;
        private int privx = 120;
    }
    private class PrivateIC {
        public int pubx = 130;
        protected int protx = 140;
        int plax = 150;
        private int privx = 160;
    }
    private static class PrivateSNC {
        public int pubx = 130;
        protected int protx = 140;
        int plax = 150;
        private int privx = 160;
    }
}
