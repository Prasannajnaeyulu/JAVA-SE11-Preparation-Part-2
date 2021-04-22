package Builtin_Functional_Interfaces.OutOfOrdinary;

import java.util.function.Consumer;

class Product {
    int id;

    Product(int id) {
        this.id = id;
    }

    public String toString() {
        return "Product " + id;
    }
}

public class Quiz {

    public static void main(String[] args) {
        Product product = new Product(1);

        // compiler error: Variable 'product' is already defined in the scope
//        Consumer<Product> consumerA = (Product product) -> System.out.println(product.id);
//        consumerA.accept(product);

        Consumer<Product> consumerB = id -> System.out.println(id);
        consumerB.accept(product);

        Consumer<Product> consumerC = System.out::println;
        consumerC.accept(product);

        Consumer<Product> consumerD = p -> System.out.println(p.id);
        consumerD.accept(product);

        Consumer<Product> consumerE = (Product p) -> System.out.println(product.id);
        consumerE.accept(new Product(2));

    }
}



