package GenericsAndCollections;

public class GenericMethods<T> {
    // interesting: Note: static methods can have method parameters as generic types
    // this type is method parameter not the class parameter if it is a class parameter
    // you can't use them in static context
    public static <T> T getValue(T t){
        return t;
    }

    public <T> void print(T t){
        System.out.println(t);
    }

    // compiler: Can't resolve symbol 'T'. The method argument type name (U) should be same as parameter type name(T)
//    public <U> void printNew(T t){
//        System.out.println(t);
//    }

    // Note: the 'T' here is specific to this method and can't be of class Type or other methods
    public <T> void printNew(T t){
        System.out.println(t);
    }



    public static void main(String... args){
        System.out.println(GenericMethods.getValue(10));
        new GenericMethods().print(20);
        new GenericMethods().printNew(30);
    }
}
