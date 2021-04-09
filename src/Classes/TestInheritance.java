package Classes;

/*
The Learn Programming Academy
Java SE 11 Developer 1Z0-819 OCP Course - Part 2
Section 1: Java Fundamentals
Topic:  Nested Class, Extras
*/

// interesting: compiler error: No enclosing instance of type 'Classes.OuterMost' is in scope
// we can't extend inner classes of outermost class directly in top-level class
// but it is possible to extend in inner member classes created in this class as shown in Line#51
//class TestInheritance1 extends OuterMost.PublicInner {
//}

public class TestInheritance extends OuterMost {
    public static void main(String[] args) {
        // compiler error: classes can't be accessed directly in a static context
        // but we can access like these in non-static context
//        new ProtectedInner();
//        new PublicInner();

        // It is OK if we access like this in a static method
//        new OuterMost().new ProtectedInner();
//        new OuterMost().new PublicInner();
        new TestInheritance().testInnerClassMembers();
    }

    private void testInnerClassMembers() {
        // non-static method - instance of current class exists so inner
        // classes (with the right access modifiers) are available...
        // we can access inner classes directly without explicitly specifying outerclass.innerclass syntax
        // because these are available to TestInheritance via Inheritance of OuterMost
        new ProtectedInner();
        // First Level
        new PublicInner();

        this.new ProtectedInner();
        // First Level
        this.new PublicInner();

        new OuterMost.ProtectedInner();
        // First Level
        new OuterMost.PublicInner();

        // Second Level
        new PublicInner().new NestedInnerSecondLevel();

        // Customized Second Level
        new KeepExtending();
        // compiler error: Classes.TestInheritance.KeepExtending' is not an enclosing class
        // use of <OuterClassName>.<innerclassName> to create objects is only valid inside a non-static context
        // provided <OuterClassName> is an enclosing class (outer most class in the hierarchy i.e., 'TestInheritance' and
        //                                                                              'OuterMost' classes in out example)
//        new KeepExtending.ExtendingFurther();
        new KeepExtending().new ExtendingFurther();
    }

    // This inner class extends the inner class that was inherited
    // from the OuterMost class
    class KeepExtending extends OuterMost.PublicInner {
        KeepExtending() {
            System.out.println("Extend the inner class as an " +
                    "inherited member");
        }

        class ExtendingFurther extends
                OuterMost.PublicInner.NestedInnerSecondLevel {
            ExtendingFurther() {
                System.out.println("Extending a deeper level of " +
                        "an inherited member");
            }
        }
    }
}