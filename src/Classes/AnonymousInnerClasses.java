package Classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnonymousInnerClasses {
    interface Test{
        String name = "Person X";
        default void print(){
            System.out.println("Hello "+ name+ " Welcome!!");
        }

        void print(String name);
    }

    public void print(String name, Test t){
        t.print();
        t.print(name);
    }

    public static void main(String... args){
        AnonymousInnerClasses a = new AnonymousInnerClasses();
        // interesting: compiler error: Anonymous class implements interface; cannot have qualifier for new
//        a.print("Anji", a.new Test() {
//                    @Override
//                    public void print(String name) {
//                    }
//        });

        a.print("Anji", new AnonymousInnerClasses.Test(){
            // non-static fields are allowed
            String s = "abc";
            // compiler error: inner classes can't have static declarations except constants
//            static String s = "12";
//            enum enum1{};
            // it is valid
            public static final String s2 = "cde";

            @Override
            public void print(String name) {
                System.out.println("My Own name: "+ name);
            }

            public void printNew(String name) {
                System.out.println("Your branded name: "+ name+ " Smart");
            }
        });

        AnonymousInnerClasses.Test t1 = new AnonymousInnerClasses.Test(){
            // compiler error: constructors are not allowed in anonymous inner class
//            Test(){}
            // non-static fields are allowed
            String s = "abc";
            // compiler error: inner classes can't have static declarations except constants
//            static String s = "12";
//            enum enum1{};
            // it is valid
            public static final String s2 = "cde";

            @Override
            public void print(String name) {
                System.out.println("My Own name: "+ name);
            }

            public void printNew(String name) {
                System.out.println("Your branded name: "+ name+ " Smart");
            }
        };

        // Anonymous inner classes are not limited to just abstract class or interface types
        // we can create create Anonymous inner class for concrete types too like Object class
        Object o = new Object(){
            public String toString(){
                return "Hello I am an anonymous inner class for concrete class";
            }
        };

        // compiler error: java.lang.String class is final. final classes can't be extended
        // so we can't create subclass
//        java.lang.String s1 = new java.lang.String("Hello"){
//        }

        // My own String class don't confuse with java.lang.String class :)
        abstract class String{
            CharSequence str;
            String(CharSequence str){
                this.str=  str;
            }
            public abstract java.lang.String print();
        }
        // Note: this is our abstract String class created above don't confuse with java.lang.String
        String s1 = new String("Hello new String class"){
            public java.lang.String print(){
                try {
                    Method m = this.str.getClass().getMethod("toString");
                    return (java.lang.String)m.invoke(this.str);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        String s2 = new String(new StringBuilder("Hello new String class from String Builder")){
            public java.lang.String print(){
                try {
                    Method m = this.str.getClass().getMethod("toString");
                    return (java.lang.String)m.invoke(this.str);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        System.out.println(o.toString());
        System.out.println(s1.print());
        System.out.println(s2.print());

        // compiler error: can't resolve the method 'printNew'
        // though we create this method in an anonymous class body, it is not available with AnonymousInnerClasses.Test type
        // you can only access methods declared inside AnonymousInnerClasses.Test type
        // as per polymorphism if you call method with parent class reference it checks for the method in parent class first
        // if it is not available it throws compiler error
//        t1.printNew("Anji");

    }
}
