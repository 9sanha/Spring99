package ai;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums.length==0){
            return 0;
        }//[1,2,3,4,6,2,3,3] 3
        int n=0;
        int b=nums.length-1;
        int temp;
        boolean end = false;
        while (!end){
            if(nums[n]==val){
                if (nums[b]==val&&n!=b-1){
                    b--;
                }
                temp=nums[n];
                nums[n]=nums[b];
                nums[b]=temp;
                b--;
            }
            n++;
            if (n==b){
                end=true;
            }
        }
        return b;
    }
}
