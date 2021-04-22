package Streams;

import java.util.stream.Collectors;
import java.util.stream.Stream;


enum Category {
    FRUIT, VEGETABLE;
}

class ProduceItem {
    private Category category;
    private String value;

    ProduceItem(Category category, String value) {
        this.category = category;
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public String toString() {
        return value;
    }
}

public class QuizTest2 {
    public static void main(String[] args) {
        // interesting: partitioningBy partitions stream into two lists. One that is not matches predicate and other matches predicate
        // returns false list first and then true list
        // The collect terminal operation creates a Map<Boolean, <List<ProduceItem>>, keyed by false and true values.
        // The false key is first and contains the Fruit ProduceItems.
        // then the true key next contains Vegetable ProductItems
        Stream.of(
                new ProduceItem(Category.FRUIT, "Apple"),
                new ProduceItem(Category.VEGETABLE, "Broccoli"),
                new ProduceItem(Category.FRUIT, "Pear")
        ).collect(Collectors.partitioningBy(
                c -> c.getCategory() == Category.VEGETABLE)) // Line 1
                .values().stream()  // Line 2
                .forEach(System.out::print);

    }
}
