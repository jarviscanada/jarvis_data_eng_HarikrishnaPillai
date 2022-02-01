import java.util.HashMap;

public class ValidAnagram {
        public boolean isAnagram(String s, String t) {
            HashMap<Integer,Character> stringMap = new HashMap<>();
            for(int i=0;i<s.length();i++){
                stringMap.put(i,s.charAt(i));}
            if(s.length()!=t.length()){
                return false;}
            else{
                for(int i=0;i<t.length();i++){
                    if(!(stringMap.containsValue(t.charAt(i))))
                        return false;}
            }return true;}
}
