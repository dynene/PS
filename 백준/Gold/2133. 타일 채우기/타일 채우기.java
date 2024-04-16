import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
240416
BJ2133 타일 채우기 - 골드 4
 */
public class Main {

    // 3*n 을 1*2와 2*1 타일로 채우는 경우의 수
    // n 최대 30

    static int n;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());

        if (n % 2 == 1) {
            System.out.println(0);
            return;
        } else if (n == 2) {
            System.out.println(3);
            return;
        }

        dp = new int[n + 1];

        // 여기서 n은 무조건 짝수임 ..
        dp[2] = 3;
        dp[4] = 11;
        int sum = dp[2];
        for (int i = 4; i <= n; i += 2) {
            dp[i] = sum * 2 + dp[i-2] + 2;
            sum += dp[i];
        }

        System.out.println(dp[n]);
    }
}
