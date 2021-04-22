package FileNIO;

//Good job!
//        Correct Answer: When an object is deserialized and it’s class is serializable, it does not get constructed with the
// class’s constructor. State is restored by reading data from the ObjectInputStream for the individual fields and making
// assignments to the appropriate fields of the object. The transient field is ignored, and never gets instantiated.
//
//        Question 1:
//        Given:

import java.io.*;

/*
Note: interesting:
1. If the class is serializable and all other classes which are instance variables inside a class ( i.e., has a relationship)
If those instance classes are also serializable then both will persist during serialization and will restore successfully
without constructing any new objects as part of deserialization

2. If we declare instance variable class as transient then it won't serialize. After deserialization it gets default assignment
that is 'null'

3. If the instance variable class is neither serializable nor we declare the instance class reference as transient then
we get Runtime error: Not serializable exception

 */
class AttributeClass {
    String attribute;

    AttributeClass(String attribute) {
        System.out.println("Attribute Class arg constructor: "+ attribute);
        this.attribute = attribute;
    }

    public AttributeClass() {
        this("unknown");
        System.out.println("Attribute Class zero arg constructor");
    }

    public String toString() {
        return attribute;
    }
}

class ContainerClass implements Serializable {
    String name;
    transient AttributeClass feature = new AttributeClass("tall");

    ContainerClass(String name, String attribute) {
        System.out.println("Container Classs constructor");
        this.name = name;
        this.feature = new AttributeClass(attribute);
    }

}

public class Quiz2 {
    public static void main(String[] args) throws Exception {
        File file = new File("ralph.ser");
        ContainerClass ralph = new ContainerClass("Ralph", "short");
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(ralph);

        }

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new FileInputStream(file))) {
            ralph = (ContainerClass) inputStream.readObject();
        }

        // When an object is deserialized and it’s class is serializable, it does not get constructed with the class’s constructor.
        // State is restored by reading data from the ObjectInputStream for the individual fields and making assignments to the
        // appropriate fields of the object. The transient field is ignored, and never gets instantiated.
        System.out.println(ralph.name + " is " + ralph.feature); // Ralph is null
    }
}
