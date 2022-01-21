import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvenOrOddTest {
    @Test
    void EvenOrOddTestValue(){
        EvenOrOdd num = new EvenOrOdd();
        assertFalse(num.EvenOrOdd(25));
    }
}