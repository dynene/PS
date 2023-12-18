import java.util.*;
import java.io.*;

/*
BOJ 14888 연산자 끼워넣기
실버 1
2초 / 512MB

시간 ms
메모리 KB
 */

public class Main {

    /*
    N개 수로 이루어진 수열 A
    수 사이에 넣을 연산자 N-1개 : + - * /
    숫자 순서 바꾸지 않고, 숫자와 숫자 사이에 연산자 N-1개 끼워넣기
    이렇게 만들 수 있는 식의 결과가 최대인 것/최소인 것 각각 구하기..

    값 범위 int 이내

     */

    static int n;
    static int[] a;
    static int[] cnt;
    static int N;

    static int minres;
    static int maxres;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());

        a = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(in.readLine());
        cnt = new int[4];
        int[] p = new int[n - 1];
        for (int i = 0; i < 4; ++i) {
            cnt[i] = Integer.parseInt(st.nextToken());

            for (int j = N; j < N + cnt[i]; ++j) {
                p[j] = i;
            }

            N += cnt[i];
        }

        minres = Integer.MAX_VALUE;
        maxres = Integer.MAX_VALUE * (-1);

        do {
            // 내가 완탐을.. 해도 될까?
            int cur = a[0];
            for (int i = 0; i < n - 1; ++i) {
                if (p[i] == 0) { // 덧셈
                    cur += a[i + 1];
                } else if (p[i] == 1) { // 뺄셈
                    cur -= a[i + 1];
                } else if (p[i] == 2) { // 곱셈
                    cur *= a[i + 1];
                } else if (p[i] == 3) { // 나눗셈
                    cur /= a[i + 1];
                }
            }

            if (minres > cur) minres = cur;
            if (maxres < cur) maxres = cur;

        } while (np(p));


        System.out.println(maxres);
        System.out.print(minres);
    }

    static boolean np(int[] p) {
        int i = N - 1;
        while (i > 0 && p[i - 1] >= p[i]) --i;

        if (i == 0) return false;

        int j = N - 1;
        while (p[i - 1] >= p[j]) --j;
        swap(p, i - 1, j);

        int k = N - 1;
        while (i < k) swap(p, i++, k--);

        return true;
    }

    static void swap(int[] p, int a, int b) {
        int tmp = p[a];
        p[a] = p[b];
        p[b] = tmp;
    }
}
