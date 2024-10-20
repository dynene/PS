/*
241020
BJ9084 동전 - 골5
 */

import java.util.*;
import java.io.*;

public class  Main {

    static int t;
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(in.readLine());
        for (int z = 0; z < t; ++z) {
            n = Integer.parseInt(in.readLine());
            st = new StringTokenizer(in.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; ++i) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            m = Integer.parseInt(in.readLine()); // 만들어야 할 금액

            // dp[i] = j
            // i = i원을 만드는 방법의 수
            int[] dp = new int[m + 1];
            dp[0] = 1;
            for (int i = 0; i < n; ++i) {
                for (int j = arr[i]; j <= m; ++j) {
                    dp[j] += dp[j - arr[i]];
                }
            }

            sb.append(dp[m]).append("\n");

        }
        System.out.print(sb);
    }
}
