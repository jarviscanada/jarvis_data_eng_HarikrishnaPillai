import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidPalindromeTest {
    @Test
    void PalindromeTest(){
        ValidPalindrome p = new ValidPalindrome();
        assertEquals(true,p.Palindrome("malayalam,malayalam"));
    }

}