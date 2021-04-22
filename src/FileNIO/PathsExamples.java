package FileNIO;

import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

//https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html#resolveSibling-java.nio.file.Path-
public class PathsExamples {
    public static void main(String... args){
//        Path p = Path.of("");
//        System.out.println("Path: "+ p.toAbsolutePath());

        Path p = Paths.get("");
        System.out.println("Path: "+ p.toAbsolutePath());

        // interesting: note: compareTo method will not check the real existence of paths
        // it simply compare the given 2 paths though they are available in the filesystem or not
        Path p1 = Paths.get("/a/../b/../c");
        Path p2 = Paths.get("a/../b/../c");
        System.out.println("Comparing paths : p1: "+ p1.toString()+ " with p2: "+p2.toString()+ " Result: "+ p1.compareTo(p2));

        Path p3 = FileSystems.getDefault().getPath("");
        // interesting: it prints nothing as it refers to the current working directory
        System.out.println(p3);

        // but absolutepath prints C:\anji\Java_Practice\JAVA-SE11-Preparation-Part-2
        System.out.println("Path: "+ p3.toAbsolutePath());

        System.out.println("Normalised path p1: "+p1.normalize()); // \c
        System.out.println("Normalised path p2: "+p2.normalize()); // c
        // relativize returns the relative path from P1
        // Runtime error: Exception in thread "main" java.lang.IllegalArgumentException: 'other' is different type of Path
        // Here path p1 is absolute so it throws IllegalArgumentException
//        System.out.println("relativized path: "+p1.relativize(p2));
        // resolve joins two paths provided either one is not null
        System.out.println("resolve the path: "+ p1.resolve(p2));
        // Technically this is how to reach to p1 from p2 (this call uses normalized paths of argument and this);
        // here p2 normalized path is 'c' and the arg path is current directory
        // so to reach current directory from c( i.e., './c') is '..'
        System.out.println("relativized path: "+ p2.relativize(FileSystems.getDefault().getPath(""))); // ..

        // resolve sibling won't normalize the given paths
        // it gets the parent of this path here in our example p1 is /a/../b/../c so it's parent is /a/../b/..
        // If given path is an empty path then this method returns this path's parent
        // so the result is \a\..\b\..\src
        System.out.println("sibling of p1 and src directory: "+ p1.resolveSibling(FileSystems.getDefault().getPath("src")));

        // a\..\b\..\src
        System.out.println("sibling of p2 and src directory: "+ p2.resolveSibling(FileSystems.getDefault().getPath("src")));


        Path p5 = Paths.get("src/FileNIO");
        System.out.println(FileSystems.getDefault().getPath("").resolve(p5));

        Path p8 = Paths.get("foo", "bar"); // C:\anji\Java_Practice\JAVA-SE11-Preparation-Part-2\foo\bar
        System.out.println(p8.toAbsolutePath());


        /*
        Resolves the given path against this path's parent path. This is useful where a file name needs to be replaced with another
         file name. For example, suppose that the name separator is "/" and a path represents "dir1/dir2/foo", then invoking this
          method with the Path "bar" will result in the Path "dir1/dir2/bar". If this path does not have a parent path, or
          other is absolute, then this method returns other. If other is an empty path then this method returns this path's parent,
          or where this path doesn't have a parent, the empty path.
         */
        Path p9 = Paths.get("foo", "bar1", "bar2");
        System.out.println(p9.resolveSibling(p8));// foo/bar1/foo/bar

        System.out.println(p8.resolveSibling(p9));// foo/foo/bar1/bar2

        System.out.println(p8.resolveSibling(Paths.get("/test")));// /test beacuse the path given as arg. is absolute
        /*
        Path relativize(Path other)
Constructs a relative path between this path and a given path.
Relativization is the inverse of resolution. This method attempts to construct a relative path that when resolved against this path, yields a path that locates the same file as the given path. For example, on UNIX, if this path is "/a/b" and the given path is "/a/b/c/d" then the resulting relative path would be "c/d". Where this path and the given path do not have a root component, then a relative path can be constructed. A relative path cannot be constructed if only one of the paths have a root component. Where both paths have a root component then it is implementation dependent if a relative path can be constructed. If this path and the given path are equal then an empty path is returned.

For any two normalized paths p and q, where q does not have a root component,

p.relativize(p.resolve(q)).equals(q)
When symbolic links are supported, then whether the resulting path, when resolved against this path, yields a path that can be used to locate the same file as other is implementation dependent. For example, if this path is "/a/b" and the given path is "/a/x" then the resulting relative path may be "../x". If "b" is a symbolic link then is implementation dependent if "a/b/../x" would locate the same file as "/a/x".

Parameters:
other - the path to relativize against this path
Returns:
the resulting relative path, or an empty path if both paths are equal
Throws:
IllegalArgumentException - if other is not a Path that can be relativized against this path
         */


    }
}
