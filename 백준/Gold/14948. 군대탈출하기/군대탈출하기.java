import java.util.*;
import java.io.*;

/*
240512
BJ14948 군대탈출하기 - 골드 2
 */

public class Main {

    /*
    레벨 제한 최댓값이 10^9네...
     */

    static int n, m;
    static int[][] map;
    static int[][][] visited;

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우 ~
    static int[] dy = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int jumped;
        int maxlv;

        Node(int x, int y, int jumped, int maxlv) {
            this.x = x;
            this.y = y;
            this.jumped = jumped;
            this.maxlv = maxlv;
        }

        @Override
        public int compareTo(Node o) {
            return o.maxlv - this.maxlv;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new int[n][m][2]; // [][][0]: 패스 X [][][1]: 패스 O

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j][0] = visited[i][j][1] = -1;
            }
        }

        visited[0][0][0] = map[0][0];

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0, map[0][0]));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
//            int lv = visited[cur.x][cur.y][cur.jumped];
//            visited[cur.x][cur.y][cur.jumped] = Math.max(visited[cur.x][cur.y][cur.jumped], cur.maxlv);

            for (int i = 0; i < 4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 한 칸 이동하는 경우.
                    int nmaxlv = Math.max(map[nx][ny], cur.maxlv); //이동할 좌표에서 필요한 최대레벨

                    if (visited[nx][ny][cur.jumped] == -1 || visited[nx][ny][cur.jumped] > nmaxlv) { // 방문한 적 없거나 필요최대레벨이 갱신됐으면
                        // 거기로 가봄 ..
                        visited[nx][ny][cur.jumped] = nmaxlv;
                        queue.add(new Node(nx, ny, cur.jumped, nmaxlv));
                    }

                    if (cur.jumped == 0) { // 패스해본 적 없는 경우 > 패스도 시켜봐야 함
                        nx += dx[i]; // 이동 방향으로 한 칸씩 더 보내기
                        ny += dy[i];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                            nmaxlv = Math.max(map[nx][ny], cur.maxlv); // 패스한 좌표에서 필요한 최대 레벨

                            if (visited[nx][ny][1] == -1 || visited[nx][ny][1] > nmaxlv) {
                                visited[nx][ny][1] = nmaxlv;
                                queue.add(new Node(nx, ny, 1, nmaxlv));
                            }
                        }
                    }
                }
            }

        }

        System.out.print(Math.min(visited[n - 1][m - 1][0], visited[n - 1][m - 1][1]));

    }
}
