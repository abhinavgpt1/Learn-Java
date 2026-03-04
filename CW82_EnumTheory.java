import java.util.Arrays;
import java.lang.Enum;

public class CW82_EnumTheory {
    public static void main(String[] args) {
        // ref: https://www.geeksforgeeks.org/java/enum-in-java/

        // Enum is a special data type that enables for a variable to be a set of predefined constants.
        // Features:
        // - type safety: enum constants are type-safe, meaning that they can only be assigned values that are defined in the enum.
        // - readability: enums can make code more readable by giving meaningful names to constant values.
        // - maintainability: if you need to add or remove constants, you can simply modify the enum definition without affecting the rest of your code.
        // - IMP: enums are public static final by default.
        // - IMP: enums can have fields, methods, and constructors, which allows for more complex behavior than just a list of constants.
        // - IMP: enums can't extend classes, but they can implement interfaces.
        //  + Reason: Enums can't extend classes because every enum implicitly extends java.lang.Enum, and Java doesn't support multiple inheritance for classes.

        // Syntax:
        /**
         * enum EnumName {
         *  CONSTANT1, 
         *  CONSTANT2, 
         *  CONSTANT3;
         * }
         */

        // PTR: enum declarartion inside method wasn't possible before Java 16. Error used to say that enum can't be local.

        enum Day {
            // semi-colon isn't required if there are no fields/methods/constructors, but it's a good practice to include it.
            SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY; 

            private Day() {
                // constructor for enum, can be used to initialize fields if needed.
            }

            public void printDay() {
                System.out.println(this.name());
            }
        }
        Day monday = Day.MONDAY;
        // Enum methods:
        // - name() : instance method that returns the name of the enum constant as a string.
        // - valueOf(String name) : static method that returns the enum constant with the specified name.
        // - values() : static method that returns an array of all enum constants.
        System.out.println("monday.name(): " + monday.name()); // prints MONDAY
        Day day = Day.valueOf("TUESDAY");
        System.out.println("Day.valueOf(): " + day);
        Day[] dayList = Day.values();
        System.out.println("Day.values(): " + Arrays.toString(dayList));

        System.out.println();

        // Enums can have abstract method, but then each enum constant must implement that method.
        enum Operation {
            ADD {
                @Override
                public double apply(int a, int b) {
                    return a + b;
                }
            },
            DIVIDE {
                @Override
                public double apply(int a, int b) {
                    return a / b;
                }
            };

            public abstract double apply(int a, int b);
        }
        Operation add = Operation.ADD;
        System.out.println("Enum with abstract method impl.: " + add.apply(5, 3)); // prints 8.0

        System.out.println();

        // Enum with fields and methods: Constructor is mandatory in this case, otherwise how would you initialize the fields?
        enum Planet {
            MERCURY(3.303e+23, 2.4397e6),
            VENUS(4.869e+24, 6.0518e6),
            EARTH(5.976e+24, 6.37814e6);

            private final double mass;   // in kilograms
            private final double radius; // in meters

            Planet(double mass, double radius) {
                this.mass = mass;
                this.radius = radius;
            }

            public double getMass() {
                return mass;
            }

            public double getRadius() {
                return radius;
            }

            @Override
            public String toString() {
                return name() + " (mass: " + mass + " kg, radius: " + radius + " m)";
            }

            public static void printPlanets() {
                for(Planet p: Planet.values()) {
                    System.out.println(p);
                }
            }
        }
        Planet earth = Planet.EARTH;
        System.out.println("user-defined instance method: " + earth.getMass()); // prints 5.976E24
        System.out.println("user-defined instance method: " + earth.getRadius()); // prints 6.37814E6
        System.out.println("in-built instance method: name(): " + earth.name()); // prints EARTH
        System.out.println("overriden toString() on instance: " + earth); // prints EARTH (mass: 5.976E24 kg, radius: 6.37814E6 m)
        System.out.println("\nuser-defined static method: printPlanets(): ");
        Planet.printPlanets();

        // Bonus:
        // - EnumSet: a specialized Set implementation for use with enum types. It is a high-performance set implementation that is optimized for enums.
        // - EnumMap: a specialized Map implementation for use with enum keys.
        
    }
}

/**
 * Output:
 * -------
 * monday.name(): MONDAY
 * Day.valueOf(): TUESDAY
 * Day.values(): [SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY]
 * 
 * Enum with abstract method impl.: 8.0
 * 
 * user-defined instance method: 5.976E24
 * user-defined instance method: 6378140.0
 * in-built instance method: name(): EARTH
 * overriden toString() on instance: EARTH (mass: 5.976E24 kg, radius: 6378140.0 m)
 * 
 * user-defined static method: printPlanets(): 
 * MERCURY (mass: 3.303E23 kg, radius: 2439700.0 m)
 * VENUS (mass: 4.869E24 kg, radius: 6051800.0 m)
 * EARTH (mass: 5.976E24 kg, radius: 6378140.0 m)
 */