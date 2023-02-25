package com.liyao.miracle;

/**
 * @Author 栗垚
 * @Date 2023/2/10
 */
class Test {
    // find max consecutive sub array sum
    public static int findMaxConsecutiveSum(int[] arr) {
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > maxSum) {
                maxSum = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    // find max nonconsecutive sub array sum


}