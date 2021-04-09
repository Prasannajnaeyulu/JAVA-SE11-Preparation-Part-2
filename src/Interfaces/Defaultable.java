package Interfaces;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 3: Interfaces
Topic:  Default methods
*/

public interface Defaultable {
    // note: all classes and enums inside an interface are public static by default
    public static enum abc{

    }
    public static class abcd{
    }

    // this method is public by default
    static void print(){
        System.out.println("Defaulatable public static method");
    }

    // compiler error: Interface abstract methods cannot have body
    // any concrete method in an interface should be treated as abstract unless we specify default before the method
//    void greet(String message){
//        System.out.println(message);
//    }

    // it is valid now
    default void greet(String message){
        System.out.println(message);
    }

    private static void printprivate(){
        System.out.println("Defaulatable private static method");
    }

    String abstractValue = "ABSTRACT";

    void abstractMethod();

    default void defaultNotAbstractMethod() {
        System.out.println("Testing Default");
    }

}

// Class implements Defaultable interface
class ImplementingClass implements Defaultable {
    public void abstractMethod() {
        System.out.println("ImplementingClass" +
                " implements interface's abstract method");

        // interesting: compiler error: Static method may be invoked on containing interface class only
        // We can't refer interface public static methods without interface name prefix
//        print();

        // It is valid now
        Defaultable.print();

        // compiler error: Static method may be invoked on containing interface class only
        // but the following is valid to access non-static methods but accessing static methods with below syntax
        // doesn't work compiler will throw the error when we try to access static methods as follows
//        Defaultable.super.print();
    }

    // compiler error: it overrides default method in an interface so the access modifier shouldn't be weaker
    public void greet(String msg){
        // note: this is how to call non-static default method of an interface from a non-static method of implementing class
        Defaultable.super.greet(msg);
    }

    static void test(){
        // interesting: compiler error: Static method may be invoked on containing interface class only
        // We can't refer interface public static methods without interface name prefix even in a static context
//        print();

        //it is valid now
        Defaultable.print();
    }
}

// Class which uses the interface in a method as a parameter type.
class DefaultClass extends BaseClass implements Defaultable {

    public static void main(String[] args) {
        // Instantiate the current class.
        DefaultClass dc = new DefaultClass();

        // Pass instance of implementing class to method using interface
        dc.callAbstractMethod(new ImplementingClass());

        // Pass lambda expression to method using interface
        dc.callAbstractMethod(() -> System.out.println("Lambda Expression" +
                " implements interface's abstract method"));

        // Call method directly..
        dc.abstractMethod();

    }

    // Method has a parameter of type Defaultable
    public void callAbstractMethod(Defaultable d) {
        // Execute implementing object's abstractMethod
        d.abstractMethod();
    }

    public void abstractMethod() {
        // Which method is this from (interface or BaseClass?)
        defaultNotAbstractMethod();
    }
}

// Create a base class that has method with same signature default
// method in interface
class BaseClass {
    public void defaultNotAbstractMethod() {
        System.out.println("Testing defaultNotAbstractMethod on class");
    }
}