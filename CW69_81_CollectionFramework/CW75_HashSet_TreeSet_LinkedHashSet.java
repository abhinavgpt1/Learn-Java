import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class CW75_HashSet_TreeSet_LinkedHashSet {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>(); // Same code works for LinkedHashSet and TreeSet. Output would be different due to underlying data structure.
        // TreeSet accepts a comparator while defining set.
        // If you don't provide a comparator, it will use the natural ordering of the elements (i.e., the compareTo method of the elements).
        // PTR: TreeSet elements must be Comparable or you must provide a Comparator, else you'll get ClassCastException.
        set.add(40);
        set.add(20);
        set.add(10);
        set.add(30);
        /**
         * The following print as follows for different Set implementations:
         * - HashSet: Set: [20, 40, 10, 30]
         * - LinkedHashSet: Set: [40, 20, 10, 30]
         * - TreeSet: Set: [10, 20, 30, 40]
         */
        System.out.println("Set: " + set);

        System.out.println("Is there 10?: " + set.contains(10));
        set.remove(10);

        /**
         * The following print as follows for different Set implementations:
         * - HashSet: Set after removing 10: [20, 40, 30]
         * - LinkedHashSet: Set after removing 10: [40, 20, 30]
         * - TreeSet: Set after removing 10: [20, 30, 40]
         */
        System.out.println("Set after removing 10: " + set);

        System.out.println("Is there 10?: " + set.contains(10));
        System.out.println("Size: " + set.size());
        System.out.println("Is empty?: " + set.isEmpty());
        set.clear();
        System.out.println("Size after clear: " + set.size());
        System.out.println("Set after clear: " + set);

        // PTR: HashSet uses HashMap (hashtable) internally, LinkedHashSet uses LinkedHashMap (hashtable + doubly-linked list) to maintain insertion order, and TreeSet uses TreeMap (red-black tree) to maintain sorted order.

        // Set for custom class
        Set<Student> students = new HashSet<>();
        // Set<Student> students = new LinkedHashSet<>(); // uses hashcode to check for duplicates but maintains insertion order.
        // Set<Student> students = new TreeSet<>(); // uses compareTo to check for duplicates and maintain sorted order. Student class must implement Comparable interface or you must provide a Comparator.
        students.add(new Student(1, "John"));
        students.add(new Student(2, "Jane"));
        students.add(new Student(3, "Doe"));

        students.add(new Student(3, "Doe"));
        students.add(new Student(3, "Doe"));
        students.add(new Student(2, "Jane"));
        students.add(new Student(2, "Jane"));
        System.out.println("Students: " + students);
        // PTR: new TreeSet<>() above will throw ClassCastException: Exception in thread "main" java.lang.ClassCastException: class Student cannot be cast to class java.lang.Comparable.

        // PTR: LinkedHashSet and HashSet need appropriate equals() and hashCode() methods. FYI, these methods come from Object class by default.
        // PTR: TreeSet needs just compareTo() or a Comparator, equals() and hashCode() are not used by TreeSet for checking duplicates, but they are still good to have for general contract of Java objects.
    }
}

class Student {
    private int id; // primary key
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student {id=" + id + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object s) {
        if(this == s) return true;
        if (s == null || getClass() != s.getClass()) return false;
        Student student = (Student) s;
        return id == student.id && name.equals(student.name); // ideally it should be just id as it's primary key but for demonstration we are using both id and name.
    }

    /**
     * Here's why you need to override hashCode() as well:
     * ---------------------------------------------------
     * When you add an object to a HashSet, it uses the object's hash code
     * The equals-hashCode contract
     * In Java, there's a fundamental contract between equals() and hashCode():
     * - If two objects are equal according to equals(), they MUST have the same hash code
     * - If two objects have the same hash code, they don't necessarily have to be equal (hash collisions are allowed)
     * 
     * What happens without hashCode()?
     * When you don't override hashCode(), Java uses the default implementation from Object.hashCode(), 
     * which typically returns a different value for each object instance, even if they are logically equal according to your equals() method.
     * 
     * In above example, (2, Jane) is added twice if hashCode() is not overridden.
     */

    @Override
    public int hashCode() {
        // If not overridden, (2, Jane) will be added to set twice.
        return Objects.hash(id);
    }
}