import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapArrayTest {
    @Test
    void SwapTestMethod(){

        SwapArray num;
        num = new SwapArray();
        int[] expected = new int[]{6,5};
        assertArrayEquals(expected ,num.SwapMethod(new int[]{5,6}));
    }
}