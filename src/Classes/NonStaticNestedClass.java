package Classes;


class EnclosingClass{
    public String outerName;
    public InnerClass innnerclass = new InnerClass();
        
    public void doSomething(){
        System.out.println("Outer Instance method");
    }

    EnclosingClass(String text){
        this.outerName = text;
    }

    //non-static inner class
    // this is technically called as InnerMemberClass
    class InnerClass {
        // only non-static variables and methods are allowed inside a non-static Inner Class
        public String innerName = "Inner class instance variable";
        public String outerName = "Shadow outerclass variable from inner class";

        //compiler error: non-static inner class can't have static methods or static variables
        // except constant variables ( public static final is allowed )
//        public static String getName(){
//            return " Hello Static";
//        }
        // compiler error: innerClass can't have static declarations
//        public static String test = "Hello";

        // This is treated as constant so it is allowed
        public static final String test = "Hello";
        // compiler error:  innerClass can't have static declarations
        // As we know enum and interface inside an innerclass is by default static so static declarations not allowed inside
        // innerclass
//        public enum DAYS{
//            MON, TUE;
//        }
//        public interface inttface1{}

        public void doSomething(){
            System.out.println("Inner class Instance method");
        }
        // As the class is non-static we can access outerclass (EnclosingClass) fields directly
        public String getOuterName(){
            // interesting: this is how to access outerclass instance variable from an innerclass instance method in the case of
            // innerclass also has the same variable name which is shadowing the outer class variable
              System.out.println("Outer class Instance variable: "+ EnclosingClass.this.outerName);

            // To access Innerclass instance variable we use this or we can omit 'this' altogether
            // As in the following two examples:
            System.out.println(this.outerName);
            return outerName;
        }

        public String getInnerName(){
            return innerName;
        }
    }
}

public class NonStaticNestedClass {
    public static void main(String... args){
        // compiler error: Syntax error
//        EnclosingClass.innerClass in = new EnclosingClass.innerClass();
//        EnclosingClass.innerClass in = EnclosingClass.new innerClass();

        EnclosingClass ec = new EnclosingClass("e's instance");
        // the following are correct syntax
        EnclosingClass.InnerClass in1 = new EnclosingClass("f's instance").new InnerClass();
        EnclosingClass.InnerClass in2 = ec.new InnerClass();

        in2.doSomething();
//        in2.doSomething();
//        System.out.println(in2.innerName);
        // both calls below prints "Shadow outerclass variable from inner class". The getOuterName returning outerName
        // instance variable of innerClass which is actually shadowing(hiding) the outerclass variable
        System.out.println(in1.getOuterName());
        System.out.println(in2.getOuterName());

        // compiler error: InnerClass is non-static so can't access inner class methods using direct className
//        ec.InnerClass.getInnerName();

        // this is ok innerclass here is an instance variable in Outer class which is pointing to an object of InnerClass
        ec.innnerclass.innerName = "Hello";
        System.out.println("Inner class constant: "+ ec.innnerclass.test);
        System.out.println(ec.innnerclass.getInnerName());
    }
}
