package Collections;

import java.util.ArrayList;
import java.util.List;

public class GenericCollectionExamples {
    public static void main(String... args){
        List<?> ls = new ArrayList<>();
        // compiler error: found int required '?'
//        ls.add(10);
//        Object a = 10;
        // compiler error: add(capture<?>) in java.util.list can't be applied to add(Object)
//        ls.add(a);

        /* compiler error: List<Number> can't be assigned to List<Integer>
        Incompatible types. Required: List<java.lang.Number> Found: ArrayList<java.lang.Integer>
         */
//        List<Number> ls2 = new ArrayList<Integer>();

        List<? extends Number> ls3 = new ArrayList<Integer>();
        // compiler error: add(capture<? extends java.lang.Number>) in List cannot be applied to (int)
        // this is because List<? extends Number> can refer to any subtype of number say, Float, Long, Double etc..
        // Hence adding direct primitive or Integer wrapper types to add doesn't allow by compiler
        // ls3.add(10);
        // compiler error: even adding Object or Number type also not allowed
//        Object a1 = 10;
//        ls3.add(a1);
//        Number a1 = 10;
//        ls3.add(a1);

        // this is Ok. Here we declared a Lower Bound as Number there won't be any specific Object types other than number
        // that this list can refer to. So this list points to either the Number or its super type ArrayList
        // So adding integer should be fine because Integer can fit to Any number type or its super class
        List<? super Number> ls4 = new ArrayList<>();
        ls4.add(10);
        ls4.add(10.25);
        ls4.add(10L);

        System.out.println(ls4); // [10, 10.25, 10]

        List<CharSequence> ls5 = new ArrayList<>();
        ls5.add(new StringBuilder("Hello"));
        ls5.add(new String("World"));
        System.out.println(ls5);
    }
}
