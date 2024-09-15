/*
240915
BJ1757 달려달려 - 골4
https://blog.naver.com/kerochuu/222375177205 풀이 참고
 */

import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] d;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        d = new int[n+1];
        for (int i = 1; i <= n; ++i) {
            d[i] = Integer.parseInt(in.readLine());
        }

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; ++i) {
            // 1. i분에 안 달린 경우
            dp[i][0] = dp[i - 1][0];

            // 2. i분에 달린 경우...
            for (int tired = 1; tired <= m; tired++) {
                dp[i][tired] = dp[i - 1][tired - 1] + d[i];
            }

            // 3. dp[i][0]의 최댓값 구하기 (dp[i-1][1], dp[i-2][2], dp[i-3][3]...)
            for (int tired = 1; tired <= m && i > tired; tired++) {
                dp[i][0] = Math.max(dp[i][0], dp[i - tired][tired]);
            }
        }

        System.out.println(dp[n][0]);
    }
}
