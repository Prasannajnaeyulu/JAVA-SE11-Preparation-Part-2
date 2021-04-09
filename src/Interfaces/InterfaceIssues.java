package Interfaces;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 3: Interfaces
Topic:  Limitations of default methods
*/

import java.util.Objects;

// This interface demonstrates overriding and declaring Object's 3
// public methods abstract
interface ProblemFreeInterface {

    // abstract methods overriding Object's non-private, non-final
    // methods is ok
    String toString();

    boolean equals(Object obj);

    int hashCode();

    public Object clone() throws CloneNotSupportedException;
}

interface ProblematicInterface {
    // Note: this is public by default
    default Object clone() throws CloneNotSupportedException {
        System.out.println("Default method CAN override clone()");
        return null;
    }
}

interface extendedProblem extends ProblematicInterface{
    // compiler error: very interesting
    // Static method 'clone()' in 'Interfaces.extendedProblem' cannot override instance method 'clone()' in
    // 'Interfaces.ProblematicInterface'
    // Note: it doesn't matter whether clone() in super interface is default or abstract. This rule applies to both because
    // both are instance methods of an interface
//    private static Object clone(){}

    // compiler error: Static method 'clone()' in 'Interfaces.extendedProblem' cannot override instance method 'clone()' in
    //  'Interfaces.ProblematicInterface'
    // Note: it doesn't matter whether clone() in super interface is default or abstract. This rule applies to both because
    // both are instance methods of an interface
//    static Object clone(){}

    static void abc(){}
    // this is OK but can't be static as in the case above
    default Object clone(){ return new String("Hello"); }
}

// This class implements interface above and implements concrete
// methods overriding the 3 public methods
// compiler error: clone()' in 'java.lang.Object' clashes with 'clone()' in 'Interfaces.ProblemFreeInterface';
// attempting to assign weaker access privileges ('protected'); was 'public'
// as we know java.lang.Object is the default class for every class in java, the clone() method from class hierarchy
// takes precedence over interface methods if methods are equal. But here interface method is public but Object class
// clone method is protected. So protected is weaker than public hence the error
//class ProblemFreeClass implements ProblemFreeInterface {}

// It is Ok If we override the clone method from an interface here with public access modifier
class ProblemFreeClass implements ProblemFreeInterface {
    public int id;

    // Constructor
    ProblemFreeClass(int id) {
        this.id = id;
    }
    // by overriding we can prevent clash of this method from java.lang.Object and the Interface
    public Object clone() throws CloneNotSupportedException {
        System.out.println("A Class CAN override clone()");
        return null;
    }

    public String toString() {
        System.out.println("A Class CAN override toString()");
        return "\tProblemFreeClass{" +
                "id=" + id +
                '}';
    }

    public boolean equals(Object o) {
        System.out.println("A Class CAN override equals()");
        if (this == o) return true;
        if (!(o instanceof ProblemFreeClass)) return false;
        ProblemFreeClass that = (ProblemFreeClass) o;
        return id == that.id;
    }

    public int hashCode() {
        System.out.println("A Class CAN override hashCode()");
        return Objects.hash(id);
    }
}

// This class extends the ProblemFreeClass, demonstrating calling overloaded
// versions of equals(), hashCode() and toString().
// Note: here interface ProblematicInterface has its own version of clone() method implemented as default method
// this will clash with java.lang.Object class in access modifier and it throws weaker access issue
// but we overridden the clone() method in ProblemFreeClass class so that gets the priority hence no error here
public class InterfaceIssues extends ProblemFreeClass
        implements ProblematicInterface {

    // Constructor
    InterfaceIssues(int id) {
        super(id);
    }

    public static void main(String[] args) {
        InterfaceIssues it = new InterfaceIssues(1);

        ProblemFreeClass it2 = null;
        try {
            it2 = (ProblemFreeClass) it.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        boolean isEquals = it.equals(it2);
        System.out.println("\thashCode = " + it.hashCode());
        System.out.println(it);

    }
}
