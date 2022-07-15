class RECT {
    void area() {
        System.out.println("Le Rect");
    }
};

class Circle extends RECT {
    void area() {
        System.out.println("Le Circle");
    }

    void Rarea() {
        area();

        super.area();
    }
};

class Inh {
    public static void main(String[] args) {
        Circle obj = new Circle();
        obj.area();
        obj.Rarea();

    }
}
