/*
RE
241026
BJ1727 커플 만들기 - 골2
https://velog.io/@shinjy9802/%EB%B0%B1%EC%A4%80-1727-%EC%BB%A4%ED%94%8C-%EB%A7%8C%EB%93%A4%EA%B8%B0-Java
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    dp, 그리디, 정렬
     */

    static int n, m;
    static int[] man;
    static int[] woman;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        man = new int[n];
        woman = new int[m];

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; ++i) {
            man[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < m; ++i) {
            woman[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(man);
        Arrays.sort(woman);


        int[][] dp = new int[n + 1][m + 1]; // dp[i][j] = 남자는 i까지, 여자는 j까지 고려했을 때의 최적값

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (i == j) {
                    //
                    dp[i][j] = dp[i - 1][j - 1] + Math.abs(man[i - 1] - woman[j - 1]);
                } else if (i > j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1] + Math.abs(man[i - 1] - woman[j - 1]));
                } else if (i < j) {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - 1] + Math.abs(man[i - 1] - woman[j - 1]));

                }
            }
        }

        System.out.println(dp[n][m]);

    }

}
