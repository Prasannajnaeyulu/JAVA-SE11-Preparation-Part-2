package GenericsAndCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz2 {
    public static void main(String[] args) {
        Quiz2 test = new Quiz2();
        List<? extends Number> items =
                new ArrayList<Integer>(Arrays.asList(1, 2, 3));  // Line 1
        // interesting: all lines would work and all produce the result [1,2,3]
        test.printItOut(items);
        test.<Object>printItOut(items);
        test.<List<?>>printItOut(items);
        // compiler error: can't use wild cards for parameter types
//        test.<? extends List>printItOut(items);
        test.<List<? extends Number>>printItOut(items);

        // the above calls we can simulate to the following
        List l1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));  // Line 1
        System.out.println(l1);

        List<?> l2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));  // Line 1
        System.out.println(l2);

        Object l3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));  // Line 1
        System.out.println(l3);

    }

    public <T> void printItOut(T type) {
        System.out.println(type);
    }
}
