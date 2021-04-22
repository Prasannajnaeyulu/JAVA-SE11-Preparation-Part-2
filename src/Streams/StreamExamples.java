package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples {
    public static void main(String... args){
        Stream<String> s = Stream.empty();
        System.out.println(s.count());

        Stream<String> s1 = List.of("Apple", "Orange", "Banana").stream();
        s1.forEach(System.out::println);

        Stream<Integer> s2 = Stream.of(10, 20, 30, 40);
        System.out.println(s2.reduce((a, b) -> a + b).get());

        String[] s3 = new String[]{"Apple", null, "Jackfruit"};
        Stream<String> s4 = Stream.ofNullable(s3[1]);
        s4.forEach(System.out::println);

        Stream<Double> s5 = Stream.generate(Math::random);
        // Stream.generate can be lazy so we can set limit separately and then we can call forEach termination operation later
        s5 = s5.limit(20);
        s5.forEach(System.out::println);

        Stream s6 = Stream.iterate(1, t -> t + 5);
        // Stream.iterate or stream.generate can't be lazy. If We set the limit without assigning to stream back, then it
        // considers as stream is operated and it closes the stream. So we can't perform any operations on this stream later
//        s6.limit(20);

//        // Runtime error: Exception in thread "main" java.lang.IllegalStateException: stream has already been
//        // operated upon or closed because s6.limit on Stream.iterate() will operate and close once we call limit separately
//        s6.forEach(System.out::println);

        // but this is OK as we are assigning it back to the stream
        s6 = s6.limit(20);
        s6.forEach(System.out::println);



        Stream<Double> s7 = Stream.iterate(12.5, t -> t <=100, t -> t + 5);
        s7.forEach(System.out::println);

        Stream<CharSequence> s8 = Stream.of("Hello World");
        s8 = Stream.concat(s8, Stream.of("World"));
        s8.forEach(System.out::println);

        Stream.Builder<String> sb = Stream.<String>builder();
        sb.add("Hello");
        sb.add("World");
        sb.add("Welcome to world of programming");
        sb.accept("helllooooo");
        sb.build().forEach(System.out::println);

        // Runtime error: Exception in thread "main" java.lang.IllegalStateException
//        sb.accept("Helloooo");

        // compiler error: Type argument can't be of primitive type
//        BiFunction<int, int> bf = (int a, int b) -> a + b;

        // compiler error: Incompatible parameter types in lambda expression: expected Integer but found int
//         BiFunction<Integer, Integer> bf = (int a, Integer b) -> a + b;

        Stream.Builder<String> sb1 = Stream.<String>builder();
        sb1.add("Hello");
        sb1.add("World");
        sb1.add("Welcome to world of programming");

//        BiFunction<String, String, String> bf1 =
        sb1.build().reduce((String a, String b) -> a + b);

        // compiler error: Incompatible parameter types in lambda expression: expected String but found CharSequence
//        sb1.build().reduce((CharSequence a, String b) -> a + b);

        // interesting: but this is OK. You can have a method reference which takes polymorphic parameter types
        // but passing as lambda as in the above example do not allow as the types in lambda should all be equal
        Stream.Builder<String> sb2 = Stream.<String>builder();
        sb2.add("Hello");
        sb2.add("World");
        sb2.build().reduce(StreamExamples::append).get();

        Stream.Builder<String> sb3 = Stream.<String>builder();
        sb3.add("Hello");
        sb3.add("World");
        sb3.add("Welcome to world of programming");


        Stream.Builder<String> sb4 = Stream.<String>builder();
        sb4.add("Hello");
        sb4.add("World");

        Stream<String> merged = Stream.concat(sb3.build(), sb4.build());
        // Runtime Exception: IllegalStateException Once the stream is built with the build() call no more adds are allowed
//        sb3.add("Extra");
        merged.forEach(System.out::println);

        // Runtime error: Illegal State exception
        // Once the stream operates ( here it is a stream of two streams concatenated) both the streams are closed
        // So performing any other operations on merged stream or individual stream that are concatenated results in
        // Illegalstate exception
//        sb3.build().reduce((a,b)-> a+ b);
//        merged.reduce((a,b)-> a+ b);
//        sb4.build().reduce((a,b) -> a+b);


        Set<String> set1 = new TreeSet<>();
        set1.add("Mark");
        set1.add("James");
        set1.add("Gary");

        Set<String> set2 = new TreeSet<>();
        set2.add("Harry");
        set2.add("Mark");
        set2.add("Barry");

        Stream<String> merged1 = Stream.concat(set1.stream(), set2.stream());
        // interesting: If we do modifications to a set after concat ( provided this set is one of the source of concat )
        // Here set1 is a first source of concat so it causes ConcurrentModificationException whenever this 'merged1' stream
        // operates at later stage i.e., on Line#126 below
//        set1.add("Juliat");
        // Note: the lambda expression is a consumer function with an argument of type String or its supertype
        // So Object is fine here
        merged1.peek(str -> System.out.println("Peek element: "+str)).distinct().forEach((Object o) -> System.out.println(o));

        System.out.println("\n---------------------------------------------\n");
        // infinite stream which generates new String objects
        Stream<String> stream = Stream.generate(String::new);
        stream = stream.limit(10);

        // this set finally contains only one element "Hello" because we are collecting the result to Set which doesn't allow
        // duplicates so you end up having only one "Hello".
//        Set<String> modifiableset = stream.map(str1 -> "Hello").peek(System.out::print).collect(Collectors.toSet());

//        List<String> modifiableset = stream.map(str1 -> "Hello").peek(System.out::print).collect(Collectors.toList());
//        modifiableset.add("123");
//        modifiableset.forEach(System.out::println);

        Set<String> unmodifiableset1 = stream.collect(Collectors.toUnmodifiableSet());
        // Runtime error: Exception in thread "main" java.lang.UnsupportedOperationException
        // this set is unmodifiable
//        unmodifiableset1.add("Hello");

        // imagine this as equivalent to array of List<Integer> [ [1,2,3,4], [5,6,7,8]]
        Stream<List<Integer>> stream2 = Stream.of(List.of(1,2,3,4), List.of(5,6,7,8));
        List<Integer> finalList = stream2.flatMap(List::stream).collect(Collectors.toList());
        System.out.println(finalList); // [1,2,3,4,5,6,7,8] - awesome that is a flattened array :)

        // other approach using flatMapToInt
        Stream<List<Integer>> stream3 = Stream.of(List.of(1,2,3,4), List.of(5,6,7,8));
        List<Integer> ls2 = stream3.flatMapToInt(ls -> ls.stream().mapToInt(Integer::intValue)).boxed().collect(Collectors.toList());
        System.out.println(ls2); // [1,2,3,4,5,6,7,8]

        // compiler error: there is no mapToInt function in IntStream class
//        IntStream.of(1,2,3,4,5).mapToInt(s10 -> s10*10);
        IntStream.of(1,2,3,4,5).mapToLong(s10 -> s10*10);
        IntStream.of(1,2,3,4,5).mapToDouble(s10 -> s10*10);

        Stream<Integer> stream5 = Stream.<Integer>of(1,2,3,4,5);
        // But mapToInt available in Stream class it is not available in IntStream class
        stream5.mapToInt(value -> value * 10);

        // compiler error: peek should accept consumer function as an argument and it should not have any return value
        // but here we are returning s12 hence the compiler error
//        stream5.peek(s12 -> s12);

        // compiler error: Once we convert Stream to intStream using mapToInt, we get an intstream so doing mapToInt
        // again throws compiler error as mapToInt not available on intStream
//        stream5.mapToInt(value -> value * 10).mapToInt(v -> v/10);

    }

    public static String append(CharSequence a, String b){
        return a+b;
    }
}
