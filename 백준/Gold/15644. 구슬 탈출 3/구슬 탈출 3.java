import java.util.*;
import java.io.*;

/*
240225
BJ 5644 구슬 탈출 3 - 골드 1
 */

public class Main {
    
    /*
    n*m
    가장자리 # 으로 막혀 있음
    빨간 구슬 1, 파란 구슬 1
    파란 구슬이 구멍에 들어가지 않게 하면서 빨간 구슬을 구멍으로 빼내기!

    각각의 동작에서 공은 동시에 움직인다.
    파란 구슬이 구멍에 빠지면 실패이다.
    빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다.
    빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다.
    
    구슬 이동: 상 하 좌 우 U D L R
    보드 상태 주어질 때, 최소 몇 번만에 + 어떤 경로로 뺄 수 있는지 구하기
    10번 이하로 빼낼 수 없는 경우 -1 출력하고 종료

    . 빈 칸
    # 장애물/벽
    O 구멍 (1개)
    R 빨간 구슬 (1개)
    B 파란 구슬 (1개)
    */

    static int n, m;
    static char[][] arr;

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};
    static char[] charDir = {'U', 'D', 'L', 'R'};
    static int[] checkPrevDir = {1, -1, 1, -1};

    static int rx, ry, bx, by, ox, oy; // R, B, O의 위치 기록... 해야 하나?

    static class Status {
        int dir;
        int depth;
        int rx, ry, bx, by;
        String path;
        char[][] map;

        Status(int depth, int dir, int rx, int ry, int bx, int by, char[][] map, String path) {
            this.depth = depth;
            this.dir = dir;
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.map = map;
            this.path = path;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];

        for (int i = 0; i < n; ++i) {
            String str = in.readLine();
            for (int j = 0; j < m; ++j) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'O') {
                    ox = i;
                    oy = j;
                } else if (arr[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (arr[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        // 최소 횟수 -> bfs?ㅇ?ㅅ?ㅇ?
        // 이전 이동에 따른 현 map의 상태 + 이전에 이동했던 방향 + 현재 호출 depth

        Queue<Status> queue = new ArrayDeque<>();
        for (int i = 0; i < 4; ++i) {
            // 다음 상태에 넣기 위해, 현재의 arr 상태를 복제하여 저장
            char[][] arr2 = new char[n][m];
            for (int j = 0; j < n; ++j) {
                arr2[j] = arr[j].clone();
            }
            queue.add(new Status(1, i, rx, ry, bx, by, arr2, String.valueOf(charDir[i])));
        }

        while (!queue.isEmpty()) {
            Status cur = queue.poll();

            boolean foundPath = false;
            boolean failed = false;

            int prx = cur.rx;
            int pry = cur.ry;
            int pbx = cur.bx;
            int pby = cur.by;

            // 입력된 방향에 대해, 벽에 부딪히거나/구멍을 만날 때까지 해당 방향으로 나아가기
            boolean Rfinish = false;
            boolean Bfinish = false;
            while (true) { // 한 칸씩 이동시켜야 하나? ... 그냥 갈 수 있는 곳까지 한 번에 이동시켜야 하나 ?ㅠㅠ ㅋㅋ
                if (Rfinish && Bfinish) break;

                // 해야 하는 거: R이 먼저 빠진 후에 B가 빠질 수 있는지 확인해야 함
                // R이 빠진 후에... B에 대해 체크해야 할 것 :

                int nrx = cur.rx + dx[cur.dir];
                int nry = cur.ry + dy[cur.dir];
                int nbx = cur.bx + dx[cur.dir];
                int nby = cur.by + dy[cur.dir];

                if (cur.map[nrx][nry] == 'O') { // 2. B가 안 빠진다고 할 때, R이 구멍에 빠지나요? // 정답 찾은 경우
                    cur.map[cur.rx][cur.ry] = '.';
                    Rfinish = true;
                    foundPath = true;
                    // 이 후에 failed가 true 되는지도 확인해야 함 ...
                }
                if (cur.map[nbx][nby] == 'O') { // 1. B가 구멍에 빠지나요?
                    failed = true;
                    break;
                }

                if (cur.map[nrx][nry] == 'B') { // R 가려는 곳에 B 있는 경우
                    if (cur.map[nbx][nby] == '.') { // B가 가려는 곳이 비어있으면
                        // 둘 다 이동 가능
                        cur.map[cur.rx][cur.ry] = '.';
                        cur.map[nrx][nry] = 'R';
                        cur.map[cur.bx][cur.by] = '.';
                        cur.map[nbx][nby] = 'B';
                        cur.rx = nrx;
                        cur.ry = nry;
                        cur.bx = nbx;
                        cur.by = nby;
                        continue;
                    } else if (cur.map[nbx][nby] == '#') { // B가 가려는 곳이 막혀 있으면
                        // 둘 다 이동 불가
                        break;
                    }
                }
                if (cur.map[nbx][nby] == 'R') { // B 가려는 곳에 R 있는 경우
                    if (cur.map[nrx][nry] == '.') {
                        cur.map[cur.rx][cur.ry] = '.';
                        cur.map[nrx][nry] = 'R';
                        cur.map[cur.bx][cur.by] = '.';
                        cur.map[nbx][nby] = 'B';
                        cur.rx = nrx;
                        cur.ry = nry;
                        cur.bx = nbx;
                        cur.by = nby;
                        continue;
                    } else if (cur.map[nrx][nry] == '#') {
                        break;
                    }
                }

                if (cur.map[nrx][nry] == '#') { // R 갈 곳 막혀 있나요?
                    // 네
                    Rfinish = true;
                } else if (cur.map[nrx][nry] == '.') {
                    // 아니요 => 이동
                    cur.map[cur.rx][cur.ry] = '.';
                    cur.map[nrx][nry] = 'R';
                    cur.rx = nrx;
                    cur.ry = nry;
                }

                if (cur.map[nbx][nby] == '#') { // B 갈 곳 막혀 있나요?
                    // 네...
                    Bfinish = true;
                } else if (cur.map[nbx][nby] == '.') {
                    // 아니요 => 이동
                    cur.map[cur.bx][cur.by] = '.';
                    cur.map[nbx][nby] = 'B';
                    cur.bx = nbx;
                    cur.by = nby;
                }

            }

            // 중간에 실패 조건 만난 경우: continue;
            if (failed) {
                continue;
            }

            // 경로 발견한 경우 & 구멍에 동시에 빠지지 않는 경우: 출력하고 종료
            if (foundPath) {
                System.out.println(cur.depth);
                System.out.println(cur.path);
                return;
            }

            // 경로 발견 못 했고 + 현재 depth가 10이었던 경우
            // => queue에 더이상 넣지 않음
            if (cur.depth > 10) {
                System.out.println(-1);
                return;
            }

            for (int i = 0; i < 4; ++i) { // 이전에 나아온 방향의 반대 방향으로 돌아가는 경우 제외하고, 세 가지에 대해 queue에 넣기
                // 이전에 나아온 방향의 반대 방향인지 확인...
                if (cur.dir + checkPrevDir[cur.dir] == i) {
                    continue;
                }

                // 다음 상태에 넣기 위해, 현재의 arr 상태를 복제하여 저장
                char[][] map2 = new char[n][m];
                for (int j = 0; j < n; ++j) {
                    map2[j] = cur.map[j].clone();
                }

                // 다음 나아갈 방향까지 path에 추가해서 다음 추가할 탐색에 넣어 보내자...
                StringBuilder tmpsb = new StringBuilder();

                // 현재 탐색에서 이동 못 한 경우
                if (prx == cur.rx && pry == cur.ry && pbx == cur.bx && pby == cur.by) {
                    // do nothing
                } else {
                    tmpsb.append(cur.path).append(charDir[i]);
                    queue.add(new Status(cur.depth + 1, i, cur.rx, cur.ry, cur.bx, cur.by, map2, tmpsb.toString()));
                }
            }
        }

        // queue 탐색이 끝났는데도 경로를 하나도 찾지 못한 경우
        // -1 출력하고 종료
        System.out.println(-1);
    }


}
