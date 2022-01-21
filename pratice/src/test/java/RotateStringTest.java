import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotateStringTest {
    @Test
    void RotateStringTestMethod(){
        RotateString letter = new RotateString();
        assertEquals("olleH",letter.RotateString("Hello"));
    }

}