import java.util.*;
import java.io.*;

/*
240303
BJ 7579 앱 - 골드 3

0/1 knapsack
DP
 */

public class Main {

    /*
    활성화된 앱 n개 A[1]~A[n]
    앱 A[i]가 사용하는 메모리 m[i] 바이트
    앱 A[i] 비활성화 뒤 재실행 시 추가 비용 C[i]

    일부 앱 비활성화하면서 메모리 M 바이트를 확보해야 할 때,
    이때의 C[i] 합의 최솟값 구하기
    */

    /*
    '메모리' 기준으로 dp 돌리면 메모리 최댓값이 100만이라 메모리 터짐
    최댓값 100인 '비용' 기준으로 돌려야 함

    dp[앱이 어디까지 입력되었는지][비용] == 해당 비용으로 확보할 수 있는 메모리 값

    dp[][]의 값 = 확보가능한 최대 메모리 크기
        i → 몇 번째까지 입력된 앱에 대한 계산인지?
        j → 비용
    */

    static int n, m;
    static int[] memory;
    static int[] cost;

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int answer = Integer.MAX_VALUE;

        st = new StringTokenizer(in.readLine());
        StringTokenizer st2 = new StringTokenizer(in.readLine());
        memory = new int[n];
        cost = new int[n];
        for (int i = 0; i < n; ++i) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
        }

        dp = new int[n][100001]; // dp[앱 개수][모든 앱을 다 비활성화할 때의 비용 최댓값]

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 100000; j++) {
                if (i == 0) { // 첫 번째 행인 경우 -> j가 i번째 앱의 cost보다 크면 해당 앱의 memory 값을 담는다
                    if (j >= cost[i])
                        dp[i][j] = memory[i];
                } else { // 첫 번째 행이 아닌 경우
                    if (j >= cost[i]) // j가 c 이상이면 최댓값 계산해서 넣기
                        // 이전 행에서 c비용 안쓰고 확보한 메모리 값 + 이번 행에서 c비용으로 확보한 메모리 VS 이전 행에서 같은 비용 써서 확보한 메모리
                        dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + memory[i], dp[i - 1][j]);
                    else // j가 i번째 앱의 cost 미만인 경우, 이전 행 값을 그대로 가져와 넣기
                        dp[i][j] = dp[i - 1][j];
                }

                // m바이트 이상의 메모리를 확보하는 최소의 비용 구하기ㅠㅠ
                if (dp[i][j] >= m)
                    answer = Math.min(answer, j);

            }
        }

        System.out.println(answer);
    }
}
