package GenericsAndCollections;

// compiler error: generic type should only have one Class type but can have many interfaces
// But here both Number and Object are class types which is invalid after & it expects interface types
//class GenericTypedNumber<T extends Number & Object>{
//}

// Generic Type can be specified with an upper bound it must be a class or interface or both
// 1. If it is a Class
// Here it allows only Number or its subtypes as class parameter types. Number is the upperbound
// Note: we can access Number class methods on T
class GenericTypedNumber<T extends Number>{
    T t;
    GenericTypedNumber(T t){
        this.t = t;
    }

    public void test(){
        // we can call number type methods on a reference of type 'T'
        t.byteValue();
        t.doubleValue();
    }
}

// 2. If  it is an Interface
// Here it allows only subtypes of Comparable as class parameter types. Comparable is the upperbound
// we can access Comparable interface type methods on T
class GenericTypedComparableTypes<T extends Comparable>{
    T t;
    GenericTypedComparableTypes(T t){
        this.t = t;
    }
    public void test(T t1){
        t.compareTo(t1);
    }
}

// we can access comparable interface type methods on T
class GenericTypedSpecificComparableTypes<T extends Comparable<Integer>>{
    T t;
    GenericTypedSpecificComparableTypes(T t){
        this.t = t;
    }
    public void test(T t1){
        // compiler error: compareTo(java.lang.Integer) can't be applied to type T
//        t.compareTo(t1);
        if(t1 instanceof Integer) {
            t.compareTo((Integer) t1);
        }
    }
}

// 3. If it is both class and implements an Interface
// Here it allows only subtypes that are extended Number & implements Comparable as class parameter types
// Note: In this case, Class Type should be the first after extends
// we can access both Number class and comparable interface types methods on T
class GenericTypedBoth<T extends Number & Comparable>{
    T t;
    GenericTypedBoth(T t){this.t = t;}
    public void test(T t1){
        t.compareTo(t1);
    }
}

// 4. If T is a class or an Interface
// this class has two parameter types. first parameter type must be a Number or its subtype.
// interesting: The second parameter type here we used Comparable one of built in types to confuse but this basically
// treated as a Type variable Ideally it could be anything T,U, V etc.. we mentioned here as Comparable to confuse but it
// it not java.lang.Comparable it is just a placeholder of second parameter type of a class ; keep in mind crazy :)
// example: GenericTypedExtendsTwoTypes<Number, Number> t;
// example: GenericTypedExtendsTwoTypes<Number, String> t;
// example: GenericTypedExtendsTwoTypes<Number, Integer> t;
// example: GenericTypedExtendsTwoTypes<Number, YourOWnObject> t;
// It just extends one Class Number second one is just a class parameter
class GenericTypedExtendsTwoTypes<T extends Number, Comparable>{
    T t;
    // this is not java.lang.Comparable it is just a type variable or placeholder of second type of class parameter
    // So it takes anything
    Comparable c;
    GenericTypedExtendsTwoTypes(T t, Comparable c){
        this.t = t;
        this.c = c;
    }

    public <T extends GenericTypedExtendsTwoTypes> void test(T t2){
        // compiler error: Cannot resolve method 'compareTo(T)'
        // 'T' here is a Number or its subtype
        // But not a type or subtype of Comparable it can be of any type so compareTo is not resolved
//        t.compareTo(t1);

        // interesting: we can access Number class methods directly but interface type methods we can't
        t.doubleValue();
        t.byteValue();
    }
}

// compiler error: Cannot inherit from final 'java.lang.Integer'
//class IntegerExtended extends Integer{}


public class GenericWithUpperBound {
    public static void main(String... args){
        // compiler error: Wrong number of type arguments: 1; required: 2
//        GenericTypedEitherOR<MyComparable> t;
        GenericTypedExtendsTwoTypes<Number, String> t1 = new GenericTypedExtendsTwoTypes<>(Integer.valueOf(10), "Hello");
        GenericTypedExtendsTwoTypes<Number, String> t2 = new GenericTypedExtendsTwoTypes<>(Integer.valueOf(10), "Welcome");
        t1.test(t2);
    }
}
