package Collections;

import java.util.Map;
import java.util.TreeMap;



/*Ralph’s value is evaluated to 1 (s2 is the value which is null because Ralph entry did not exist), so the value is set to
  employeeNo (1). In addition, Anne is added to the map, s1 has the value ‘Anne’ so is not null, therefore the value for
  the ‘Anne’ key is set to s2 +1. If the code had used a increment operator (++) on employeeNo, this would have been
  a compile time error. In addition, a TreeMap is ordered using natural Order, which is alphabetical for Strings.*/
class Test {
    public static void main(String[] args) {
        Map<String, Integer> treemap = new TreeMap<>();

        // Ralph a new employee
        int employeeNo = 1;
        treemap.compute("Ralph",
                (s1, s2) -> (s2 == null) ? employeeNo : s2 + 1);  // Line 1

        int s2 = 3;  // Line 2

        // Anne a  new employee
        // compiler error: Variable used in lambda expression should be final or effectively final
//        treemap.compute("Anne",
//                (s1, s3) -> (s1 == null) ? employeeNo + 1 : s2++);  // Line 3

        // it is OK now
        treemap.compute("Anne",
                (s1, s3) -> (s1 == null) ? employeeNo + 1 : s2+1);  // Line 3
        System.out.println(treemap);
    }
}
