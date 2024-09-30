/*
241001
2:48
BJ2636 치즈 - 골4
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    n*m 판
    공기와 닿은 칸은 1시간 뒤 녹아 없어짐

    1. 치즈가 모두 녹아 없어지는 데 걸리는 시간
    2. 모두 녹기 1시간 전, 치즈 남아있는 칸 개수
     */

    static int result1;
    static int result2;

    static int n, m;
    static int[][] arr;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int leftCnt = 0;
        arr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) leftCnt++;
            }
        }

        int time = 0;
        int prevLeftCnt = leftCnt;
        while (true) {
            if (leftCnt == 0) {
                result2 = prevLeftCnt;
                break;
            } else {
                prevLeftCnt = leftCnt;
            }

            // 공기와 접촉된 칸 찾아서 기록.
            // 0,0 시작으로 bfs 수행하다가, 처음으로 1을 만나면 그 칸에 체크하고 그 방향은 종료.
            boolean[][] air = new boolean[n][m];
            List<Node> airs = new ArrayList<>();
            boolean[][] visited = new boolean[n][m];

            Queue<Node> queue = new ArrayDeque<>();
            queue.add(new Node(0, 0));
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                for (int i = 0; i < 4; ++i) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) { // 범위 내이고 + 탐색 안 한 칸이라면
                        visited[nx][ny] = true;
                        // 치즈 위치한 칸인지 확인
                        if (arr[nx][ny] == 1) {
                            if (!air[nx][ny]) {
                                air[nx][ny] = true; // 공기 닿은 곳 체크
                                airs.add(new Node(nx, ny));
                            }
                        } else {
                            queue.add(new Node(nx, ny));
                        }
                    }
                }
            }

            // airs에 담긴 공기 닿은 칸.. 치즈 녹음..
            for (Node node : airs) {
                arr[node.x][node.y] = 0;
                leftCnt--;
            }

            time++;
        }

        result1 = time;
        System.out.println(result1);
        System.out.println(result2);
    }
}
