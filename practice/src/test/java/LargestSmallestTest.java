import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class LargestSmallestTest {

    @Test
    void finderLS() {
        LargestSmallest s = new LargestSmallest();
        int[] integers = new int[]{ 20, 98, 12, 7, 35 };
        int[] expected = new int[]{7,98};
        assertArrayEquals(expected,s.FinderLS(integers));
    }
}