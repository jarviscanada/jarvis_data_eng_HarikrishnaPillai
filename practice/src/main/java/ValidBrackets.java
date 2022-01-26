import java.util.HashMap;
import java.util.Stack;

public class ValidBrackets {
    public boolean Validity(String s) {
        Stack<Character> Storage = new Stack<>();
        HashMap<Character, Character> Lookup = new HashMap<>();
        Lookup.put('(', ')');
        Lookup.put('[', ']');
        Lookup.put('{', '}');
        for (int i = 0; i < s.length(); i++) {
            if (Lookup.containsKey(s.charAt(i)) && Lookup.containsValue(s.charAt(i))) {
                boolean b = s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}';
                if (Storage.isEmpty()) {
                    if (b) {
                        return false;
                    }
                    Storage.push(s.charAt(i));
                } else {
                    if (s.charAt(i) == Lookup.get(Storage.peek())) {
                        Storage.pop();
                    } else {
                        if (b) {
                            return false;
                        }
                        Storage.push(s.charAt(i));
                    }
                }
            } else {
                i++;
            }
        }
        if (Storage.isEmpty()) {
            return true;
        }
        return false;
    }
}
