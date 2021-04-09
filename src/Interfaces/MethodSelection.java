package Interfaces;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 1: Java Fundamentals
Topic:  Nested Class, Extras
*/

class SuperClass {
    public void doSomething() {
        System.out.println("SuperClass: doSomething");
    }
}

interface LessSpecificInterface {
    default void doSomething() {
        System.out.println("LessSpecific: doSomething");
    }
}

interface MoreSpecificInterface {
    default void doSomething() {
        System.out.println("MoreSpecific: doSomething");
    }
}

// compiler error: Interfaces.MethodSelection inherits unrelated defaults for doSomething() from types
// Interfaces.LessSpecificInterface and Interfaces.MoreSpecificInterface
//public class MethodSelection
//        implements LessSpecificInterface, MoreSpecificInterface {
//}

// interesting: note: LessSpecificInterface and MoreSpecificInterface both are unrelated hence we must override the clashed method
// doSomething() in MethodSelection class which resolves the problem
public class MethodSelection
        implements LessSpecificInterface, MoreSpecificInterface {
    public static void main(String[] args) {
        MethodSelection methodSelection = new MethodSelection();
        methodSelection.callDoSomething();
    }

    public void callDoSomething() {
        // note: syntax to call specific interface default method
        LessSpecificInterface.super.doSomething();
    }

    public void doSomething() {
    }
}