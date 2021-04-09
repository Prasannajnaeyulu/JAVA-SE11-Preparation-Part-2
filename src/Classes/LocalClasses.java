package Classes;

public class LocalClasses {
    private String defaultname="Jane";
    private String lastName = "Dow";
    private void doSomething(){
        // compiler error: modifiers private, public and protected not allowed inside a method body
//        private String name = "something";
//        public String name1 = "something";
//        protected String name2 = "something";

        //compiler error: modifier public, private and protected not allowed inside a method body
//        public class LocalClass1{}
//        private class LocalClass2{}
//        protected class LocalClass3{}

        String defaultname = "Ralph";
        if(this.defaultname !=null)
            defaultname = this.defaultname;

        class LocalClass{
            String name;
            LocalClass(String name){
                if(name !=null)
                    this.name = name;
                else this.name += lastName;
                // compiler error: lastName of an outer class is not accessible via this access directly
                // if any variable is shadowed then access via '<Outerclassname>.this.<variablename>'
//                this.name += this.lastName; //invalid
                this.name += lastName; // valid
                this.name+= LocalClasses.this.lastName; // valid
                // compiler error: variable 'defaultname' accessed from within a LocalClass should be final or
                // effectively final
                // Note: this rule is only applicable to LocalClass ( A class declared within some function body) but not to
                // Class Member classes like 'Static Nested Class' or 'Inner Class' where the classes are inside another class
                // as class member
//                else this.name = defaultname; // invalid defaultname is not effectively final
            }
            public String toString(){
                return this.name;
            }
            // compiler error: Local Inner classes can't have static references
//            static void method1();
            // compiler error: Local Inner classes can't have static references
            // Note: enum and interface are by default static
//            public enum test{};
//            public interface intf1{}


        }

        System.out.println(new LocalClass("Anji"));
        System.out.println(new LocalClass("Mark"));
    }

    public static void main(String... args){
        new LocalClasses().doSomething();
    }
}
