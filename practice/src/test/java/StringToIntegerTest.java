import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringToIntegerTest {
    @Test
    void SafeTestString(){
        StringToInteger myStr = new StringToInteger();
        assertEquals(-986,myStr.Atoi("-986"));
    }

}