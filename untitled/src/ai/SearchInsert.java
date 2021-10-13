package ai;

public class SearchInsert {
    public int searchInsert(int[] nums, int target) {

        int numsLen = nums.length;

        for (int i = 0; i < numsLen; i++){
            if (target == nums[i]){
                return i;
            }else if(target < nums[i]){
                return i;
            }
        }
        return numsLen;
    }

}
