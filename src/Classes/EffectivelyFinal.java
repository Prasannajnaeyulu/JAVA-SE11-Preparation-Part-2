package Classes;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 1: Java Fundamentals
Topic:  Nested Class, local Class, effectively final review
*/

// Simple class demonstrating a local class in a method
public class EffectivelyFinal {

    public static void main(String[] args) {

        // Create a local variable and assign it a value
        int efinal = 1;

        // Local Class created with a single method that
        // uses the efinal local variable just created
        class LocalClass {
            public void printValue() {
                System.out.println("efinal = " + efinal);
            }
        }

        // Execute method on the local class
        new LocalClass().printValue();
    }
}