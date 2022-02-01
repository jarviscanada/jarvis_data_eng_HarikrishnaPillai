import java.util.Stack;

public class StackImplementsQ {
        private Stack<Integer> StackOne = new Stack();
        private Stack<Integer> StackTwo = new Stack();

        public void enqueue(int x) {
            while (!StackOne.isEmpty())
                StackTwo.push(StackOne.pop());
            StackOne.push(x);
            while (!StackTwo.isEmpty())
                StackOne.push(StackTwo.pop());
        }

        public int Dequeue() {
            return StackOne.pop();
        }

        public int peek() {
            return StackOne.peek();
        }

        public boolean empty() {
            return StackOne.isEmpty();
        }
    }

