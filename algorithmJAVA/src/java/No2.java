package java;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

//범위 = int (-2^31 ~ 2^31-1)
//Input: x = 123
//Output: 321
public class No2 {
    public int reverse(int x) {
                        //123456
        boolean isminus=false;
        if (x<0){
            isminus=true;
            x*=-1;
        }
        String strX = Integer.toString(x);

        StringBuilder sumStr = new StringBuilder();

        String[] splitStrX = strX.split("");
                //{1,2,3,4,5,6}

        int len = splitStrX.length;

        if (Objects.equals(splitStrX[len-1], "0")) {
            if (len == 1) {
                return 0;
            }else {
                for (int i=len-1; i>1;i--){
                    sumStr.append(splitStrX[i]);
                }
                if (isminus){
                    return Integer.parseInt(String.valueOf(sumStr))*-1;
                }
                return Integer.parseInt(String.valueOf(sumStr));

            }
        }

        for (int i=len-1; i>=0;i--){
            sumStr.append(splitStrX[i]);
        }
        if (isminus){
            return Integer.parseInt(String.valueOf(sumStr))*-1;
        }
        return Integer.parseInt(String.valueOf(sumStr));
    }

    public static void main(String[] args) {
        No2 no = new No2();
        System.out.println(no.reverse(1234));
    }



}