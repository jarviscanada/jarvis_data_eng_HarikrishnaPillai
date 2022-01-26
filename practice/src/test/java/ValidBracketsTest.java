import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidBracketsTest {
    @Test
    void BracketTest(){
        ValidBrackets Vb = new ValidBrackets();
        assertEquals(true,Vb.Validity("[{(HarikrishnaSureshkumarPillai)}]"));
    }

}