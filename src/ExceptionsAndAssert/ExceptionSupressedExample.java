package ExceptionsAndAssert;

import java.io.FileNotFoundException;
import java.io.FileReader;

/* Note: Any class that directly extends Throwable is a checked exception class
   So it must satisfy try/catch or throws requirement specification
*/
class CustomThrowable extends Throwable{
    CustomThrowable(String message){
        super(message);
    }

    CustomThrowable(String message, Throwable t){
        super(message, t);
    }

    public void greet(String message){
        if(message==null)
            throw new CustomRuntimeException("Message to greet shouldn't be null");
        System.out.println(message);
    }
}

class CustomRuntimeException extends RuntimeException{
    CustomRuntimeException(String message){
        super(message);
    }

    CustomRuntimeException(String message, Throwable t){
        super(message, t);
    }
}

class First implements AutoCloseable {
    public void run() {
        System.out.println("Running First");
        //throw new IllegalStateException("First failed to run illegal state exception");
    }

    /*
    close()' in 'ExceptionsAndAssert.First' clashes with 'close()' in 'java.lang.AutoCloseable';
    overridden method does not throw 'java.lang.Throwable'
    */
    // compiler error: overridden method should not have any throws declaration or it must throw same or subtype of the
    // exception in its parent interface or class. The close() from AutoCloseable throws Exception so here throws class can ignore
    // specifying it or can specify most specific exception which is same or subtype but not broader exception
//    public void close() throws Throwable { }

    // It is ok
//    public void close() throws Exception {
//        throw new IllegalStateException("First failed to close");
//    }

    public void close() {
        throw new IllegalStateException("First failed to close");
    }
}

class Second implements AutoCloseable {
    public void run() {
        throw new IllegalStateException("Second failed to run illegal state exception");
    }

    public void close() {
        throw new IllegalStateException("Second failed to close");
    }
}

public class ExceptionSupressedExample {
    public static void main(String ...args){
//        Example1:
//        try {
//            new CustomThrowable("custom exception").greet(null);
//        }
//        // addSuppressed is to add another exception to the original exception
//        catch (Exception e){
//            e.addSuppressed(new CustomThrowable("Exception in calling method"));
//            /*
//            Exception in thread "main" ExceptionsAndAssert.CustomRuntimeException: Message to greet shouldn't be null
//	at ExceptionsAndAssert.CustomThrowable.greet(ExceptionAssert.java:17)
//	at ExceptionsAndAssert.ExceptionAssert.main(ExceptionAssert.java:35)
//	Suppressed: ExceptionsAndAssert.CustomThrowable: Exception in calling method
//		at ExceptionsAndAssert.ExceptionAssert.main(ExceptionAssert.java:38)
//             */
//            throw e;
//        }
//        Example2:

        First f = new First();
        try(f; Second s = new Second()){
            f.run();
            s.run();
        }
        catch (Exception e){
            //It prints java.lang.IllegalStateException: Second failed to run illegal state exception
            // interesting: Note: it suppress exceptions thrown by f.close() and s.close() calls as these calls happens first
            // prior to handling exceptions thrown by s.run(), so the exceptions of f.close() and s.close() will suppress and
            // we get the exception from s.run  into catch block and that's what it prints out here
            //e.addSuppressed(e);
            e.printStackTrace();
        }
    }
}


