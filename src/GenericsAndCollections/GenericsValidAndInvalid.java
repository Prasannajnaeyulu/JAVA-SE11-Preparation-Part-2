package GenericsAndCollections;

// compiler error: Generic class may not extend 'java.lang.Throwable' or any of its subclasses
// like Error, Exception
//class Test<T> extends Throwable{}
//class Test<T> extends Exception{}
//class Test<T> extends Error{}

class Test<T> {
    T currentobj;
    Test(T t){
        try {
            this.currentobj = t;
        }
        // compiler error: Generic types can't be used for catch block types
        // Incompatible types.
        //Required:
        //java.lang.Throwable
        //Found:
        //T
//        catch (T e){}
        finally{ System.out.println("Instantiated Object of type : "+ currentobj.getClass().getName());}
    }

    // interesting: we can't overload methods taking same raw type i.e., 'Test' in this example even though their inferred type
    // is different
    // compiler error: methodA(Test<String>)' clashes with 'methodA(Test<Number>)'; both methods have same erasure
//    void methodA(Test<String> s){}
    // compiler error: methodA(Test<Number>)' clashes with 'methodA(Test<Integer>)'; both methods have same erasure
//    void methodA(Test<Number> s){}
    void methodA(Test<Integer> s){}

    // Types are known at declaration and instantiation of the current Object. So referring 'T' in static context will not resolve T
    // So we can't refer the generic Types in a static context (static field, method or static initializer)
    // compiler error: GenericsAndCollections.Test.this' cannot be referenced from a static context
//    static void methodB(T e){}
//    static{ T t;}
//    static T a;

    void print(){
        // compiler error: generic types can't be instantiated directly; you never know the type it can be an Interface or abstract class
        // in which case this goes invalid. Hence java designers has this restriction
        // Type parameter 'T' cannot be instantiated directly
//        T t = new T();
        System.out.println("GenericTyped Object "+ currentobj);
    }
}

public class GenericsValidAndInvalid {
    // compiler error: we can't create array with Generic types
    // use collection instead
//    Test<String>[] tsArray = new Test<String>[10];
}
