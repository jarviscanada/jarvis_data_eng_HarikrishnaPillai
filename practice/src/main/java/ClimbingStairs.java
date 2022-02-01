public class ClimbingStairs {
    public int RClimb(int n) {
        // path can be reached
        if(n == 0)
            return 1;
        // path cannot be reached
        if(n < 0)
            return 0;
        return RClimb(n - 1) + RClimb(n - 2);
    }
    public int DpClimb(int n){
        if(n==0){
            return 1;
        }
        if(n==1|| n==2){
            return n;
        }
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]+=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
