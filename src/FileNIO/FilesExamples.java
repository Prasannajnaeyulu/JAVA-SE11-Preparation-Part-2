package FileNIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.util.List;

public class FilesExamples {
    public static void main(String... args) throws IOException {
        // Assume file exists and it has a content "Welcome to the java programming"
        Path tmp = Paths.get("temp.txt");
        // If the file already exists and if we don't specify APPEND option with the write method then it replaces the
        // content of the file from the head ( begining ) which basically corrupt the file say if file already exists
        // and it has content "Welcome to the java programming"
        // Then this first write of Hello overwrites the file and left the content as
        // Hellome to the java programming
        // subsequent writes replace each character in the file accordingly unless we specify APPEND Option
        Files.write(tmp, "Hello".getBytes(), StandardOpenOption.CREATE); //Hellome to the java programming
        // This will append to the end of the file instead of corrupting
        Files.write(tmp, "World".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND); //Hellome to the java programmingWorld
        // This will write to the head ( beginning ) of the file which again corrupt the file
        Files.write(tmp, "World".getBytes(), StandardOpenOption.CREATE); // Worldme to the java programmingWorld

        System.out.println(Files.readAllLines(tmp));

//        // compiler error: write method will only take bytes to write
//        Files.write(tmp, "Hello World", StandardOpenOption.CREATE_NEW, StandardOpenOption.TRUNCATE_EXISTING);

//         Runtime error: File already exists java.nio.file.FileAlreadyExistsException: temp.txt
        // If the file exists then CREATE_NEW throw file already found exception
//        Files.write(tmp, "Hello World".getBytes(), StandardOpenOption.CREATE_NEW, StandardOpenOption.TRUNCATE_EXISTING);

        // Runtime Error: NullPointerException getParent() of tmp path returns null
        // because tmp refers to a file tmp.txt ( of course it logically has a parent the current directory)
        // but getParent() on path refers to parent in the Path string provided while constructing the path
        // here we just given Paths.get("tmp.txt") which has no parent hence tmp.getParent() in the call below returns null
        // hence the NullPointerException
//        Files.write(tmp.getParent().resolve(Paths.get("temp1.txt")),
//                "Hello New World".getBytes(), StandardOpenOption.CREATE_NEW, StandardOpenOption.TRUNCATE_EXISTING);

        // CREATE_NEW creates a new file if it doesn't exists otherwise it throws an exception as explained above
        Files.write(tmp.toAbsolutePath().getParent().resolve(Paths.get("temp1.txt")),
                "Hello New World".getBytes(), StandardOpenOption.CREATE_NEW, StandardOpenOption.TRUNCATE_EXISTING);


        // If the file exists, it truncates the existing content so file goes to 0 bytes
        // And the new content "Hello New World" will be written to the file
        Files.write(tmp.toAbsolutePath().getParent().resolve(Paths.get("temp1.txt")),
                "Hello New World".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Path tmp1 = Paths.get("temp1.txt");
        System.out.println(Files.readAllLines(tmp1)); // Hello New World

        // Runtime Error: Exception in thread "main" java.lang.IllegalArgumentException: READ not allowed
        // We are opening the file for reading but we are doing a write here hence the error
//        Files.write(tmp.toAbsolutePath().getParent().resolve(Paths.get("temp1.txt")),
//                "Hello New World".getBytes(), StandardOpenOption.READ, StandardOpenOption.TRUNCATE_EXISTING);

        // compiler error: No readAllLines method with this syntax it is
        // readAllLine(Path) or readAllLines(Path, CharSet)
//        Files.readAllLines(tmp.toAbsolutePath().getParent().resolve(Paths.get("temp1.txt")),
//                "Hello New World".getBytes(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        System.out.println(Files.isWritable(tmp1));
        Path symlink = Paths.get("./symlinks/temp1_symlink.txt");
        // Runtime Error: Exception in thread "main" java.nio.file.FileSystemException: .\symlinks\temp1_symlink.txt:
        // A required privilege is not held by the client. Windows warrants us to run this in Run As Administrator mode
//        Path symlinkpath = Files.createSymbolicLink(symlink, tmp1);
//        System.out.println(symlinkpath.toString());

        List<String> ls = List.of("Hello", "Welcome", "to my world");
        // It will write each string in the list as a separate line in the file
        /*Hello
        Welcome
        to my world
        */
        Files.write(tmp, ls, StandardOpenOption.CREATE);

        System.out.println(Files.readAllLines(tmp));

    }
}
