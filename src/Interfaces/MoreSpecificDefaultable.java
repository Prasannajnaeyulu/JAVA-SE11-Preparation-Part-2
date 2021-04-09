package Interfaces;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 3: Interfaces
Topic:  Default methods
*/

// Interface extends Defaultable, and overrides the default method
public interface MoreSpecificDefaultable extends Defaultable {
    void defaultNotAbstractMethod();
}

// Class implements both interfaces
class MoreSpecific extends DefaultClass implements
        Defaultable, MoreSpecificDefaultable {

    // implements the abstractMethod and calls the default method
    public void abstractMethod() {
        // interesting: it calls the most specific method in the inheritance hierarchy where class method outweigh
        // interface methods. Here both abstract class DefaultClass and MoreSpecificDefaultable and Defaultable have
        // concrete implementation for this but Default class treat as more specific so it calls that
        // Testing defaultNotAbstractMethod on class
        defaultNotAbstractMethod();  // Which default method is used?
    }

    public void defaultNotAbstractMethod(){
        new Defaultable(){
            @Override
            public void abstractMethod() {}
        }.defaultNotAbstractMethod();
    }

    // invokes the abstract method
    public static void main(String[] args) {
        new MoreSpecific().abstractMethod();
    }
}