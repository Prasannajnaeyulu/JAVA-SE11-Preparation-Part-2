package Collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListsAndSets {
    public static void main(String... args){
        List<Integer> ls = new ArrayList();
        ls.add(10);
        ls.add(20);
        ls.add(30);
        ls.add(40);
        // we can add null to list
        ls.add(null);
        ls.add(null);
        Short s = 10;
        // compiler error: add(java.lang.Integer) in List cannot be applied to (java.lang.Short)
//        ls.add(s);

        // compiler error: required List<Object> found List<Integer>
//        List<Object> ls1 = ls;
        // compiler error: can't cast java.lang.List<Integer> to java.lang.List<Object>
//        List<Object> ls1 = (List<Object>)ls;

          // interesting: we can type cast to raw list as follows
          List<Object> ls1 = (List)ls;
          System.out.println(ls1);

          List<? extends Number> ls2 = ls;
          System.out.println(ls2);

          //compiler error: Wrong 2nd argument type. Found: 'int', required: '? extends java.lang.Number'
//          ls2.add(0, 5);
//        Number n1 = Integer.valueOf(5);

        // compiler error: Wrong 2nd argument type. Found: 'java.lang.Number', required: '? extends java.lang.Number'
//        ls2.add(0, n1);

        // All 3 lists pointing same reference so clearing in one clears the elements in the reference
        // So all prints empty list []
        ls2.clear();
        System.out.println(ls1); // []
        System.out.println(ls2); // []
        System.out.println(ls); // []
        System.out.println(ls.isEmpty()); // true

        Set<String> set = new HashSet<>();
        // set with duplicates
        set.add(null);
        set.add(null);
        set.add(null);
        set.add("hello");
        set.add("hello");
        System.out.println(set); //[null, hello]
        set.remove("hello");
        System.out.println(set); //[null]

        List<String> lsstr = List.of("A", "A", "B", "C", "D");
        set.addAll(lsstr);
        System.out.println(set); // [null, A, B, C, D]
        set.remove("A");
        System.out.println(set); // [null, B, C, D]
        System.out.println(lsstr); // [A, A, B, C, D]
        // Runtime error: lsstr is an immutable collection so list modifications not allowed
        lsstr.removeIf((s1) -> s1.startsWith("B"));
    }
}
