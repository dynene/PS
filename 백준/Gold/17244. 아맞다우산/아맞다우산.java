import java.util.*;
import java.io.*;

/*
RE
240901
17:30
BJ17244 아맞다우산 - 골2
 */

public class Main {

    /*
    비트마스킹 부분 풀이 참고함.
    비트마스킹 개념 복습하고 다른 문제 더 풀어보기...
     */

    /*
    Integer.bitCount(x) : x를 2진수로 변환했을 때 1의 개수를 반환
    */

    static class Node {
        int x;
        int y;
        int dist;
        int check;

        Node(int x, int y, int dist, int check) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.check = check;
        }
    }

    static int n, m;
    static char[][] arr;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int sx = 0;
        int sy = 0;

        arr = new char[m][n];
        int cnt = 0;
        for (int i = 0; i < m; ++i) {
            String str = in.readLine();
            for (int j = 0; j < n; ++j) {
                char c = str.charAt(j);
                arr[i][j] = c;
                if (c == 'S') {
                    sx = i;
                    sy = j;
                    arr[i][j] = '.';
                } else if (c == 'X') {
                    arr[i][j] = (char) ('0' + cnt++);
                }
            }
        }

        boolean[][][] visited = new boolean[m][n][(1 << cnt)];
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(sx, sy, 0, 0));
        visited[sx][sy][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (arr[nx][ny] == '#' || visited[nx][ny][cur.check]) {
                        continue;
                    } else if (arr[nx][ny] == '.') {
                        // 찾으러 보내기
                        visited[nx][ny][cur.check] = true;
                        queue.add(new Node(nx, ny, cur.dist + 1, cur.check));
                    } else if (arr[nx][ny] == 'E') {
                        // 종점을 발견함. 물건 다 찾았다면 여기서 탈출.
                        if (cnt == Integer.bitCount(cur.check)) {
                            System.out.println(cur.dist + 1);
                            return;
                        }
                    } else {
                        // 뭔가 물건을 찾음. 체크한 후 다음 탐색 보내기.
                        int curNum = arr[nx][ny] - '0'; // cur.check에 curNum을 추가 표시하기
                        int nextCheck = cur.check | (1 << curNum);
                        visited[nx][ny][nextCheck] = true;
                        queue.add(new Node(nx, ny, cur.dist + 1, nextCheck));
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
