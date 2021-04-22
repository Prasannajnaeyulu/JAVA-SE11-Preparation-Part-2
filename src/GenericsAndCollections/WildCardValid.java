package GenericsAndCollections;

import java.util.List;

public class WildCardValid<T> {
    // can be used in instance variable declaration
    List<?> ls1;
    // can be used inside nested types
    static List<WildCardValid<?>> ls2;

    // can be used as instance method argument
    void print(List<? extends Number> ls){}

    // can be used as instance method return type
    List<? extends Number> test1(List<? extends Number> t){
        return t;
    }

    // can be used for static method argument and return type as well
    static List<? extends Number> test2(List<? extends Number> t){
        return t;
    }

    public static void main(String... args){
    }
}
