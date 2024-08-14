import java.util.*;
import java.io.*;

/*
240814
BJ2302 극장 좌석 - 실1
 */

public class Main {

    static int n; // 전체 좌석 수
    static int m; // 고정석 개수

    static boolean[] vip;
    static int[] dp;

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        m = Integer.parseInt(in.readLine());
        vip = new boolean[n + 1];
        for (int i = 0; i < m; ++i) {
            vip[Integer.parseInt(in.readLine())] = true;
        }

        dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            if (vip[i] || vip[i - 1]) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);
    }

}
