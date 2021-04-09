package Enums;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 1: Java Fundamentals
Topic:  Enum Extras
*/

interface intf1{
    public void print();
    // compiler error: Default method 'toString' overrides a member of 'java.lang.Object'
    // we can't override any default method of java.lang.Object class in an interface as a default method such as hashcode(), equals() etc..
//    default String toString(){}

    // note: we can override clone as it is a protected method in java.lang.Object class but not a public method
    // Also finalize() method from java.lang.Object is protected so we can use that name as well for default method
    // so it is Ok.
//     default Object clone(){
//         return new java.lang.Object(){
//             @Override
//             protected Object clone() throws CloneNotSupportedException {
//                 return super.clone();
//             }
//
//             @Override
//             protected void finalize() throws Throwable {
//                 super.finalize();
//             }
//         };
//     }

        // interesting: we can have abstract methods in an interface matches to java.lang.Object
        // only default methods should not have these names but abstract methods it is OK
        // Any class that implements this interface may or may not implement these because all classes are by default
        // inherited from java.lang.Object class so these methods are inherently implemented :)
        String toString();
        int hashCode();
        boolean equals(Object o);
}

// compiler error: enums can't qualify with final
//final enum DAYS{
//        SUN,  MON, TUE, WED, THU, FRI, SAT;
// }

enum PrimaryColor implements intf1{
    RED(1) {
        {
            // initializer for the individual class constants
            System.out.println(this + " initialized");
        }
    },
    BLUE(2) {

        {
            // initializer for the individual class constants
            System.out.println(this + " initialized");
        }
    },
    YELLOW(3) {
        {
            // initializer for the individual class constants
            System.out.println(this + " initialized");
        }
    };

    int rating = 0;

    // static initializer for the PrimaryColor class (enum)
    // this executes last
    static {
        System.out.println("Enum Class Initialization");
    }

    // interesting: for enums this calls first and then constructor and then enum constant anonymous methods
    // initialization block and at last static initializer block
    // initializer for all of the anonymous class constants
    {
        System.out.println("Enum Body Initialization for " + this);
    }

    // Constructor
    PrimaryColor(int rating) {
        System.out.println("Primary Color constructor for " + this);
        this.rating = rating;
    }
    public void print(){
        System.out.println("Hello Interface I implemented you!!");
    }

    // compiler error: clone() method in java.lang.enum is final so we can't override
//    public Object clone() {}
}

public class EnumExtras {
    public static void main(String[] args) {
        /*
        Enum Body Initialization for RED
        Primary Color constructor for RED
        RED initialized
        Enum Body Initialization for BLUE
        Primary Color constructor for BLUE
        BLUE initialized
        Enum Body Initialization for YELLOW
        Primary Color constructor for YELLOW
        YELLOW initialized
        Enum Class Initialization
        RED
        BLUE
        YELLOW
         */
        System.out.println(PrimaryColor.RED);
        System.out.println(PrimaryColor.BLUE);
        System.out.println(PrimaryColor.YELLOW);
    }
}