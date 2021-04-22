package Streams;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalExamples {
    class Student {
        private Optional<Float> gpa;
        private String name;

        Student(String name, Float gpa){
            this.name = name;
//            this.gpa = Optional.of(gpa); // if gpa is null it throws NullPointerException
            // the best practice is to use ofNullable instead
            this.gpa = Optional.ofNullable(gpa);
        }

        Student(String name){
            this(name, null);
        }

        public Optional<Float> getGpa() {
            return gpa;
        }

        public void setGpa(Optional<Float> gpa) {
            this.gpa = gpa;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString(){
            return "Student: "+ this.name+" his GPA: "+ this.gpa;
        }
    }

    public static void main(String... args){
        Optional<Float> gpa = null;
        // compiler error: Streams.OptionalExamples.this' cannot be referenced from a static context
//        Student s1 = new Student("Anji", 3.2f);
//        Student s2 = new Student("Greg", null);

        Student s1 = new OptionalExamples().new Student("Anji", 3.2f);
        Student s2 = new OptionalExamples().new Student("Greg");

        System.out.println(s1.getGpa()); // 3.2
        System.out.println(s2.getGpa()); // Optional.empty

        // Filter only the students with gpa present
        Stream.<Student>of(s1, s2).filter(student -> student.getGpa().isPresent()).forEach(System.out::println); //Student: Anji his GPA: Optional[3.2]

        // interesting: it prints nothing as we do not call any stream terminate method here
        Stream.<Student>of(s1, s2).filter(student -> student.getGpa().isPresent()).peek(s-> System.out.println(s.getGpa().get()));

        // compiler error: count method returns long so we must capture return value into long type but we used int here hence the error
//        int count = Stream.<Student>of(s1, s2).filter(student -> student.getGpa().isPresent()).peek(s-> System.out.println(s.getGpa().get())).count();

        long count = Stream.<Student>of(s1, s2).filter(student -> student.getGpa().isPresent()).peek(s-> System.out.println(s.getGpa().get())).count(); // prints 3.2
        System.out.println("Number of Students with grade is: "+ count); // 1


        // Runtime error: Exception in thread "main" java.lang.NullPointerException
        // gpa is null hence result in NullPointerException
//        Optional.of(gpa);

        // We can use 'ofNullable' instead_of 'of' to get rid of NullPointerException
        System.out.println(Optional.ofNullable(gpa)); // prints 'Optional.empty'

        // Runtime Error: Exception in thread "main" java.util.NoSuchElementException: No value present
        // Calling get on Null value Optional object throws NoSuchElementException
//        Optional.ofNullable(gpa).get();

    }
}
