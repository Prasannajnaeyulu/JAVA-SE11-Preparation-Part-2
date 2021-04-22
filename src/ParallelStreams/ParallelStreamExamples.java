package ParallelStreams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParallelStreamExamples {
    public static void main(String... args){
//        List<Integer> result = List.of(1,2).stream().parallel().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
//        System.out.println(result);

        List<Integer> result = List.of(1, 2)
                // this parallel stream can't guarantee the order of retrieving elements from the above list
                // the elements may come in any order
                .parallelStream()
                .peek(System.out::println)
                .collect(ArrayList::new,
                        (x, y) -> { System.out.println("accumulator x: "+ x+ " and y: "+ y); x.add(y); },  // Position 2
                        // interesting: remember the combiner function returns x as a result
                        // here we are adding 'x' to 'y' so y gets all elements where as 'x' will not change
                        // hence 'x' the result of accumulator from above holds the same value
                        // which the 'result' variable will get
                        // See the below output trace for better understanding:
                        /* 2
                           accumulator x: [] and y: 2
                           1
                           accumulator x: [] and y: 1
                           combiner x: [1] and y: [2]
                           [1]
                        */
                        (x, y) -> { System.out.println("combiner x: "+ x+ " and y: "+ y); y.addAll(x); }  // Position 3
                );
        System.out.println(result);

        Integer result1 = List.of(1, 2)
                // this parallel stream can't guarantee the order of retrieving elements from the above list
                // the elements may come in any order
                .parallelStream()
                .peek(System.out::println)
                // Note: second function for reduce is BiFunction which takes two Integer args in this case as we are streaming integer list
                // third argument is a combiner which is a BinaryOperator function which takes two Integers basically two results
                // from two parallel streams
                // From first parallel stream, x gets 10 first, and y gets 1 or 2
                // From second parallel stream, x gets 10 and y gets 1 or 2
                // after accumulators run for all streams then the combiner function will start
                // which gets result of first accumulator in first arg, result of second accumulator in second arg
                // and apply binary operation '+' which adds the two results Integer values
                // 11 + 12 = 23
                .reduce(10, (x, y) -> x+y, (x,y) -> x+y);
                // compiler error: reduce method gets two integers as argument as we are streaming on integer list
                // but we are trying to call List add function here on an Integer object hence the error
//                .reduce(new ArrayList<Integer>(), (x, y) -> x.add(y));
//                .collect(new ArrayList<Integer>(),
                        //interesting: compiler error: collect method first argument should be a supplier function
                        // but we are passing an object instead of function hence it couldn't
//                        (x, y) -> { System.out.println("x: "+ x+ " and y: "+ y); x.add(y); },  // Position 2
//                        (x, y) -> { System.out.println("x: "+ x+ " and y: "+ y); x.addAll(y); }  // Position 3
//                );
        System.out.println(result1); // prints '23'


        String result3 = List.of("Apple", "Banana")
                // this parallel stream can't guarantee the order of retrieving elements from the above list
                // the elements may come in any order
                .parallelStream()
                .peek(System.out::println)
                // Any immutable object will leave the object in same state as before
                // because the first argument which is used by accumulator function returns a new string
                // but not changing original string hence the original string empty stays as it is after
                // all the operations
                .collect(String::new, String::concat, String::concat);
        System.out.println(result3); // prints empty string

        Integer res = List.of(1,2,3).stream().parallel().peek(System.out::println)
                .reduce(10, (x,y) -> { System.out.println("acc x: "+x+ " y: "+ y);return x + y; },
                        (x,y) -> { System.out.println("combiner x: "+x+ " y: "+ y);return x + y; });

        System.out.println(res);

        // interesting: combiner function will only be evaluated in parallel stream it is not executed
        // in sequential stream. So the result is outcome of accumulator function only
        Integer res1 = List.of(1,2,3).stream().peek(System.out::println)
                // interesting: Note: first argument of reduce is a result holder which should match to the Integer
                // as we are streaming the integer list. The result would be accumulated into this
                .reduce(10, (x,y) -> { System.out.println("acc x: "+x+ " y: "+ y);return x + y; },
                        (x,y) -> { System.out.println("combiner x: "+x+ " y: "+ y);return x + y; });

        System.out.println(res1); //16


//        Integer res4 = List.of(1,2,3).stream().peek(System.out::println)
                // interesting: Integer class has no default constructor available hence we can't use this as a supplier
//                .collect(Integer::new, (x,y) -> { System.out.println("acc x: "+x+ " y: "+ y);return x + y; },
//                        (x,y) -> { System.out.println("combiner x: "+x+ " y: "+ y);return x + y; });

//        System.out.println(res4);

        // compiler error: interesting: here the accumulator and combiner takes BiConsumer functions,
        // So consumer doesn't return anything its return type must be void
        // Incompatible types: expected void but the lambda body is neither a statement expression nor a void-compatible block
//        Integer res4 = List.of(1,2,3).stream().parallel().peek(System.out::println)
//                .collect(() -> new Integer(10),
//                        (x,y) -> { System.out.println("acc x: "+x+ " y: "+ y); return x+y; },
//                        (x,y) -> { System.out.println("combiner x: "+x+ " y: "+ y); return x+y; });

        Integer res4 = List.of(1,2,3).stream().parallel().peek(System.out::println)
                .collect(() -> new Integer(10),
                        // as the lambda functions are BiConsumer type any increments/decrements made to x will not reflect
                        // see the following output trace for example:
                        /*
                            2
                            1
                            acc x: 10 y: 1
                            3
                            acc x: 10 y: 3
                            acc x: 10 y: 2
                            combiner x: 10 y: 10
                            combiner x: 10 y: 10
                         */
                        // interesting: for each parallel stream, 'x' gets 10 and nothing will change so the value of x i.e., '10'
                        // will be returned to combiner as a result of accumulation
                        (x,y) -> { System.out.println("acc x: "+x+ " y: "+ y); x+=y; },
                        (x,y) -> { System.out.println("combiner x: "+x+ " y: "+ y); x+=y; });

        System.out.println(res4); // 10

//        Integer res5 = List.of(1,2,3).parallelStream().peek(System.out::println)
//                .collect(ArrayList::new,
                        // compiler error: no instance(s) of type variable(s) E exist so that ArrayList<E> conforms to Integer
                        // see the left hand side assignment is of type Integer but this function returns ArrayList hence
                        // the error
//                        (x,y) -> { System.out.println("acc x: "+x+ " y: "+ y); x.add(y); },
//                        (x,y) -> { System.out.println("combiner x: "+x+ " y: "+ y); x.addAll(y); });

        List<Integer> res5 = List.of(1,2,3).parallelStream().peek(System.out::println)
                .collect(() -> new ArrayList<>(),
                        // interesting: here we are using ArrayList as a first argument
                        // so .add() actually updates the Object so the updated object is what it pass to
                        // the combiner. Hence though the consumer doesn't return anything, as it updated the
                        // underlying object, the added values available in the arrayList
                        (x,y) -> { System.out.println("acc x: "+x+ " y: "+ y); x.add(y); },
                        (x,y) -> { System.out.println("combiner x: "+x+ " y: "+ y); x.addAll(y); });
        System.out.println(res5); // [1,2,3]
    }
}
