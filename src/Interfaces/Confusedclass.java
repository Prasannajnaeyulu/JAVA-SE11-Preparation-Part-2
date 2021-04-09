package Interfaces;

// compiler error: Interfaces.Confusedclass inherits unrelated defaults for getRate() from types Interfaces.Walkable
// and Interfaces.Runnable
// It doesn't matter whether we use these methods in out class or not it checks if two unrelated interfaces have conflict default
// methods if so then compiler throws error straight away
//public class Confusedclass implements Walkable, Runnable{
//
//    @Override
//    public void walk() {
//        System.out.println("Walking.. at the rate of"+ getRate());
//    }
//
//    @Override
//    public void run() {
//        System.out.println("Running at the rate of"+ getRate());
//    }
//}

// To overcome above problem we have to override getRate() method locally
public class Confusedclass implements Walkable, Runnable{

    @Override
    public void walk() {
//        System.out.println("Walking at the rate of: "+ getRate()); // note: it calls local one defined in this class
        System.out.println("Walking at the rate of: "+ Walkable.super.getRate()); // it calls default method defined in interface Walkable
    }

    @Override
    public void run() {
//        System.out.println(String.format("Running at the rate of: %f", getRate())); // note: it calls local one defined in this class
        System.out.println(String.format("Running at the rate of: %f", Runnable.super.getRate())); // it calls default method defined in interface Runnable
    }

    // interesting: even though we are calling specific walkable or runnable getRate above, without this local method,
    // compiler will throw an error as I noted above irrespective of whether we are using interface default methods or not
    // the two unrelated interfaces having same function name for default is a compiler error. So we must override
    // locally as follows
    public float getRate(){
        return 5.2f;
    }

    public static void main(String[] args){
        Confusedclass c = new Confusedclass();
        c.walk();
        c.run();
    }
}