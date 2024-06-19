import java.util.*;
import java.io.*;

/*
240619
BJ3687 성냥개비 - 골2
 */

public class Main {

    /*
    2개 = 1
    3개 = 7
    4개 = 4
    5개 = 2
    6개 = 6 || 0
    7개 = 8
     */

    /*
    최댓값 - 무조건 자릿수 클수록 유리 -> 숫자를 많이 만들어야 최댓값에 도달 가능.
    최솟값 - 최대한 적은 개수의 숫자 만들기
          - 하나 만들 때 최대한 작은 수로 만들기
          - 전부 0이면 안 됨 (1 이상인 수가 1개 이상 있어야 함)
          - 0이 아닌 가장 작은 수를 맨 앞에 배치 + 그 후 오름차순 배치

    2~100개의 성냥개비 -> 각각 경우에서의 최솟값을 모두 구해두고, dp[n]으로 접근?
    dp를 어케하는디

    남은 성냥개비 개수 1이 되지 않도록, 2~7개 이내에서 계속해서 숫자를 만듦
     */

    static int t;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // 2~100까지의 최솟값 모두 구하기
        long[] dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10; // 2 + 6 = 1, 0

        int[] val = {-1, -1, 1, 7, 4, 2, 0, 8};
        for (int i = 9; i <= 100; ++i) {
            for (int j = 2; j <= 7; ++j) {
                StringBuilder sb = new StringBuilder();
                sb.append(dp[i-j]).append(val[j]);
                dp[i] = Math.min(Long.parseLong(sb.toString()), dp[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(in.readLine()); // 최대 100
        for (int z = 1; z <= t; ++z) {
            int n = Integer.parseInt(in.readLine()); // 2~100 사이의 n

            // 최댓값 구하기
            /*
            만들 수 있는 만큼의 1을(2개 소모) 만들고 난 뒤, 나머지가 3이면 1 하나 빼고 7을 만듦
             */
            int numOf1 = n / 2;
            int numOf7 = 0;
            if (n % 2 == 1) {
                numOf1--;
                numOf7++;
            }
            StringBuilder maxSb = new StringBuilder();
            if (numOf7 == 1) maxSb.append(7);
            for (int i = 0; i < numOf1; ++i) {
                maxSb.append(1);
            }

            sb.append(dp[n]).append(" ").append(maxSb.toString()).append("\n");
        }

        System.out.print(sb);
    }
}