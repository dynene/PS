import java.util.*;
import java.io.*;

/*
240528
BJ17484 진우의 달 여행(Small) - 실3
 */

public class Main {

    static int n, m;
    static int[][] arr;

    static int[] dx = {1, 1, 1}; // 좌하, 하, 우하
    static int[] dy = {-1, 0, 1};

    static int minCost;

    static class Node {
        int prevDir;
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost, int prevDir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.prevDir = prevDir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minCost = Integer.MAX_VALUE;

        for (int i = 0; i < m; ++i) {
            bfs(0, i);
        }

        System.out.print(minCost);
    }

    static void bfs(int sx, int sy) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(sx, sy, arr[sx][sy], -1));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 3; ++i) {
                if (cur.prevDir == i) continue;

                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (nx == n - 1) {
                        // 달에 도착
                        if (cur.cost + arr[nx][ny] < minCost) {
                            minCost = cur.cost + arr[nx][ny];
                        }
                    } else {
                        queue.add(new Node(nx, ny, cur.cost + arr[nx][ny], i));
                    }
                }

            }
        }
    }
}
