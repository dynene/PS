import java.util.*;
import java.io.*;

/*
240907
14:20
BJ2240 자두나무 - 골5
 */

public class Main {

    /*
    t초동안 매 초 1 또는 2에서 자두 떨어짐
    최대 w번의 이동으로, 받을 수 있는 자두 개수 최댓값
     */

    static int t, w;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        t = Integer.parseInt(st.nextToken()); // 최대 1000
        w = Integer.parseInt(st.nextToken()); // 최대 30

        int[][][] dp = new int[t + 1][3][w + 1];

        int[] fall = new int[t + 1];
        for (int i = 1; i <= t; ++i) {
            fall[i] = Integer.parseInt(in.readLine());
        }

        if (fall[1] == 1) {
            // 1초에 위치 1에서 이동 0으로 받는 자두 +1
            dp[1][1][0] = 1;
        } else {
            // 1초에 위치 2에서 이동 1으로 받는 자두 +1
            dp[1][2][1] = 1;
        }

        for (int i = 2; i <= t; ++i) {
            // t초동안...
            if (fall[i] == 1) { // i초에 나무 1에서 떨어짐
                // i-1초에 위치 1에 있을 때의 값들에 자두 +1
                // i-1초에 위치 2에 있을 때의 값들에 이동 수 +1해서 자두 +1
                for (int j = 0; j <= w; ++j) {
                    if (j == 0) {
                        dp[i][1][j] = dp[i - 1][1][j] + 1;
                        dp[i][2][j] = dp[i - 1][2][j];
                    } else {
                        dp[i][1][j] = Math.max(dp[i - 1][1][j] + 1, dp[i - 1][2][j - 1] + 1);
                        dp[i][2][j] = Math.max(dp[i - 1][1][j - 1], dp[i - 1][2][j]);
                    }
                }
            } else { // i초에 나무 2에서 떨어짐
                // i-1초에 위치 1에 있을 때의 값들에 이동 수 +1해서 자두 +1
                // i-1초에 위치 2에 있을 때의 값들에 자두 +1
                for (int j = 0; j <= w; ++j) {
                    if (j == 0) {
                        dp[i][1][j] = dp[i - 1][1][j];
                        dp[i][2][j] = dp[i - 1][2][j] + 1;
                    } else {
                        dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][2][j - 1]);// 못 받
                        dp[i][2][j] = Math.max(dp[i - 1][1][j - 1] + 1, dp[i - 1][2][j] + 1);// 받음
                    }
                }
            }
        }

        // w초일 때의 최댓값...
        int result = 0;
        for (int i = 1; i <= 2; ++i) {
            for (int j = 0; j <= w; ++j) {
                result = Math.max(result, dp[t][i][j]);
            }
        }

        System.out.println(result);

    }
}
