import java.util.HashMap;

class TwoSum {
    public int[] MapTwoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int req = target - nums[i];
            if (map.containsKey(nums[i]))
                return new int[]{map.get(nums[i]), i};
            map.put(req, i);
        }
        return null;
    }
    public int[] BruteTwoSum(int[] nums, int target) {
        for (int x = 1; x < nums.length; x++) {
            for (int y = x; y < nums.length; y++) {
                if (nums[x] == target - nums[x - y]) {
                    return new int[]{x - y, x};
                }
            }
        }
        return null;
    }
}