/*
241010
BJ2467 용액 - 골5
 */

import java.util.*;
import java.io.*;

public class Main {

    static int n; // 10만
    static int[] arr; // -10억~10억

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());

        st = new StringTokenizer(in.readLine());
        arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /*
        산성 +
        알칼리성 -

        혼합 후 특성값 = 혼합에 쓰인 용액 특성값의 합
        두 용액 혼합하여, 특성값이 0에 가장 가까운 용액 만들기
         */

        if (n == 2) {
            System.out.println(arr[0] + " " + arr[1]);
            return;
        }

        int minSum = Integer.MAX_VALUE;
        int[] minIdx = new int[2];

        int left = 0;
        int right = n - 1;
        while (left<right) {
            if (Math.abs(arr[left] + arr[right]) < minSum) {
                minSum = Math.abs(arr[left] + arr[right]);
                minIdx[0] = left;
                minIdx[1] = right;
            }

            if (arr[left] + arr[right] < 0) {
                // 0보다 작은 경우, 더 큰 수를 더해줘야 0에 가까워질 것
                left++;
            } else {
                right--;
            }

        }

        System.out.println(arr[minIdx[0]] + " " + arr[minIdx[1]]);
    }
}