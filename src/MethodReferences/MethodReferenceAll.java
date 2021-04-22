package MethodReferences;

interface SaySomething{
    void say();
}

public class MethodReferenceAll {
    static void greet(){
        System.out.println("Hello Welcome to the world of dealing Functional Interfaces with method reference");
    }

    void greetInstance(){
        System.out.println("Greeting from an Instance");
    }

    private static String greetDifferent(){
        System.out.println("Greeting differently");
        return "Hello";
    }

    private static String greetDifferent_V2() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Greeting differently");
        return "Hello";
    }

    public static void main(String... args){
        // interesting: Any method that matches to the signature of a functional interface abstract method
        // here greet matches to the signature of say function in an interface. I mean both takes
        // no args. So the greet method can be substituted as a lambda expression which simulates the behavior of
        // implementing the interface SaySomething so we can assign this to SaySomething reference
        SaySomething s = MethodReferenceAll::greet;
        s.say();

        // interesting: method signature match means both are zero args methods that's it don't care of access specifier
        // or return type
        SaySomething s1 = MethodReferenceAll::greetDifferent;
        s1.say();

        // same as above but this time an instance method matches to the signature of interface abstract method
        // so we can use this as an alternate to lambda expression
        SaySomething s2 = new MethodReferenceAll()::greetInstance;
        s2.say();

        // this time using a lambda expression which has anonymous method definition for interface abstract method say
        SaySomething s3 = () -> System.out.println("Hello greeting from lamnda");
        s3.say();

        // compiler error: unhandled exception. Here 'say()' method from interface doesn't declare with any throw clause
        // hence using method reference that throws exception violates method signature
        // Note: even try catch around the following statement doesn't work
//        s3 = MethodReferenceAll::greetDifferent_V2;
    }
}
