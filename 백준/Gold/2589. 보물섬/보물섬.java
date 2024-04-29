import java.util.*;
import java.io.*;

/*
240429
BJ2589 보물섬 - 골드 5
 */

public class Main {

    /*
    육지 L 바다 W
    보물: 서로 간에 '최단 거리'로 이동하는데 있어 '가장 긴 시간'이 걸리는 '육지' 두 곳에 나뉘어 묻혀있다.
    보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간 구하기

    모든 육지에 대해, 거기서부터 시작해서 거리 visited에 기록하기
    visited에 찍힌 최대 거리값이 답 (근데 1초 안에 이게 됨?)
     */

    static class Node {
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    static int n, m; // 세로, 가로
    static char[][] map;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            for (int j = 0; j < m; ++j) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (map[i][j] == 'L') { // 육지인 경우
                    // bfs 보내보기 ...
                    Queue<Node> queue = new ArrayDeque<>();
                    visited = new int[n][m];
                    visited[i][j] = 1;
                    queue.add(new Node(i, j, 1));

                    while (!queue.isEmpty()) {
                        Node cur = queue.poll();

                        if (cur.dist > result) {
                            result = cur.dist;
                        }
                        for (int a = 0; a < 4; ++a) {
                            int nx = cur.x + dx[a];
                            int ny = cur.y + dy[a];
                            if (nx >= 0 && ny >= 0 && nx < n && ny < m) { // 범위 내 값이면
                                if (map[nx][ny] == 'L' && visited[nx][ny] == 0) {
                                    visited[nx][ny] = cur.dist + 1;
                                    queue.add(new Node(nx, ny, cur.dist + 1));
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(result - 1);
    }
}
