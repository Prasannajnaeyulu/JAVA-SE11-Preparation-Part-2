package FileNIO;

import java.io.*;

public class ConsoleReadWrite {
    public static void main(String... args) throws IOException {
        Console console = System.console();
        if(console!=null) {
            console.flush();
            console.writer().write("What's your name?");
            //        System.out.println("What's your name?");
            String name = console.readLine();
            console.writer().write(String.format("Thanks for your Input, %1s", name));
//            console.writer().write("Thanks for your Input, %1s".format(name));
        }
        else{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("What's your name?");
            String name = reader.readLine();
            System.out.println(String.format("Thanks for your Input, %1s", name));
        }
    }
}
