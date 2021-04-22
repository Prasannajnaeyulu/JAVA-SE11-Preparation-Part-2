package FileNIO;

import java.io.*;
import java.util.Random;

public class DataStreams {
    public static void main(String... args) throws IOException {
//        DataOutputStream dos = new DataOutputStream(System.out);
//        dos.flush();
        // compiler error: dos::write throws IOException where as accept method in consumer is not
        // hence method reference doesn't work here.
//        new Random().ints(1, 1000).forEach(dos::write);

        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("test.txt"))) {
            new Random().ints(1, 1000).limit(10).forEach(i -> {
                try {
//                    dos.writeInt(i);
                    dos.writeUTF("Anji"+ i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            dos.flush();
        }

//        try(DataInputStream dis = new DataInputStream(System.in)){
//            System.out.println("What's your name?");
//            String name = dis.readUTF();
//            System.out.println("Hello Mr. "+ name);
//        }

        try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("test.txt")))){
            String name = dis.readUTF() ;
            System.out.println("Hello Mr. "+ name);
        }
    }
}
