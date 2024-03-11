import java.util.*;
import java.io.*;

/*
240307
BJ 2294 동전 2 - 골드 5
 */

public class Main {

    static int n, k;
    //    static int[] value;
//    static int[][] dp;
//    static int result;
    static int[] dp;
    static int[] coin;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
//        value = new int[n];
//        result = -1;

        coin = new int[n];

        for (int i = 0; i < n; ++i) {
            coin[i] = Integer.parseInt(in.readLine());
        }

        /*
        i: i번째 동전까지 고려했을 때
        j: j원을 만들 수 있는 최소 동전의 수 = dp[i][j]...
         */

        // dp[i] = coin[0~i]들로 k원을 만들 수 있는 동전의 최소 개수
        /*
        사실상 dp의 값이 변경되는 부분은 j= coin[i]일 때 부터이다.
        dp는 1차원 배열로 주어진 동전(coin[i])의 금액과 j원을 만들 수 있는 동전의 개수를 저장한다.
         */

        dp = new int[k + 1];
        for (int i = 1; i <= k; ++i) {
            dp[i] = 10000001;
        }

        for (int i = 0; i < n; ++i) {
            for (int j = coin[i]; j <= k; ++j) {
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }

        if (dp[k] == 10000001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }

}
