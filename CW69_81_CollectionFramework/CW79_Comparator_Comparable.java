import java.util.Arrays;
import java.util.Comparator;

class Student {
    String name;
    int height;
    int age;

    Student(String name, int height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Student {name='%s', height=%d, age=%d}", name, height, age);
    }

    String getName() {
        return name;
    }

    int getHeight() {
        return height;
    }

    int getAge() {
        return age;
    }
}

class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

public class CW79_Comparator_Comparable {
    public static void main(String[] args) {
        // Check basics and comparison between Comparable and Comparator in _09_Collections.java
        // Above ref is more than enough for Comparable. Following program is to show various Comparator features.
        
        Student[] students = {
                new Student("alice", 160, 20),
                new Student("bob", 175, 22),
                new Student("alice", 165, 21),
                new Student("charlie", 160, 19),
                new Student("bob", 175, 20)
        };

        System.out.println("Original array:");
        Arrays.stream(students).forEach(System.out::println);

        /* Conventional way of using Comparator */
        System.out.println("\n1. Using Impl. of Comparator (asc. name):");
        Arrays.sort(students, new StudentNameComparator());
        Arrays.stream(students).forEach(System.out::println);

        /* Comparator.comparing() : returns Comparator<T> */
        System.out.println("\n2. using comparing(s -> s.height), comparing(Student::getHeight), comparingInt(s -> s.height):");
        Comparator<Student> byHeight = Comparator.comparing(Student::getHeight); 
        // or Comparator.comparing(s -> s.height)
        // or Comparator.comparingInt(s -> s.height) // Similarly there are comparingLong and comparingDouble.
        Arrays.sort(students, byHeight);
        Arrays.stream(students).forEach(System.out::println);
        
        /* thenComparing() : returns Comparator<T> and can be chained */
        System.out.println("\n3. thenComparing() - Sort by name, then by height using chaining:");
        Comparator<Student> nameFirstThenHeight = Comparator.comparing((Student s) -> s.name)
                .thenComparing(s -> s.height);
        Arrays.sort(students, nameFirstThenHeight);
        Arrays.stream(students).forEach(System.out::println);

        // IMP PTR: comparing() requires a function and not a comparator.
        // Meanwhile thenComparing() requires comparator. 
        // - i.e. why we use Comparator.comparing inside thenComparing()

        /* reversed() : BEWARE: it reverses everything if used after thenComparing() */
        System.out.println("\n4. reversed() - Sort by name descending:");
        Comparator<Student> descNameComparator = Comparator.comparing((Student s) -> s.name).reversed();
        Arrays.sort(students, descNameComparator);
        Arrays.stream(students).forEach(System.out::println);
        
        // PTR: Use this overloaded function instead of chaining reversed() - Comparator.comparing(function, comparator)
        // - Comparator<Student> descNameComparator2 = Comparator.comparing(Student::getName, Comparator.reverseOrder())

        // Complex chaining: name asc, height desc, age asc
        System.out.println("\n5. Complex chaining - name(asc), height(desc), age(asc):");
        Comparator<Student> complex = Comparator.comparing((Student s) -> s.name)
                .thenComparing(Comparator.comparing((Student s) -> s.height).reversed())
                .thenComparing(s -> s.age);
        Arrays.sort(students, complex);
        Arrays.stream(students).forEach(System.out::println);
        // IMP: complex.reversed(); // this will reverse the whole order - name desc, height asc, age desc

        /* naturalOrder() and reverseOrder() */
        System.out.println("\n6. naturalOrder() and reverseOrder() for integers:");
        Integer[] numbers = { 5, 2, 8, 1, 9 };
        Arrays.sort(numbers, Comparator.naturalOrder());
        System.out.println("Natural order: " + Arrays.toString(numbers));
        Arrays.sort(numbers, Comparator.reverseOrder());
        System.out.println("Reverse order: " + Arrays.toString(numbers));

        /* Custom comparator using compare() method */
        System.out.println("\n7. Custom Comparator with compare() method:");
        Comparator<Student> customComparator = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // Custom logic: sort by age difference from 20
                int ageDiff1 = Math.abs(s1.age - 20);
                int ageDiff2 = Math.abs(s2.age - 20);
                return Integer.compare(ageDiff1, ageDiff2);
            }
        };
        Arrays.sort(students, customComparator);
        System.out.println("Custom: closest to age 20 first:");
        Arrays.stream(students).forEach(System.out::println);

        // IMP: PTR: natural ordering eg. Comparator.naturalOrder() or studentList.sort(null) is valid only for inbuilt types and not for user defined types unless they implement Comparable interface.

        // (can skip)
        // - nullsFirst() and nullsLast()
        System.out.println("\n - nullsFirst() and nullsLast():");
        String[] names = { "alice", null, "bob", "charlie", null };
        Arrays.sort(names, Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println("Nulls first: " + Arrays.toString(names));
        Arrays.sort(names, Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println("Nulls last: " + Arrays.toString(names));

        // PTR: passing a get method using method reference inside comparing() is much
        // convienient than casting like (Student s) -> s.name. Instead use
        // Student::getName.
    }
}

/**
 * Output:
 * -------
 * Original array:
 * Student {name='alice', height=160, age=20}
 * Student {name='bob', height=175, age=22}
 * Student {name='alice', height=165, age=21}
 * Student {name='charlie', height=160, age=19}
 * Student {name='bob', height=175, age=20}
 * 
 * 1. Using Impl. of Comparator (asc. name):
 * Student {name='alice', height=160, age=20}
 * Student {name='alice', height=165, age=21}
 * Student {name='bob', height=175, age=22}
 * Student {name='bob', height=175, age=20}
 * Student {name='charlie', height=160, age=19}
 * 
 * 2. using comparing(s -> s.height), comparing(Student::getHeight), comparingInt(s -> s.height):
 * Student {name='alice', height=160, age=20}
 * Student {name='charlie', height=160, age=19}
 * Student {name='alice', height=165, age=21}
 * Student {name='bob', height=175, age=22}
 * Student {name='bob', height=175, age=20}
 * 
 * 3. thenComparing() - Sort by name, then by height using chaining:
 * Student {name='alice', height=160, age=20}
 * Student {name='alice', height=165, age=21}
 * Student {name='bob', height=175, age=22}
 * Student {name='bob', height=175, age=20}
 * Student {name='charlie', height=160, age=19}
 * 
 * 4. reversed() - Sort by name descending:
 * Student {name='charlie', height=160, age=19}
 * Student {name='bob', height=175, age=22}
 * Student {name='bob', height=175, age=20}
 * Student {name='alice', height=160, age=20}
 * Student {name='alice', height=165, age=21}
 * 
 * 5. Complex chaining - name(asc), height(desc), age(asc):
 * Student {name='alice', height=165, age=21}
 * Student {name='alice', height=160, age=20}
 * Student {name='bob', height=175, age=20}
 * Student {name='bob', height=175, age=22}
 * Student {name='charlie', height=160, age=19}
 * 
 * 6. naturalOrder() and reverseOrder() for integers:
 * Natural order: [1, 2, 5, 8, 9]
 * Reverse order: [9, 8, 5, 2, 1]
 * 
 * 7. Custom Comparator with compare() method:
 * Custom: closest to age 20 first:
 * Student {name='alice', height=160, age=20}
 * Student {name='bob', height=175, age=20}
 * Student {name='alice', height=165, age=21}
 * Student {name='charlie', height=160, age=19}
 * Student {name='bob', height=175, age=22}
 * 
 *  - nullsFirst() and nullsLast():
 * Nulls first: [null, null, alice, bob, charlie]
 * Nulls last: [alice, bob, charlie, null, null]
 */