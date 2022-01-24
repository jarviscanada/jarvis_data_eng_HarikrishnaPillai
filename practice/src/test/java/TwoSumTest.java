import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {
    @Test
    void TwoSumTestVerify(){
        TwoSum num =new TwoSum();
        int[] arr = new int[]{2,7,11,15};
        int[] expected = new int[]{0,1};
        assertArrayEquals(expected,num.MapTwoSum(arr,9));
        assertArrayEquals(expected,num.BruteTwoSum(arr,9));
    }
}