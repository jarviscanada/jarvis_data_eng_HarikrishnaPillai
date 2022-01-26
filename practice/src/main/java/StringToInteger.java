public class StringToInteger {
    public int Atoi(String str){          //Method to convert string to integer
        int i= 0;
        int sign =1;
        int result=0;
        if(str.charAt(0) == '-')
        {
            sign = -1;
            i++;
        }
        for (; i < str.length(); ++i)   //O(n) time complexity
            result = result * 10 + str.charAt(i) - '0';
        return sign * result;
    }
    }

