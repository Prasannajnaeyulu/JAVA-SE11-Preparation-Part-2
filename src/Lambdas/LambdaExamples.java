package Lambdas;

interface Calculable<T, U>{
    void compute(T t, U u);
}

interface Calc{
    int compute(int a, int b);
}

public class LambdaExamples {
    public static void main(String... args){
        // compiler error: the 'compute' method in the functional interface Calculable is declared with void
        // return type. But this lambda expression is returning a+b which is int type hence the error
//        Calculable c = (int a, int b) -> a+b;
        int a = 0;
        // compiler error: Variable 'a' is already defined in the scope
//        Calculable c = (a, b) -> System.out.println((int)a+(int)b);
        // compiler error: Variable used in lambda expression should be final or effectively final
//        Calculable c = (b1, b2) -> System.out.println((int)++a + (int)b);


        // interesting: lambda expression variables can be changed but the outside variables used in the expression
        // must be final. The lambda parameter variables can't be final we can change them inside an expression
        Calc c = (c1, c2) -> ++c1 + c2++;

        Calculable c3 = (b1, b2) -> System.out.println((int)b1+(int)b2);
        c3.compute(10, 30);
    }
}
