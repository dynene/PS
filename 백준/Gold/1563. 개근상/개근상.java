import java.util.*;
import java.io.*;

/*
240814
17:00
BJ1563 개근상 - 골4
 */

public class Main {

    static int n;
    static int MOD = 1000000;

    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        dp = new long[n + 1][2][3]; // [i일][i일까지의 지각 횟수: 0, 1][i일의 연속 결석 횟수: 0, 1, 2]

        dp[1][0][0] = 1; // 지각0 결석0 - 출석
        dp[1][0][1] = 1; // 지각0 결석1 - 결석
        dp[1][0][2] = 0; // 지각0 결석2 - 불가능
        dp[1][1][0] = 1; // 지각1 결석0 - 지각
        dp[1][1][1] = 0; // 지각1 결석1 - 불가능
        dp[1][1][2] = 0; // 지각1 결석2 - 불가능

        if (n > 1) {
            dp[2][0][0] = 2; // 2일차 지각0 결석0일째 - 출 출, 결 출}
            dp[2][0][1] = 1; // 2일차 지각0 결석1일째 - 출 결
            dp[2][0][2] = 1; // 2일차 지각0 결석2일째 - 결 결
            dp[2][1][0] = 3; // 2일차 지각1 결석0일째 - 출 지, 결 지, 지 출
            dp[2][1][1] = 1; // 2일차 지각1 결석1일째 - 지 결
            dp[2][1][2] = 0; // 2일차 지각1 결석2일째 - 불가능

            for (int i = 3; i <= n; ++i) {
                dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD; // i일차 지각0 결석0일째 =
                dp[i][0][1] = dp[i - 1][0][0]; // i일차 지각0 결석1일째 = 오늘은 무조건 결석. i-1일차 지각0 결석0일째
                dp[i][0][2] = dp[i - 1][0][1]; // i일차 지각0 결석2일째 = 오늘은 무조건 결석. i-1일차 지각0 결석1일째
                dp[i][1][0] = (dp[i - 1][1][2] + dp[i - 1][1][1] + dp[i - 1][1][0] + dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD; // i일차 지각1 결석0일째 = i-1일차.. 지각1 결석2일째 + 지각1 결석1일째 + 지각1 결석0일째 + 지각0 결석0일째 + 지각0 결석1일째 + 지각0 결석2일째
                dp[i][1][1] = dp[i - 1][1][0]; // i일차 지각1 결석1일째 = i-1일차 지각1 결석0일째
                dp[i][1][2] = dp[i - 1][1][1]; // i일차 지각1 결석2일째 = i-1일차 지각1 결석1일째
            }
        }

        System.out.println((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] + dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % MOD);
    }
}
