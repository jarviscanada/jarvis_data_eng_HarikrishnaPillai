public class SwapArray<i> {
    public int[] SwapMethod(int beta[]){
        beta[0]=beta[0]+beta[1];
        beta[1]=beta[0]-beta[1];
        beta[0]=beta[0]-beta[1];
        return beta;
    }
}
