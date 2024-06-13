import java.util.*;
import java.io.*;

/*
240613
BJ2169 로봇 조종하기 - 골2
 */

public class Main {

    // 0.0 출발, n-1.m-1 도착까지 탐사 지역 가치 합 최대로 만들기
    // 최대 1000*1000 ...

    static int n, m;
    static int[][] arr;
    static int[][] visited;
    static int[][] tmp;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new int[n + 1][m + 1];
        arr = new int[n + 1][m + 1];
        tmp = new int[2][m + 2];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= m; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 안 하면 시간초과가 나겠지...
        visited[1][1] = arr[1][1];
        for (int i = 2; i <= m; ++i) {
            visited[1][i] = visited[1][i - 1] + arr[1][i];
        }

        for (int i = 2; i <= n; ++i) {
            tmp[0][0] = visited[i - 1][1];
            for (int j = 1; j <= m; ++j) {
                tmp[0][j] = Math.max(tmp[0][j - 1], visited[i - 1][j]) + arr[i][j];
            }

            tmp[1][m + 1] = visited[i - 1][m];
            for (int j = m; j >= 1; j--) {
                tmp[1][j] = Math.max(tmp[1][j + 1], visited[i - 1][j]) + arr[i][j];
            }

            for (int j = 1; j <= m; ++j) {
                visited[i][j] = Math.max(tmp[0][j], tmp[1][j]);
            }
        }
        System.out.print(visited[n][m]);
    }

}
