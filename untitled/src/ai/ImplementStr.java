package ai;


//문자열 두개가 주어졌을 때 haystack 안에 needle 문자열의 위치를 구하는 문제입니다.
//Java 의 indexOf 함수를 직접 구현한다고 생각하면 됩니다.


public class ImplementStr {

    public int strStr(String haystack, String needle) {

        if (haystack.equals(needle)){
            return 0;
        }

        for (int i = 0 ; i<haystack.length()-needle.length()+1;i++){
            if (haystack.substring(i,i+needle.length()).equals(needle)){
                return i;
            }
        }
        return -1;
    }

}
