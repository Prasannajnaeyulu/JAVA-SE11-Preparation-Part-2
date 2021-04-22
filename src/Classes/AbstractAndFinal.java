package Classes;

// compiler error: top-level enum, class or interface can't be private or protected
// they must be either public or package-private
//private enum Test{
//}
//protected enum Test{
//}
//private class Test{
//}
//protected class Test{
//}
//private interface Test{
//}
//protected interface Test{
//}

//compiler error: top-level class,enum or interface can't be static
//static class Test{
//}
//static interface Test{
//}
//static enum Test{
//}

 // compiler error: an abstract class can't be final
//final abstract class Test{}

//compiler error: enum can't be abstract
//abstract enum Test{}

//It is valid
abstract interface Test{
}

// compiler error: Cannot return a value from a method with void result type
//void abc(){
//    return "abc";
//}


public class AbstractAndFinal {
    // Inside class we can have nested classes, interfaces or enums with any access modifier
    public class Test{}
    protected class Test1{}
    private class Test2{}
    public interface Test3{}
    protected interface Test4{}
    private interface Test5{}
    public enum Test6{
        TEST_HELLO, TEST_WORLD;
        //compiler error: enum constructors can't be protected/public they are private by default
//        protected Test6(){
//            System.out.println("Test6 enum constructor");
//        }
//        public Test6(){
//            System.out.println("Test6 enum constructor");
//        }

        // Note: interesting: constructors inside enum are by default private. Even if we specify private explicitly or not
        // they are private by default
        private Test6(){
            System.out.println("Test6 enum constructor");
        }
    }
    protected enum Test7{}
    private enum Test8{}
    static class Test9{}
    // nested interfaces and enums are by default static even we explicitly specify or not
    static interface Test10{ int test();}
    static enum Test11{}

    public static void main(String... args){
    }
}

final class Test1 implements AbstractAndFinal.Test10{

    @Override
    // Note: interesting: an overridden method can be final
    public final int test() {
        return 0;
    }
}

// compiler error: class can't extend the final class
//class Test2 extends Test1{}

class Test2 implements AbstractAndFinal.Test10{

    // compiler error: static method 'test()' in 'Classes.Test2' cannot override instance method 'test()' in 'Classes.AbstractAndFinal.Test10'
    // can't apply static for a overridden method
//    public static int test() {
//        return 0;
//    }

    @Override
    // Note: interesting: an overridden method can be final
    public final int test() {
        return 0;
    }
}

class Test3 extends Test2{
    // compiler error: can't override final method from class Test2
//    public int test() {}
//    public static int test() {}
}

abstract class Test4 implements AbstractAndFinal.Test10{
    int a;
    // abstract class constructor can be private no compiler issue here
    // but we can't extend this class unless we've another public or protected constructor
    private Test4(){}
    public Test4(int a){
        this();
        this.a = a;
    }
    protected Test4(Integer a){
        this();
        this.a = a;
    }
}

//compiler error: as abstract class Test4 default constructor is private we can't extend Test5 with Test4
// we can use abstract class other constructors to workaround
//class Test5 extends Test4{
//    @Override
//    public int test() {
//        return 0;
//    }
//}

// this is OK now
class Test6 extends Test4{
    Test6(){
        super(10);
    }

    @Override
    public int test() {
        return 0;
    }
}
