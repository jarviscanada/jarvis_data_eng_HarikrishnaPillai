import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QImplementsStackTest {
    @Test
    void StackTest(){
        QImplementsStack Qs = new QImplementsStack();
        Qs.push(1);
        Qs.push(2);
        Qs.push(3);
        assertEquals(3,Qs.top());
        assertEquals(3,Qs.pop());
    }

}