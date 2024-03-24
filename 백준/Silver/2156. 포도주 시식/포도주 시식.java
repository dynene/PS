import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
240324
BJ2156 포도주 시식 - 실버 1
 */

public class Main {

    /*
    포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마신다.
    연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
     */

    /*
    dp[i] = i번째 잔까지 고려했을 때 마실 수 있는 최대 포도주 양
     */

    static int n;
    static int[] arr;
    static Asdf[] dp;

    static class Asdf {
        int stack;
        int maxamount;

        Asdf(int stack, int maxamount) {
            this.stack = stack;
            this.maxamount = maxamount;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(in.readLine());
        }

        dp = new Asdf[n + 1];
        dp[0] = new Asdf(0, 0);
        dp[1] = new Asdf(1, arr[1]);

        if (n > 1) {
            dp[2] = new Asdf(2, dp[1].maxamount + arr[2]);
        }

        for (int i = 3; i <= n; ++i) {
            if (dp[i - 1].stack == 2) { // 앞에 두 잔을 마신 경우
                // 앞에 두 잔 중 하나를 제거하고 이번 잔을 마시는 게 더 이득인지 확인하기
                // 1. 이번 잔을 안 마시기
                int tmp1 = dp[i - 1].maxamount;
                // 2. i-2번째를 안 마시고 이번 잔을 마시기
                int tmp2 = dp[i - 3].maxamount + arr[i - 1] + arr[i];
                // 3. i-1번째를 안 마시고 이번 잔을 마시기
                int tmp3 = dp[i - 2].maxamount + arr[i];

                if (tmp1 < tmp2) {
                    if (tmp2 < tmp3) {
                        // tmp3 최대
                        dp[i] = new Asdf(1, tmp3);
                    } else {
                        // tmp2 최대
                        dp[i] = new Asdf(2, tmp2);
                    }
                } else {
                    if (tmp1 < tmp3) {
                        // tmp3 최대
                        dp[i] = new Asdf(1, tmp3);
                    } else {
                        // tmp1 최대
                        dp[i] = new Asdf(0, tmp1);
                    }
                }
            } else {
                dp[i] = new Asdf(dp[i - 1].stack + 1, dp[i - 1].maxamount + arr[i]);
            }
        }
        
        System.out.println(dp[n].maxamount);
    }
}
