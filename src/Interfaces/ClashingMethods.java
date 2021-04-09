package Interfaces;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 3: Interfaces
Topic:  Method clashing
*/

interface FirstInterface {
    default void methodA() {
        System.out.println("FirstInterface's default method A");
    }
    static void methodB(){
        System.out.println("SecondInterface's default method A");
    }
}

interface SecondInterface {
    // static (and public) method
    default void methodA() {
        System.out.println("SecondInterface's default method A");
    }
}
public class ClashingMethods implements FirstInterface {
    public static void main(String[] args) {
        ClashingMethods first = new ClashingMethods();
        // Calling static method on an interface
        first.methodA();
        // compiler error: Static method may be invoked on containing interface class only
        // we can't invoke interface static method directly we must use explicit interface name prefix
//        first.methodB();
        // it is valid
        FirstInterface.methodB();

    }
}
