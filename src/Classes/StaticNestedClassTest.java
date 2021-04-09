package Classes;

class StaticNestedClasses {
    public static String APP_NAME = "Hello World!!";
    public String instanceVariable = "instance variable";

    public void doSomething(){
        System.out.println("Hello I am an Instance method of outer class");
        // We can call inner class non-static or static members from outer class as follows
//        new NestedClass().test();
//        var b = NestedClass.TestEnum.NAME;
    }

    public static void doSomethingStatically(){
        System.out.println("Hello I am a static method of outer class");
    }

    // Treat this as a static member of the outer class 'StaticNestedClasses'
    // As the class is static, you can only access static members of an outer class directly
    // If we need non-static members of outer class inside this nested class we have to create instance of the class
    // and can access via instance
    // This is technically called as Static Nested Class
    public static class NestedClass{
        // static nested class can contain both static and non-static fields (variables and methods)
        public static String s = "Hello";
        public String s1 = "World";
        public enum TestEnum{
            //compiler error: variables declaration in enum should be after constants
//            String s;
            NAME("Hello enum");
            String s;
            TestEnum(String s){
                this.s = s;
            }
            public String toString(){
                return this.s;
            }
        }
        // As this innerclass is static, we can have static methods inside innerclass
        public static void test2(){}

        public void test(){
            // compiler error: non-static field instanceVariable can't be referenced from a static context
//            instanceVariable+=1;
            // compiler error: Non-static method 'doSomething()' cannot be referenced from a static context
//            doSomething();

            // it is OK now
            StaticNestedClasses sn = new StaticNestedClasses();
            String s = sn.instanceVariable;
            sn.doSomething();
            // static fields can be accessed directly
            System.out.println(APP_NAME);
            doSomethingStatically();
        }
    }
}


public class StaticNestedClassTest{
    public static void main(String... args){
        var a = StaticNestedClasses.NestedClass.TestEnum.NAME;
        System.out.println(a);
        StaticNestedClasses.NestedClass n = new StaticNestedClasses.NestedClass();
        n.test();
    }
}