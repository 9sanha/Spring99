package ai;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int end=digits.length-1;
        if (digits[end]!=9){
            digits[end]++;
            return digits;
        }
        int up=1;
        for ( int i = end; i >= 0 ; i-- ){
            if (digits[i]+up>=10 && 1 < i){
                digits[i]=0;
            }else{
                digits[i]+=up;
                return digits;
            }
        }
        int[] arr = new int[end+1];
        arr[0] = 1;
        for (int digit : digits) {
            arr[up] = digit;
            up++;

        }
        return arr;
        // 0번째에 1 추가
    }
}
