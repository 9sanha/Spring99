package java;

import java.util.Arrays;
import java.util.Scanner;

public class No1 {

    //2중 for문 안쓰고 도전
//문제 - https://leetcode.com/problems/two-sum/

    public static int[] twoSum(int[] nums, int target) {
        //정답 인덱스 찾는 용
        // 얕은 복사 (주소값 복사)
//        int[] serchArray = nums;
        int[] serchArray = nums.clone();
        int[] result = new int[2];
        int indexStart = 0;
        int indexEnd = nums.length - 1;
        //while
        boolean isFind = false;
        //오름차순 정렬
        Arrays.sort(nums);
        while (!isFind) {
            int sum = nums[indexEnd] + nums[indexStart];
            if (indexEnd == indexStart) { //모든 연산 후 없으면 반복 중단
                isFind = true;
            } else if (sum > target) { // 계산값이 target 보다 클 때
                indexEnd -= 1;
            } else if (sum < target) { // 계산값이 target 보다 작을 때
                indexStart += 1;
            } else {
                result[0] = nums[indexEnd];
                result[1] = nums[indexStart];
                isFind = true;
            }
        }
        // 정렬이 된 배열에만 정상작동
//        result[0]=Arrays.binarySearch(serchArray,result[0]);
//        result[1]=Arrays.binarySearch(serchArray,result[1]);
        int[] last = new int[2];
        int j = 0;
        boolean issame = false;
        for (int i = 0; i < serchArray.length; i++) {
            if (j == 2) {
                break;
            }
            if (serchArray[i] == result[0] && !issame) {
                last[0] = i;
                j++;
                issame = true;
            } else if (serchArray[i] == result[1]) {
                last[1] = i;
                j++;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int target;
        int[] nArray;
        String[] strArray;
        Scanner sc = new Scanner(System.in);

        System.out.println("배열에 들어갈 정수를 입력해주세요.");
        System.out.println("예) [1,2,3]일 때: 1 2 3");
        System.out.print("입력: ");
        //앞 뒤 공백 제거
        String inputString = sc.nextLine().trim();

        System.out.print("target 입력: ");
        target = sc.nextInt();

        //공백으로 분리해서 배열에 넣음
        strArray = inputString.split(" ");

        //int array로 형변환
        nArray = Arrays.stream(strArray).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(nArray));
        System.out.println(Arrays.toString(twoSum(nArray, target)));


    }

}
