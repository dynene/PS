/*
241105
18:10
BJ2665 미로만들기 - 골4
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    n*n 판에서
    검은방=0=이동불기
    흰방=1=이동가능
    시작방(0,0)에서 (n-1,n-1)으로 이동하려 할때,
    검은방을 흰방으로 최소 개수만큼만 바꿔서 이동 가능하게 만들기
     */

    static class Node {
        int x;
        int y;
        int change;

        Node(int x, int y, int change) {
            this.x = x;
            this.y = y;
            this.change = change;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int n;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            for (int j = 0; j < n; ++j) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0));
        boolean[][][] visited = new boolean[n][n][101];
        visited[0][0][0] = true;

        int minChange = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (nx == n - 1 && ny == n - 1) {
                        if (cur.change < minChange) {
                            minChange = cur.change;
                        }
                    }

                    if (arr[nx][ny] == 0) { // 벽
                        if (cur.change<100 && !visited[nx][ny][cur.change + 1]) {
                            // 보내보기...
                            visited[nx][ny][cur.change + 1] = true;
                            queue.add(new Node(nx, ny, cur.change + 1));
                        }
                    } else { // 이동 가능
                        if (!visited[nx][ny][cur.change]) {
                            visited[nx][ny][cur.change] = true;
                            queue.add(new Node(nx, ny, cur.change));
                        }
                    }
                }
            }
        }

        System.out.println(minChange);
    }
}
