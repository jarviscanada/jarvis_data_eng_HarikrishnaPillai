import java.util.LinkedList;
import java.util.Queue;

public class QImplementsStack {
    Queue<Integer> q = new LinkedList<>();
/** Push Method as seen in stack. Pop all elements after inserting to maintain structure of Stack **/
    public void push(int x) {
        q.add(x);
        for(int i=1; i<q.size(); i++){
            q.add(q.remove());
        }
    }
/** Pop method using remove method in Queue **/
    public int pop() {
        if (q.isEmpty()){
            return -1;
        }
        return q.remove();
    }
/** Peek at the top of the Stack **/
    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
