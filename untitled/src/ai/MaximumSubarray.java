package ai;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        boolean a =false;
        int j = 0;
        if (nums.length==1){
            return 1;
        }

        for(int i=1; i<nums.length-4; i++) {
            if (nums[i]+nums[i+1]>nums[i+2]){
                sum += nums[i+2];
                j=sum;
                a=true;
            }else if (a){
                sum = 0;

            }


        }

        return maxSum;


    }
}
