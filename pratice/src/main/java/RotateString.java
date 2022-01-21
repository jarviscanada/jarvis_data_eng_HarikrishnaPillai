public class RotateString {
    public String RotateString(String word){
        StringBuilder answer = new StringBuilder(word.length());
        for(int i=(word.length()-1);i>=0;i--){
          answer.append(word.charAt(i));
        }
        return answer.toString();
    }
}
