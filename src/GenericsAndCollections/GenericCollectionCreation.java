package GenericsAndCollections;

class GenericallyTyped<T>{
    T currentobj;
    GenericallyTyped(T t){
        this.currentobj = t;
    }

    void print(){
        System.out.println("GenericTyped Object "+ currentobj);
    }
}
public class GenericCollectionCreation {
    public static void main(String... args){
        GenericallyTyped<String> generic1 = new GenericallyTyped<>("Hello");
        // compiler error: type must be specified on the left hand side of assignment
//        GenericallyTyped<> str1 = new GenericallyTyped<>("Hello");

        GenericallyTyped<String> generic2 = new GenericallyTyped<String>("Hello");
        GenericallyTyped<StringBuilder> generic3 = new GenericallyTyped<StringBuilder>(new StringBuilder("Hello"));
        // compiler error: generic1 is String type but it is StringBuilder so won't allow
//        generic1 = new GenericallyTyped<>(new StringBuilder("World"));
        // compiler error: Required String but found StringBuilder
//        generic1 = new GenericallyTyped<StringBuilder>(new StringBuilder("World"));
        //Note: generic1 is of type GenericallyTyped<String>. Where as the one here is Raw type with StringBuilder Object
        // Where as StringBuilder Objects

        // Runtime error: can't cast StringBuilder to String Exception in thread "main" java.lang.ClassCastException:
        // class java.lang.StringBuilder cannot be cast to class java.lang.String (java.lang.StringBuilder and java.lang.String
        // are in module java.base of loader 'bootstrap') at
        // GenericsAndCollections.GenericCollectionCreation.main(GenericCollectionCreation.java:27)
        generic1 = new GenericallyTyped(new StringBuilder("Hello"));
        System.out.println(generic1.currentobj);
    }
}
