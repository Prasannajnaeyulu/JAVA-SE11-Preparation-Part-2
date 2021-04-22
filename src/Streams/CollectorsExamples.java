package Streams;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Pet{
    String[] names = {"Dog", "Cat", "Rat", "Chicks"};
    String[] states = {"AL", "CO", "CF", "FL"};
    int[] ages = {2,4,1,5,7,10};

    String name;
    String state;
    int age;

    Pet(){
        // Note: Random().ints first argument inclusive second argument exclusive
        List<Integer> indexes = new Random().ints(0, 4).boxed().limit(3).
                collect(Collectors.toList());
        this.name = names[indexes.get(0)];
        this.state = states[indexes.get(1)];
        this.age = ages[indexes.get(2)];
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString(){
        return "Pet: "+ this.name+ " State : "+ this.state+ " Age: "+ this.age;
    }
}
public class CollectorsExamples {
    public static void main(String... args){
        Map<String, Long> map = Stream.generate(Pet::new).limit(10).
                                        collect(Collectors.groupingBy(
                                                Pet::getName,
                                                Collectors.counting()));

        System.out.println(map);

        Map<String, Map<String, Long>> map2 = Stream.generate(Pet::new).limit(10).
                collect(Collectors.groupingBy(
                        Pet::getState,
                        Collectors.groupingBy(Pet::getName,
                                TreeMap::new,
                                Collectors.counting()
                        )));

        System.out.println(map2);

        Double averageAgeOfAllDogsFromAllStates = Stream.generate(Pet::new).limit(10)
                                                    .peek(System.out::println)
                                                    .filter(p -> p.getName().equals("Dog"))
                                                    .peek(d -> System.out.println(d.getAge()))
                                                    .collect(Collectors.averagingInt(Pet::getAge));

        System.out.println(averageAgeOfAllDogsFromAllStates);
    }
}
