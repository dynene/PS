import java.util.*;
import java.io.*;

public class Main {

    static int n;

    // 9901로 나눈 나머지 출력

    // 4일 때 41가지 경우...
    /*
    0 -> 왼 / 오 / x
    1 -> (왼) 오 / x (오) 왼 / x (x) 왼 / 오 / x
    왼 / 오 / x
    왼 오 / 왼 x / 오 왼 / 오 x / x 왼 / x 오 / x x

     */

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        dp = new int[n];

        dp[0] = 3;

        if (n == 1) {
            System.out.println(dp[0]);
            return;
        }

        dp[1] = 7;

        for (int i = 2; i < n; ++i) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 9901;
        }


        System.out.println(dp[n - 1]);
    }
}
