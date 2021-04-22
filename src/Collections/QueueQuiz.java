package Collections;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueQuiz {
        public static void main(String[] args) {
            Deque<String> deque = new ArrayDeque<>();
            deque.addLast("first");
            deque.addFirst("last");

            System.out.print(deque.element() + " " + deque.pop() + " ");   // Line 1
            deque.poll();
            System.out.print(deque.poll() + " ");  // Line 2
            System.out.print(deque.peek());  // Line 3
        }
    }