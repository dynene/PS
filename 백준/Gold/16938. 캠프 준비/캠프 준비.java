import java.util.*;
import java.io.*;

/*
240514
BJ16938 캠프 준비 - 골5

시간 제한 2초
메모리 제한 512MB
 */

public class Main {

    /*
     * N문제 중 2문제 이상을 조건 지키면서 출제하는 경우의 수 구하기.
     * 출제 문제들의 전체 난이도 합 L 이상 R 이하
     * 최고 난이도 - 최저 난이도 >= X
     *
     * 문제 최대 15문제
     * 문제별 난이도 최대 10^6
     */

    static int n, l, r, x;
    static int[] arr;

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        if (n == 1) {
            System.out.print(0);
            return;
        }

        arr = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 2개부터 15개까지 뽑아보기 ..
        // 매 경우마다 조건 체크하기 ...

        for (int i = 2; i <= n; ++i) { // 2개부터 n개까지 ..
            int[] p = new int[n];
            for (int j = n - 1; j >= n - i; --j) {
                p[j] = 1;
            }

//            System.out.println(i);
//
//            // 지금 np가 먼가 이상함 ..

            do {
//                for (int j = 0; j < n; ++j) {
//                    if (p[j] == 0)
//                        System.out.print(p[j] + " ");
//                    else
//                        System.out.print(arr[j] + " ");
//                }
//                System.out.println();

                boolean check = true;
                // 현재 p[] 대로 문제 출제 시 조건 만족하는지 확인

                // 난이도 합 L 이상 R 이하
                int sum = 0;
                int min = 0;
                for (int j = 0; j < n; ++j) {
                    if (p[j] == 1) {
                        sum += arr[j];
                        if (min == 0) min = arr[j];
                    }
                }
                if (l > sum || r < sum) {
                    check = false;
                }

                // 최고난도 - 최저난도 >= X
                int max = 0;
                for (int j = n - 1; j >= 0; --j) {
                    if (p[j] == 1) {
                        max = arr[j];
                        break;
                    }
                }
                if (max - min < x) {
                    check = false;
                }

                // 조건 만족하는 경우 result에 추가
                if (check) result++;
            } while (np(p)); // 다음 경우 찾기
        }


        System.out.print(result);
    }

    static boolean np(int[] p) {
        int i = n - 1;
        while (i > 0 && p[i - 1] >= p[i]) --i;
        if (i == 0) return false;

        int j = n - 1;
        while (p[i - 1] >= p[j]) --j;
        swap(p, i - 1, j);

        int k = n - 1;
        while (i < k) swap(p, i++, k--);

        return true;
    }

    static void swap(int[] p, int a, int b) {
        int tmp = p[a];
        p[a] = p[b];
        p[b] = tmp;
    }

}
