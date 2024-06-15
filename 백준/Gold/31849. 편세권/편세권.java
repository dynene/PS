import java.util.*;
import java.io.*;


public class Main {

    /*
    편세권 점수 = (방에서 가장 가까운 편의점까지의 거리 × 월세)
    방(a,b)에서 편의점(c,d)까지의 거리 |a-c|+|b-d|

    편세권 점수 가장 낮은 방의 편세권 점수 구하기
     */

    static int n, m, r, c;
    static int minScore = Integer.MAX_VALUE;

    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    static int[][] map;

    static class Node {
        int x;
        int y;
        int sx;
        int sy;

        Node(int x, int y, int sx, int sy) {
            this.x = x;
            this.y = y;
            this.sx = sx;
            this.sy = sy;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[n + 1][m + 1];
        List<Node> rooms = new ArrayList<>();
        for (int i = 0; i < r; ++i) {
            st = new StringTokenizer(in.readLine());
            // (a,b)에 위치한, 월세 p인 방
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n + 1][m + 1];
        boolean check = false;

        for (int i = 0; i < c; ++i) {
            st = new StringTokenizer(in.readLine());
            // (c,d)에 위치한 편의점
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            queue.add(new Node(c, d, c, d));
            visited[c][d] = true;
        }

        /*
        "방에서 가장 가까운 편의점까지의 거리"
        >"각 편의점에서 방까지의 거리"??

        최단거리 찾기 > bfs?

         */

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int a = 0; a < 4; ++a) {
                int nx = cur.x + dx[a];
                int ny = cur.y + dy[a];
                if (nx > 0 && nx <= n && ny > 0 && ny <= m) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new Node(nx, ny, cur.sx, cur.sy));
                    }
                    if (map[nx][ny] != 0) {
                        int score = map[nx][ny] * (Math.abs(nx - cur.sx) + Math.abs(ny - cur.sy));
                        if (minScore > score) {
                            minScore = score;
                        }
                    }
                }
            }
        }

        System.out.print(minScore);
    }
}
