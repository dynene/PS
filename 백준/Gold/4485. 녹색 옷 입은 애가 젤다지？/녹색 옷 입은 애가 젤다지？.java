import java.util.*;
import java.io.*;

/*
RE... ㅠ_ㅠ
240628
BJ4485 녹색 옷 입은 애가 젤다지? - 골4
 */

public class Main {

    static int n;
    static int[][] arr;
    static int[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    /*
    0,0 에서 n-1, n-1까지 이동하는 최소 비용
     */

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cost > o.cost) {
                return 1;
            } else if (this.cost == o.cost) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) break;
            else cnt++;

            int result = 0;

            arr = new int[n][n];
            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < n; ++j) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new int[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
            }

            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.add(new Node(0, 0, arr[0][0]));
            visited[0][0] = arr[0][0];

            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                if (cur.x == n - 1 && cur.y == n - 1) {
                    break;
                }

                for (int i = 0; i < 4; ++i) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        // 원래 값보다 작은 값이라면 갱신 후 큐에 넣음
                        if (visited[nx][ny] > cur.cost + arr[nx][ny]) {
                            visited[nx][ny] = cur.cost + arr[nx][ny];
                            queue.add(new Node(nx, ny, visited[nx][ny]));
                        }
                    }
                }
            }

            result = visited[n - 1][n - 1];

            sb.append("Problem ").append(cnt).append(": ").append(result).append("\n");
        }

        System.out.print(sb);
    }
}
