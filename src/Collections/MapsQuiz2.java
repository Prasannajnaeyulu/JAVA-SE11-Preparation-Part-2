package Collections;

import java.util.LinkedHashSet;
import java.util.List;

// Note: LinkedHashSet does not support duplicates, and are printed in the order in which they were originally inserted.
public class MapsQuiz2 {
    public static void main(String[] args) {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.addAll(List.of("Dog", "Cat", "Gerbil", "Hamster"));
        set.retainAll(List.of("Goat", "Cat", "Hamster"));
        set.addAll(List.of("Cat", "Gerbil"));
        System.out.println(set);
    }
}
