package GenericsAndCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz {
    public static void main(String[] args) {
        List<Long> list1 = new ArrayList<Long>(); // Line 1
        // compiler error: add(java.lang.Long) in List cannot be applied to (int)
//        list1.add(5);   // Line 2
        List<? extends Number> list2 = null;  // Line 3
        // There is nothing wrong with this line of code. You could have replaced List<?> with just List,
        // since type erasure evaluates List<?> as List. Note that using any specific type such as List<Integer> or List<Object>
        // or a wildcard with a bound List<? extends Number> is not valid when using instanceof operator.
        if (list1 instanceof List<?>) { // Line 4
            list2 = list1;      // Line 5
        }
        // compiler error: Illegal generic type for instanceof
//        if (list1 instanceof List<? extends Number>) { }

        // compiler error: Inconvertible types; cannot cast 'java.util.List<java.lang.Long>' to 'java.util.List<java.lang.Number>'
//        if (list1 instanceof List<Number>) {}

        System.out.println(list2);
    }
}