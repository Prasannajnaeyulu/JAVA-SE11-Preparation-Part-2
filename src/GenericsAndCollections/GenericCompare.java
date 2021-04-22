package GenericsAndCollections;

import java.util.List;

/* A generic class with two type parameters where the first parameter type must extend Comparable
And Second parameter type can be anything
 */
public class GenericCompare<T extends Comparable, U> {
    U value;

    GenericCompare(U value){
        this.value = value;
    }

    U getValue(){
        return value;
    }

    <X, W extends List<X>> X getComparableResults(T s1, T s2, W definitions) {
        int result = s1.compareTo(s2);
        if (result != 0)
            result = result < 0 ? -1 : 1;
        result++;
        return definitions.get(result);
    }

    <T extends Comparable> int compareTypes(T s1, T s2){
        return s1.compareTo(s2);
    }

    static int compareObjects(Object o1, Object o2){
        if(o1 instanceof Apple)
            return ((Apple)o1).compareTo((Apple)o2);
        else if(o1 instanceof Orange)
            return ((Orange)o1).compareTo((Orange)o2);
        return -1;
    }

    public static void main(String... args){
        GenericCompare<String, Byte> gc = new GenericCompare<>(Byte.valueOf("10"));
        String s1 = "Hello";
        String s2 = "World";
        String result = gc.getComparableResults(s1, s2, List.of("Less Than", "Equals", "Greater Than"));
        System.out.println(s1+ " is "+ result+ " "+ s2);
        System.out.println("Value: "+ gc.getValue());

        GenericCompare<Integer, Integer> gc1 = new GenericCompare<>(Integer.valueOf("20"));
        Integer i1 = 10;
        Integer i2 = -20;
        String result1 = gc1.getComparableResults(i1, i2, List.of("Less Than", "Equals", "Greater Than"));
        System.out.println(i1+ " is "+ result1+ " "+ i2);
        System.out.println("Value: "+ gc1.getValue());

        Apple a1 = new Apple("Red");
        Apple a2 = new Apple("Green");

        compareObjects(a1, a2);

        Orange o1 = new Orange("Yellow");
        Orange o2 = new Orange("Orange");

        compareObjects(o1, o2);

//        compareObjects(a1, o1);

    }
}

class Apple implements Comparable<Apple>{
    String name;

    Apple(String s){
        this.name = s;
    }

    @Override
    public int compareTo(Apple o) {
        return this.name.compareTo(o.name);
    }
}

class Orange implements Comparable<Orange>{
    String name;

    Orange(String s){
        this.name = s;
    }

    @Override
    public int compareTo(Orange o) {
        return this.name.compareTo(o.name);
    }
}