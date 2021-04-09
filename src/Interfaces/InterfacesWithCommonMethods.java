package Interfaces;

public class InterfacesWithCommonMethods {
}

interface Walkable{
    void walk();
    default float getRate(){
        return 2.3f;
    }
}

interface Runnable{
    void run();
    default float getRate(){
        return 4.3f;
    }
}

interface Test1 {
    default void myMethod() {
        System.out.println("Do this");
    }
}

abstract class Test implements Test1{
    // compiler error: attempting to assign weaker access privileges
//    abstract void myMethod();

    // it is valid the overriden method can be static or final but it must have public access modifier
    public static final void methodA(){}

    // interesting: the default method of an interface can override in implementing class and can mark as
    // static or abstract or final but the access modifier must be public always as we can't assign weaker modifier
    public abstract void myMethod();
}