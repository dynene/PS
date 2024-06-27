import java.util.*;
import java.io.*;

/*
240627
BJ14940 쉬운 최단거리 - 실1
 */


public class Main {

    static int n, m;
    //static int[][] arr;
    static int[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x;
        int y;
        int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int sx = 0;
        int sy = 0;
        //arr = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; ++j) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 0) {
                    visited[i][j] = 0;
                } else {
                    if (tmp == 2) {
                        sx = i;
                        sy = j;
                    }
                    visited[i][j] = -1;
                }
            }
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(sx, sy, 0));
        visited[sx][sy] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (visited[nx][ny] == -1) {
                        visited[nx][ny] = cur.d + 1;
                        queue.add(new Node(nx, ny, cur.d + 1));
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                sb.append(visited[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);

    }
}
