import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CW72_IteratorAndListIterator {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");

        // Using Iterator to traverse the list in a forward direction
        // ref: https://www.geeksforgeeks.org/java/iterators-in-java/
        System.out.print("(Forward) Iteration using Iterator: ");
        Iterator<String> itr = fruits.iterator();
        while (itr.hasNext()) {
            String element = itr.next();
            System.out.print(element + " ");

            // Iterator contains remove(), but not set()
            // if (condition)
            // itr.remove(); // Removing the element during iteration
        }
        System.out.println("\n------------");

        // Using ListIterator to traverse list-implemented classes in both directions
        // ref: https://www.geeksforgeeks.org/java/listiterator-in-java/
        ListIterator<String> listItr = fruits.listIterator();
        System.out.println("Forward iteration using ListIterator: ");
        while (listItr.hasNext()) {
            System.out.print("Index of upcoming element: " + listItr.nextIndex());
            String element = listItr.next();
            System.out.println(", Element: " + element);
        }
        System.out.println("------------");

        ListIterator<String> listReverseItr = fruits.listIterator(fruits.size()); // accepts the index to start from.
        System.out.println("Backward iteration using ListIterator: ");
        while (listReverseItr.hasPrevious()) {
            System.out.print("Index of upcoming element: " + listReverseItr.previousIndex());
            String element = listReverseItr.previous();
            System.out.println(", Element: " + element);
        }
        System.out.println("------------");

        // ListIterator contains add(), set() and remove()
        ListIterator<String> listItr2 = fruits.listIterator();
        System.out.print("Modifying element starting with 'b' to Uppercase using ListIterator: ");
        while (listItr2.hasNext()) {
            String element = listItr2.next();
            if (element.startsWith("b")) {
                listItr2.set(element.toUpperCase()); // Convert to uppercase if it starts with 'b'
            }
        }
        fruits.stream().forEach(e -> System.out.print(e + " ")); // Print modified list
        System.out.println("\n------------");

        // Iterate from specific element
        System.out.print("Iterating after 'date': ");
        List<String> fruits2 = Arrays.asList("apple", "banana", "cherry", "date", "elderberry", "fig", "grape");
        ListIterator<String> listItr3 = fruits2.listIterator(fruits2.indexOf("date") + 1); 
        // listItr3 resides between "date" and "elderberry" 
        // next() starts execution from 'elderberry'
        // Recall: There exists N+1 cursors. indexOf(date) + 1 = 4 = 4th 0-based cursor = lies between date and elderberry.
        
        while (listItr3.hasNext()) {
            String element = listItr3.next();
            System.out.print(element + " ");
        }
        System.out.println("\n------------");

        System.out.print("Iterating before 'date': ");
        ListIterator<String> listItr4 = fruits2.listIterator(fruits2.indexOf("date") + 1); 
        // listItr4 resides between "date" and "elderberry" 
        // previous() starts execution from 'date'
        while (listItr4.hasPrevious()) {
            String element = listItr4.previous();
            System.out.print(element + " ");
        }
    }
}

/**
 * Output:
 * (Forward) Iteration using Iterator: apple banana cherry 
 * ------------
 * Forward iteration using ListIterator: 
 * Index of upcoming element: 0, Element: apple
 * Index of upcoming element: 1, Element: banana
 * Index of upcoming element: 2, Element: cherry
 * ------------
 * Backward iteration using ListIterator: 
 * Index of upcoming element: 2, Element: cherry
 * Index of upcoming element: 1, Element: banana
 * Index of upcoming element: 0, Element: apple
 * ------------
 * Modifying element starting with 'b' to Uppercase using ListIterator: apple BANANA cherry 
 * ------------
 * Iterating after 'date': elderberry fig grape 
 * ------------
 * Iterating before 'date': date cherry banana apple 
 */