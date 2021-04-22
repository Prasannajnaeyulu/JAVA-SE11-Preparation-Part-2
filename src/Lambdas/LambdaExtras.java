package Lambdas;

import java.util.*;

// interesting: A Functional interface can have Object class methods declared as abstract
// but they are not counted for abstract methods
interface Functionable{
    void print();
    String toString();
    boolean equals(Object o1);
}

interface ExtendedFunctionable extends Functionable{
    void printV2();
}

// interesting: Note this is not String datatype rather it is just a generic type String same as T
interface Confusable<String> extends Comparator<String>{
    default int compare(String s1, String s2){
        Random r = new Random();
        int i = r.nextInt(10);

        if(i%2 == 0 ) i = -i;
        return i;
    }
    void print();
}

public class LambdaExtras {
    public static void main(String... args){
        // interesting: Object class methods can't be counted under abstract methods calculation
        // so this lambda expression matches to abstract method print()
        Functionable f1 = () -> System.out.println("Hello");
        f1.print();

        // compiler error: Multiple non-overriding abstract methods found in interface Lambdas.ExtendedFunctionable
//        ExtendedFunctionable f2 = () -> System.out.println("World");

        Confusable<String> c = () -> System.out.println("Custom Comparator");
        c.print();
        List<String> ls = new ArrayList<>(List.of("Jane", "Mark", "Emily"));
        // Note: Collections.sort takes custom comparator as second argument
        // any class implements comparator would be fine
        Collections.sort(ls, c);
        System.out.println(ls);

    }
}
