/*
241026
BJ14442 벽 부수고 이동하기 2 - 골3
 */

import java.util.*;
import java.io.*;

public class Main {

    static int n, m, k;
    static int[][] arr;

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cnt;
        int dist;

        Node(int x, int y, int cnt, int dist) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            for (int j = 0; j < m; ++j) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        // 0.0에서 n-1.m-1으로 이동하기...
        // k번 벽 부수기 가능
        // 근데 1000*1000... ㅎ

        if(n==1 && m==1) {
            System.out.println(1);
            return;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][][] visited = new boolean[n][m][k + 1];
        pq.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 0이라면, 안 부수고 가보기
                    if (arr[nx][ny] == 0 && !visited[nx][ny][cur.cnt]) {
                        if (nx == n - 1 && ny == m - 1) {
                            System.out.println(cur.dist + 1);
                            return;
                        }
                        visited[nx][ny][cur.cnt] = true;
                        pq.add(new Node(nx, ny, cur.cnt, cur.dist + 1));
                    }

                    // 1이라면, 횟수 남았다면 부수고 가보기
                    else {
                        if (cur.cnt < k && !visited[nx][ny][cur.cnt + 1]) {
                            if (nx == n - 1 && ny == m - 1) {
                                System.out.println(cur.dist + 1);
                                return;
                            }
                            visited[nx][ny][cur.cnt + 1] = true;
                            pq.add(new Node(nx, ny, cur.cnt + 1, cur.dist + 1));
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
