package GenericsAndCollections;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 4: Generics and Collections
Topic:  Generics, Confusing type parameters
*/

// class Container, a generic class with one type parameter
// interesting: here Number is a placeholder of parameter type it is not java.lang.Number don't confuse :)
// it can be treated as Container<T> just to confuse they used Number in place of T
class Container<Number> {

    // instance variable
    Number myNumber;

    // Constructor
    Container(Number myNumber) {
        this.myNumber = myNumber;
        System.out.println(this);
    }

    // overridden toString() method
    public String toString() {
        return myNumber.toString();
    }

}

public class GenericsConfusion {
    public static void main(String[] args) {
        // Declare Container with String type argument
        Container<String> c1 = new Container<>("Hello");

        // Declare Container with String type argument
        Container<Integer[]> c2 = new Container<>(new Integer[]{1, 2, 3, 4});

        // Declare Container with UnknownError type argument
        Container<UnknownError> c3 =
                new Container<>(new UnknownError("Testing"));

        CardboardContainer<String> n1 =
                new CardboardContainer<String>("Hello");

        CardboardContainer<String> n2 =
                new CardboardContainer<>(3);

        // compiler error: when using explicit argument to a constructor like '<Integer>' used here, we can't omit class parameter type
        // I mean we shouldn't diamond operator
//        CardboardContainer<String> n3 =
//                new <Integer>CardboardContainer<>(3);

        // it is ok now
        CardboardContainer<String> n3 =
                new <Integer>CardboardContainer<String>(3);

        // compiler error: can't resolve constructor CardboardContainer(java.lang.Integer)
        // don't know yet why it is rejected
//        CardboardContainer<Number> cc = new <Number>CardboardContainer<Number>(Integer.valueOf(10));
//        CardboardContainer<String> cc = new CardboardContainer<String>("Hello");
    }
}

class CardboardContainer<T> {
    T myField;

    // compiler error: CardboardContainer(K)' clashes with 'CardboardContainer(T)'; both methods have same erasure
    // think of this scenario as T type and K type might be same in which case we end up having two constructors with same type
    // say, this class instantiated as follows. In this case both T and K are equal so we end up having two constructors
    // compiler therefore considering T and K as same type erasures hence the error
    // very Important: it would be allowed if K extends something like '<K extends Number> CardboardContainer(K myField) {}'
    // CardboardContainer<Integer> i = new <Integer>CardboardContainer<Integer>();
//    <K> CardboardContainer(K myField) {}

    CardboardContainer(T myField) {
        System.out.println("In T constructor");
        this.myField = myField;
    }

    // note: interesting: this is OK. because we are explicitly stating 'V extends Number'. So 'V' in constructor type treated as
    // Number type so it ideally equals to 'CardboardContainer(Number myField)'
    <V extends Number> CardboardContainer(V myField) {
        System.out.println("In T2 constructor");
        // compiler error: the Type of this.myField is 'T' but the type of 'myField' here is 'V' both are different
        // so it is a compiler error
//        this.myField = myField;
    }
}