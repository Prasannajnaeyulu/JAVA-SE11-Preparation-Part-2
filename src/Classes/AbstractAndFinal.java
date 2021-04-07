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

 // compiler error: an abstract class can't be final
//final abstract class Test{}

//compiler error: enum can't be abstract
//abstract enum Test{}

//It is valid
abstract interface Test{}


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
