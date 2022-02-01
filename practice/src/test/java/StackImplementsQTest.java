import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackImplementsQTest {
    @Test
    void QueueTest(){
        StackImplementsQ Sq = new StackImplementsQ();
        Sq.enqueue(1);
        Sq.enqueue(2);
        Sq.enqueue(3);
        assertEquals(1,Sq.peek());
        assertEquals(1,Sq.Dequeue());


    }

}