package GenericsAndCollections;

// compiler error: wild cards can't be used in class super type declaration
//public class WildCardsInvalid extends WildCardValid<?>{
//}

// compiler error: wild cards can't be used in class Type declaration
//public class WildCardsInvalid<?>{
//}

public class WildCardsInvalid {
    // compiler error: wild cards can't be used in method type declaration
//    <?> void print(){}

    // compiler error: Wildcard type '?' cannot be instantiated directly
//    WildCardValid<String> wildCardValid = new WildCardValid<?>();
}