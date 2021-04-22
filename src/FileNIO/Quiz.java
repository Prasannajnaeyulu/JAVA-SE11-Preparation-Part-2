package FileNIO;

//Good job!
//        Correct answer. * The relativize method could be rewritten as
// Path.of("/test/root").relativize(Path.of("/test/root/f.txt")) * It is important to note that
// getName(0) is not c:/ (the root). getName(0) returns the element closest to the root, test. * p.subpath(0, 1) = test,
// p.subpath(1,2) = root; p.subpath(2,3) = f.txt
//

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Quiz {
    public static void main(String[] args) throws IOException {
        Path p = Paths.get("c:/", "test", "root", "f.txt");

        Path p1 = p.getParent().relativize(p);  // Line 1 // f.txt
        Path p2 = p.getName(0);        // Line 2 // test ( it is the element closest to the root)
        Path p3 = p.subpath(2, 3);     // Line 3 (f.txt)

        System.out.println(p1 + " " + p2 + " " + p3);
    }
}
