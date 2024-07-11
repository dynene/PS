import java.util.*;
import java.io.*;

/*
RE
240711
BJ15989 1,2,3 더하기 4 - 골5
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[10001][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= 10000; ++i) {
            dp[i][1] = dp[i - 3][1];
            dp[i][2] = dp[i - 2][2] + dp[i - 2][1];
            dp[i][3] = dp[i - 3][3] + dp[i - 3][2] + dp[i - 3][1];
        }

        for (int z = 1; z <= t; ++z) {
            int n = Integer.parseInt(in.readLine());
//            int result = 0;

            // n을 1, 2, 3의 합으로 나타내는 방법의 수
            // 정답 sb에 넣기
            sb.append(dp[n][1]+dp[n][2]+dp[n][3]).append("\n");
        }

        System.out.print(sb);
    }
}
