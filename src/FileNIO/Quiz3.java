package FileNIO;

import java.io.*;

/* interesting: If the class extends another class and the parent class is not serializable
then during deserialization of child object it calls super class default no args constructor
if none available it throws the runtime error
 */
class AttributeClass1{
    String attribute;

    AttributeClass1(String attribute) {
        System.out.println("Attribute Class arg constructor: "+ attribute);
        this.attribute = attribute;
    }
    // This constructor gets called whenever child class object is deserialize
    // If this constructor do not exist then we get Exception in thread "main" java.io.InvalidClassException: FileNIO.ContainerClass1;
    // no valid constructor error on the line where we are deserializing using ObjectInputStream.readObject(..) call
    public AttributeClass1() {
        this("unknown");
        System.out.println("Attribute Class zero arg constructor");
    }

    public String toString() {
        return attribute;
    }
}

class ContainerClass1 extends AttributeClass1 implements Serializable {
    String name;

    ContainerClass1(String name, String attribute) {
        super(attribute);
        this.name = name;
        System.out.println("Container Class constructor: "+ attribute);
//        this.feature = new AttributeClass1(attribute);
    }

}

public class Quiz3 {
    public static void main(String[] args) throws Exception {
        File file = new File("ralph.ser");
        ContainerClass1 ralph = new ContainerClass1("Ralph", "short");
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(ralph);

        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {
            ralph = (ContainerClass1) inputStream.readObject();
        }

        // When an object is deserialize and it’s class is serializable, it does not get constructed with the class’s constructor.
        // State is restored by reading data from the ObjectInputStream for the individual fields and making assignments to the
        // appropriate fields of the object. If the superclass is not serializable then it calls super class zero args constructor
        // as part of serialization; if no super class zero args. constructor is available then it is a run time error while readingObject
        System.out.println(ralph.name + " is " + ralph.attribute); // Ralph is unknown
    }
}

