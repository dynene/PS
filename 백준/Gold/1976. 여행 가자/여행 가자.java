import java.util.*;
import java.io.*;

/*
240611
BJ1976 여행 가자 - 골4
 */

public class Main {

    static int n, m;
    static int[][] adjMatrix;
    static int[] route;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        m = Integer.parseInt(in.readLine());

        adjMatrix = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; ++j) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                if (j == i) adjMatrix[i][j] = 1;
            }
        }

        st = new StringTokenizer(in.readLine());
        route = new int[m];
        for (int i = 0; i < m; ++i) {
            route[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        
        for (int i = 0; i < m - 1; ++i) {
            int from = route[i];
            int to = route[i + 1];

            // from - to가 연결되어 있는지 확인
            if (adjMatrix[from][to] == 1) {
                continue;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            boolean visited[] = new boolean[n];
            visited[from] = true;
            queue.add(from);

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == to) {
                    adjMatrix[from][to] = 1;
                    break;
                } else {
                    for (int a = 0; a < n; ++a) {
                        if (adjMatrix[cur][a] == 1 && !visited[a]) {
                            queue.add(a);
                            visited[a] = true;
                        }
                    }
                }
            }

            if (adjMatrix[from][to] != 1) {
                System.out.print("NO");
                return;
            }
        }
        
        System.out.print("YES");
    }
}
