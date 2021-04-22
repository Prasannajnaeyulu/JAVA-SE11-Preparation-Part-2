package GenericsAndCollections;

public class WrapperClassMethods {
    public static void main(String... args){
        System.out.println(Integer.parseInt("10")); // 10
        System.out.println(Boolean.parseBoolean("True")); // true
        // interesting: the getInteger(), getBoolean() methods here takes property key string as an argument
        System.out.println(Integer.getInteger("10")); // null
        System.out.println(Boolean.getBoolean("True")); // false

        System.setProperty("app.debug", "true"); // it can be "true" or "True" both maps to boolean 'true'
        System.setProperty("app.version", "10");

        System.out.println("Property app.version: "+ Integer.getInteger("app.version")); // 10
        System.out.println("Property app.debug: "+ Boolean.getBoolean("app.debug")); // true
    }
}
