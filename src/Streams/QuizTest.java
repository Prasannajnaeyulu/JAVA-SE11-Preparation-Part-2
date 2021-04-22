package Streams;

import java.util.stream.Stream;

public class QuizTest {
    public static void main(String[] args) {

        System.out.println(
                Stream.iterate(10, t -> t <= 30, (t) -> t + 10)
                        .mapToInt((s) -> s * 1000) // Line 1
                        .map((s) -> s / 1000) // Line 2
                        .average()); // Line 3

    }
}
