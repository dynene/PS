import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
240408 틀 / 240414
BJ1520 내리막길 - 골드 3
 */

public class Main {

    /*
    제일 왼쪽 위 지점에서 출발하여 제일 오른쪽 아래 지점까지 "항상 내리막길로만 이동"하는 경로의 개수
     */

    static int n, m; // n: 세로(x) m: 가로(y)
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // dp...
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        dp[n - 1][m - 1] = 1;
        int result = dfs(0, 0);

        System.out.println(result);
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        int tmp = 0;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                // 범위 안의 값인 경우
                if (arr[nx][ny] < arr[x][y]) {
                    tmp += dfs(nx, ny);
                }
            }
        }

        dp[x][y] = tmp;
        return tmp;

    }
}