package Collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorExamples {
    public static void main(String... args){
        // compiler error: Cannot resolve symbol 'Person'
        // Person is forward declaration hence it is not recognized
        // if we access Person class after class definition it is working alright
//        Person p = new Person("Anji", "Padavala");

        class Person{
            String fristName, lastName;

            Person(String firstName, String lastName){
                this.fristName = firstName;
                this.lastName = lastName;
            }


            public String getFristName() {
                return fristName;
            }

            public void setFristName(String fristName) {
                this.fristName = fristName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                lastName = lastName;
            }

            public String toString(){
                return this.fristName+ " "+ this.lastName;
            }
        }

        Person p1 = new Person("Anji", "Padavala");
        Person p2 = new Person("Srinu", "Gunti");
        Person p3 = new Person("Chandan", "Mallela");
        Person p4 = new Person("Mark", "Mclaugh");

        List<Person> lsPersons = new ArrayList<>(List.of(p1, p2, p3, p4));
        lsPersons.sort(Comparator.comparing(Person::getLastName).
                thenComparing(Person::getFristName));
        System.out.println(lsPersons);

        List<String> ls = new ArrayList<>();
        ls.add("Orange");
        ls.add("Apple");
        ls.add("Banana");
        ls.sort(Comparator.reverseOrder());
    }
}
