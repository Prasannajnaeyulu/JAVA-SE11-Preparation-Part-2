package GenericsAndCollections;

public class GenericConstructors {
    Number t;
    <T extends Number> GenericConstructors(T t){
        this.t = t;
    }

    public <T extends Double> Number addValue(T t){
        return this.t.doubleValue()+t.doubleValue();
    }

    public static void main(String... args){
        Integer i1 = 10;
        // specifying clarity type when invoking a constructor method
        // we can omit this as java compiler can infer the type based on parameter data type
        // So both of the following declarations are valid
        GenericConstructors gc1 = new <Integer>GenericConstructors(i1);
        GenericConstructors gc2 = new GenericConstructors(i1);
        Number result1 = gc1.addValue(20.0);
        // we can specify clarity type before method name are we can omit compiler is smart enough to infer the type based on
        // parameter here we are passing 10.0 which compiler automatically infers to Double
        Number result2 = gc2.<Double>addValue(10.0);
        System.out.println(result1); // 30.0
        System.out.println(result2); // 20.0
    }
}
