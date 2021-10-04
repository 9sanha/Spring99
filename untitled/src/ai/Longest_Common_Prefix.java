package ai;

public class Longest_Common_Prefix {
    public String Longest_Common_Prefix(String[] strs){

        int end = 1;
        if (strs.length==1){return strs[0];}

        while (strs.length != end){//
            for (String str : strs) { // 배열에서 하나씩 가져오기
                String cmp = strs[0].substring(0,end); // 첫번째 원소로 비교
                 if (-1==str.indexOf(cmp)){// for문으로 가져온 원소에 cmp(첫번째 원소의 end-1번째 글자가 없으면)
                     return strs[0].substring(0,end-1); // 지금까지 구한 같은 부분만 리턴
                 }
            }
            end++;
        }
        return strs[0].substring(0,end);

    }
}
