package MethodReferences;

interface MyInerface{
    String doSomething(String s);
}

public class MethodReferenceExample {

//    public String doSomething(String s){
//        return s;
//    }

    public static void main(String... args){
        MyInerface i1 = (s) -> s;
        executeOnInterface(i1);

        // compiler error: Method reference should match to the signature of the abstract method that is doSomething
        // which takes one string argument but we are providing Interface type as argument instead hence the error
        // Bad return type in method reference: cannot convert MethodReferences.MyInerface to java.lang.String
//        executeOnInterface(MethodReferenceExample::getMyInterface);

        // But this is OK calling the method directly without method reference
        executeOnInterface(MethodReferenceExample.getMyInterface());
    }

    static MyInerface getMyInterface(){
        MyInerface i = s -> s;
        return i;
    }

    static void executeOnInterface(MyInerface i){
        System.out.println(i.doSomething("Hello"));
    }
}
