package ParallelStreams;

public class Quiz {
   /* Good job!
    Correct Answer: A serial stream will not use the third argument of the reduce method (the combiner) so making this
    change will produce the correct result. Or the combiner needs to be changed to x.addAll(y) for a parallelStream,
    adding the results of the parallel sub-streams represented by y to the result x.

    Question 1:
    Given:

    public class Test {
        public static void main(String[] args) {
            List<Integer> result = List.of(1, 2)
                    .parallelStream()  // Position 1
                    .collect(ArrayList::new,
                            (x, y) -> x.add(y),  // Position 2
                            (x, y) -> y.addAll(x)  // Position 3
                    );
            System.out.println(result);
        }
    }
    Which two of the following changes (when applied singly) allows the code to compile and print [1 2]

    A. Change code at Position 1 to

            .stream()  // Position 1

    B. Change code at Position 1 to

            .stream().parallel()  // Position 1

    C. Change code at Position 2 to :

            (x, y) -> y.add(x),  // Position 2

    D. Change code at Position 2 to

            (x, y) -> x.set(y-1, y),  // Position 2

    E. Change code at Position 3 to

            (x, y) -> x.addAll(y)  // Position 3

    F. Change code at Position 3 to

            (x, y) -> x.add(y)  // Position 3
            */


//    Correct answer: The result of the first operation (collect) will always be an empty String because a String does not mutate,
// and the result of the second operation (reduce) will always be “ab”, so the code will always output false.
//
//    Question 1:
//    Given:
//
//    public class Test {
//        public static void main(String[] args) {
//            String firstResult = Stream.of("a", "b")
//                    .parallel()
//                    .collect(String::new,
//                            String::concat,
//                            String::concat);
//
//            String secondResult = Stream.of("a", "b")
//                    .parallel()
//                    .reduce(new String(),
//                            String::concat,
//                            String::concat);
//
//            System.out.println(firstResult.equals(secondResult));
//        }
}
