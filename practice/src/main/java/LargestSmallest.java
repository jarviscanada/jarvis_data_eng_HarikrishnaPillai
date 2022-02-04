import java.util.Arrays;
public class LargestSmallest {
    public int[] FinderLS(int a[]){
        int max = Arrays.stream(a).max().getAsInt();
        int min = Arrays.stream(a).min().getAsInt();
        int[] arr = new int[]{min,max};
        return arr;
    }
}
