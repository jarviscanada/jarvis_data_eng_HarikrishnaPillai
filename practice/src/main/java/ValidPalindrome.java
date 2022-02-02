public class ValidPalindrome {
    public boolean Palindrome(String s){
        boolean flag= false;
        StringBuilder sb= new StringBuilder(s.toLowerCase().replaceAll("[^a-z0-9]", ""));
        String pseudo="";
        for(int i=(sb.length()-1);i>=0;i--){
            pseudo = pseudo + sb.charAt(i);
        }
        StringBuilder end= new StringBuilder(pseudo.replaceAll("[^a-z0-9)]",""));
        if(sb.equals(end)){
            flag=true;
        }
        return flag;
    }
}
