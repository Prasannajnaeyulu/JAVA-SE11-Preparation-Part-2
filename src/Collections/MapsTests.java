package Collections;

import java.util.HashMap;
import java.util.Map;

public class MapsTests {
    public static void main(String... args){
        Map<String, Integer> map = new HashMap<>();
        map.put("alpha", 1);
        // Runtime error: java.lang.NullPointerException because key "beta" is not found
        // compute function takes a bifunction and it passes key, value of every entry in the map
        // to this bi function
        map.compute("alpha", (key, value) -> { System.out.println("key :"+ key); return value + 100; });
        System.out.println(map);
        // Note: if the key is not found then merge function creates a new entry in the map with this key
        // and the value as the value supplied in second argument here it is '10'
        // The remapping function will not execute in this case
        // here default value is the value provided in second argument
        // value1 is the value of the key found in the map
        map.merge("beta", 10, (value1, defaultvalue) -> value1*=10 );
        // {alpha=101, beta=10}
        System.out.println("map after beta merge: "+ map);

        // here default value is the value provided in second argument
        // value1 is the value of the key found in the map
        map.merge("beta", 30, (value1, defaultvalue) -> value1*=defaultvalue );
        System.out.println("map after remapping beta: "+ map);

        map.merge("beta", 20, MapsTests::mergecompute);
        System.out.println("map after remapping non-existent key gamma: "+ map);
    }

    static Integer mergecompute(Integer i1, Integer i2){
        System.out.println("i1:" + i1);
        System.out.println("i2:" + i2);
        if(i1 == null) return null;
        return i1+i2;
    }
}
