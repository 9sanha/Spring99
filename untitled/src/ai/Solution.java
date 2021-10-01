package ai;



import java.util.ArrayList;

class Solution {
    public static void main(String[] args) {
    boolean a = isPalindrome(20);
    }
    public static boolean isPalindrome(int x) {

        if(x<0||(x%10==0&&x!=0)){return false;}

        ArrayList<Integer> arr = new ArrayList<Integer>();
        int start = x;

        boolean gogo = true;
        while (gogo){
            gogo = start/10!=0;
            arr.add(start%10); // 나머지는 배열에
            start/=10;
        }

        int len = arr.size();
        for (int i =0;i<len/2;i++){
            if (arr.get(i) != arr.get(len-(i+1))){
                return false;
            }
        }

        return true;

    }
}