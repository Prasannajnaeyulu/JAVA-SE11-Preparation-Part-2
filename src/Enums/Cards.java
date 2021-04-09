package Enums;

// this enum internally converts to public final class as follows
//C:\anji\Java_Practice\JAVA-SE11-Preparation-Part-2\out\production\JAVA-SE11-Preparation-Part-2\Enums>javap DAYS.class
//Compiled from "Cards.java"
//final class Enums.DAYS extends java.lang.Enum<Enums.DAYS> {
//public static final Enums.DAYS MONDAY;
//public static final Enums.DAYS TUESDAY;
//public static final Enums.DAYS WEDNESDAY;
//public static final Enums.DAYS THURSDAY;
//public static final Enums.DAYS FRIDAY;
//public static final Enums.DAYS SATURDAY;
//public static final Enums.DAYS SUNDAY;
//public static Enums.DAYS[] values();
//public static Enums.DAYS valueOf(java.lang.String);
//static {};
//        }

enum DAYS{
    MONDAY(){
        public String toSting(){return "Hello";};
    }, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// this enum that can be extensible so it will be converted internally like this
//C:\anji\Java_Practice\JAVA-SE11-Preparation-Part-2\out\production\JAVA-SE11-Preparation-Part-2\Enums>javap Cards.class
//Compiled from "Cards.java"
//public abstract class Enums.Cards extends java.lang.Enum<Enums.Cards> {
//public static final Enums.Cards JOCKEY;
//public static final Enums.Cards QUEEN;
//public static final Enums.Cards KING;
//public static final Enums.Cards ACE;
//        int number1;
//        int number2;
//public static Enums.Cards[] values();
//public static Enums.Cards valueOf(java.lang.String);
//abstract void printName();
//public static void main(java.lang.String...);
//        Enums.Cards(java.lang.String, int, int, Enums.Cards$1);
//        Enums.Cards(java.lang.String, int, int, int, Enums.Cards$1);
//static {};
//        }

public enum Cards {
    JOCKEY(11){
        void print(String s){
            System.out.println(s+" number: "+ number1);
        }

        void printName() {
            print("Jockey here...");
        }
    },
    QUEEN(12){
        void print(String s){
            System.out.println(s+" number: "+ number1);
        }

        void printName() {
            print("Queen here...");
        }
    },
    KING(13){
        void print(String s){
            System.out.println(s+" number: "+ number1);
        }

        void printName() {
            print("King here...");
        }
    },
    ACE(1, 14){
        void print(String s){
            System.out.println(s+" number: "+ number1+ " I've another: "+ number2);
        }

        void printName() {
            print("Ace here you can use me for 1 or 14 count");
        }
    };

    int number1, number2;

    Cards(int number){
        this.number1 = number;
    }

    Cards(int number1, int number2){
        this.number1 = number1;
        this.number2 = number2;
    }

    // any abstract method declared in an enum should be overridden by all enum constants in their body as shown above
    abstract void printName();
    protected int getNumber1(){
        return number1;
    }
    private int getNumber2(){
        return number2;
    }

    public static void main(String ... args){
        for(Cards card: Cards.values()){
            card.printName();
        }

        System.out.println(Cards.QUEEN.valueOf("QUEEN")); //QUEEN
    }
}
