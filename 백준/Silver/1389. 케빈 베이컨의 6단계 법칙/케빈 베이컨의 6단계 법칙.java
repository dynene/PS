import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
240331
BJ1389 케빈 베이컨의 6단계 법칙 - 실버 1
 */

public class Main {

    static int n, m;
    static boolean[][] adjArr;
    //    static boolean[] visited;
    static int result;

    static class Status {
        int cur;
        int dist;
        boolean[] visited;

        Status(int cur, int dist, boolean[] visited) {
            this.cur = cur;
            this.dist = dist;
            this.visited = visited;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjArr = new boolean[n + 1][n + 1];
        result = 0;

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjArr[a][b] = true;
            adjArr[b][a] = true;
        }

        // 그냥 .. dfs를 버리자 .. :<
        int mindist = Integer.MAX_VALUE;

        for (int i = 1; i <= n; ++i) { // 출발점
            int sumofdist = 0;

            for (int j = 1; j <= n; ++j) { // 도착점
                if (i == j) continue;

                Queue<Status> queue = new ArrayDeque<>();
                boolean[] visited = new boolean[n + 1];
                visited[i] = true;
                queue.offer(new Status(i, 0, visited));

                while (!queue.isEmpty()) {
                    Status status = queue.poll();

                    if (status.cur == j) {
                        sumofdist += status.dist;
                        break;
                    }

                    for (int a = 1; a <= n; ++a) {
                        if (status.visited[a]) continue;
                        if (adjArr[status.cur][a]) {
                            status.visited[a] = true;
                            queue.offer(new Status(a, status.dist + 1, status.visited));
                        }
                    }
                }
            }

            if (sumofdist < mindist) {
                result = i;
                mindist = sumofdist;
            }
        }

        System.out.println(result);


    }
}