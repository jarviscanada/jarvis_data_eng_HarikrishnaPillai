import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CompareMapTest {
    @Test
    void MapCompareMethod(){
        CompareMap num = new CompareMap();
        Map<String, String> mapA = new HashMap<>();
        mapA.put("key1", "value1");
        Map<String, String> mapB = new HashMap<>();
        mapB.put("key1", "value1");
        assertEquals(true, num.CompareMaps(mapA, mapB));
    }

}