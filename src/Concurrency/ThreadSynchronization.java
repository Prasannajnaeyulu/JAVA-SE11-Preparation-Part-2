package Concurrency;

class Notifier{
    String name="Anonymous";
    void notify(String message){
        System.out.println("message: "+ message+ " from "+ this.name);
    }
    // it Locks the Object on which it is called
    // say, if we call 'n1.update(...)', then it locks object n1 until update method is finished
    synchronized void update(Notifier n1) {
        System.out.println("update method from "+ this.name);
        n1.handle_update(this);
    }

    // it locks the object on which it is called
    // say, if we call 'n2.handle_update(...)' then it locks Object n2 until handle_update method is finished
    synchronized void handle_update(Notifier n2) {
        System.out.println("handle_update method from: "+ this.name);
//        n2.notify();
    }
}

public class ThreadSynchronization{
    public static void main(String... args){
        Notifier n1 = new Notifier();
        Notifier n2 = new Notifier();
        Thread thread1 = new Thread(() -> n1.update(n2));
        Thread thread2 = new Thread(() -> n2.update(n1));
        thread1.start();
        thread2.start();
    }
}
