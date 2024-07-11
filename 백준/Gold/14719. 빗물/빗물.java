import java.util.*;
import java.io.*;

/*
240711
BJ14719 빗물 - 골5
 */

public class Main {

    static int h, w;
    static int[] arr;

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());

        arr = new int[w];
        for (int i = 0; i < w; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        /*
        앞에서부터 보고 뒤에서부터 보면서... 자신보다 높은 값이 나오면 갱신
        현재 칸에 고이는 빗물 칸 수 = Math.min(왼쪽 최댓값, 오른쪽 최댓값) - 현재 칸 높이
         */
        for (int i = 0; i < w; ++i) {
//            System.out.println("<" + i + ">");

            // leftMax 갱신 - 나보다 크면서 이전 값보다 작아지지 않아야 함
            int leftMax = arr[i];
            int leftIdx = i;
            while (leftIdx > 0 && leftIdx < w - 1) {
                if (arr[leftIdx - 1] > leftMax) {
                    leftMax = arr[leftIdx - 1];
                }
                leftIdx--;
            }

            // rightMax 갱신
            int rightMax = arr[i];
            int rightIdx = i;
            while (rightIdx < w - 1 && rightIdx > 0) {
                if (arr[rightIdx + 1] > rightMax) {
                    rightMax = arr[rightIdx + 1];
                }
                rightIdx++;
            }

//            System.out.println("leftMax " + leftMax + " leftIdx " + leftIdx + " : rightMax " + rightMax + " rightIdx " + rightIdx);

            // result 더하기
//            System.out.println("result+ " + (Math.min(leftMax, rightMax) - arr[i]));
            result += Math.min(leftMax, rightMax) - arr[i];
        }

        System.out.print(result);
    }
}
