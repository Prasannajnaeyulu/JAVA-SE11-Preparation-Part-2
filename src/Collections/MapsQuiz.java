package Collections;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class MapsQuiz {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Ralph", 55);

        map.merge("Ralph", 1, MapsQuiz::mergeFunction);  // Line 1
        map.merge("Ros", 56, MapsQuiz::mergeFunction);  // Line 2
        System.out.println(map);

    }

    static Integer mergeFunction(Integer i1, Integer i2) {
        if (i2 < 55) return null;
        return i1 + i2;  // Line 3
    }
}
