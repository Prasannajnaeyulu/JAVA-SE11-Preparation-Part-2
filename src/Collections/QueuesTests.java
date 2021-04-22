package Collections;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueuesTests {
    public static void main(String... args){
        Queue<String> queue = new ArrayDeque<>();
        boolean iselementadded = queue.add("Hello");
        System.out.println(iselementadded);
        iselementadded = queue.offer("World");
        System.out.println(iselementadded);
        System.out.println("Retrieving peek of queue: "+ queue.peek());
        // Runtime Error: NullPointerException
        // ArrayDeque does not allow null values. Adding null values throws NullPointerException
//        queue.add(null);
        System.out.println(queue);
        System.out.println("Retrieving head element of queue: "+ queue.element());
        System.out.println("Retrieving peek of queue: "+ queue.poll());
        System.out.println(queue);
        System.out.println("Removing element: " + queue.remove());
        System.out.println(queue);
        // compiler error: removeFirst is not in Queue but in Deque interface
        // But ArrayDeque implements both interfaces hence we can typecast
//        queue.removeFirst();

        // if we typecast it is OK now
        // Queue is empty so remove()... calls throws java.util.NoSuchElementException
        ((ArrayDeque<String>)queue).removeFirst();
    }
}
