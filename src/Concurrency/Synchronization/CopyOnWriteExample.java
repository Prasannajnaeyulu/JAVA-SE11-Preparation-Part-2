package Concurrency.Synchronization;/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 11: Concurrency
Topic:  CopyOnWriteArrayList
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteExample {
    public static void testList(List<String> currentList) {
        System.out.println("----------- Testing " +
                currentList.getClass().toGenericString());
        System.out.println("Original State: " + currentList);
        // Get Iterator
        // it copies the contents of supplied instance 'currentList' of type CopyOnWriteArrayList to the Iterator
        //
        Iterator<String> it = currentList.iterator();

        int i = 0;

        // Use iterator
        System.out.println("Print first three elements: ");
        while (it.hasNext()) {
            // interesting: if the list is a synchronized list say, ArrayList then
            // this throws runtime exception: 'IllegalStateException'
            // If the list is concurrent say, CopyOnWriteArrayList then it throws 'UnsupportedOperationException'
//            it.remove();

            System.out.println(it.next());
            // Add some elements while iterating over elements
            if (i++ == 0) {
                // If the list is synchronized list say, ArrayList then it throws
                // Exception in thread "main" java.util.ConcurrentModificationException
                // If the list is concurrent say, CopyOnWriteArrayList then it works Ok
                currentList.addAll(List.of("James", "Jim", "Joe"));
                // It removes Evan from the list
                currentList.remove(it.next());
            }
            // break after third element
            else if (i == 3) break;
        }

        // Print remaining elements on iterator
        System.out.println("Printing remaining elements");
        it.forEachRemaining(System.out::println);

        System.out.println("Values = " + currentList + "\n");
    }

    public static void main(String[] args) {

        // Create an ArrayList and initialize with 5 values
        List<String> alist = new ArrayList<>(List.of("" +
                "David", "Evan", "Anne", "Bob", "Carol"));

        // Create a CopyOnWriteArrayList using previous list
        List<String> concurrentList = new CopyOnWriteArrayList<>(alist);

        testList(alist);

    }
}