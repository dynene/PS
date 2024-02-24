import java.util.*;
import java.io.*;

/*
240223-4
BOJ 15683 감시 - 골드 4
 */

public class Main {

    static int n, m;
    static int[][] arr;

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1}; // 상 하 좌 우

    static List<Cctv> cctvList;

    static int minnum; // 사각지대 최소 크기 = 답


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 지도 정보 입력
        arr = new int[n][m];
        minnum = Integer.MAX_VALUE;

        // 벽 개수
        int numOfWall = 0;
        cctvList = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < m; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] >= 1 && arr[i][j] <= 5) { // 1~5의 값인 경우
                    cctvList.add(new Cctv(arr[i][j], i, j));
                } else if (arr[i][j] == 6) { // 벽인 경우
                    numOfWall++;
                }
            }
        }

        // cctv 0개
        if (cctvList.isEmpty()) {
            System.out.println(n * m - numOfWall);
            return;
        }

        if (cctvList.get(0).type == 1 || cctvList.get(0).type == 3 || cctvList.get(0).type == 4) {
            for (int i = 0; i < 4; ++i) {
                watch(0, i, arr);
            }
        } else if (cctvList.get(0).type == 2) {
            watch(0, 0, arr);
            watch(0, 2, arr);
        } else if (cctvList.get(0).type == 5) {
            watch(0, 0, arr);
        }

        System.out.println(minnum);
    }

    static void watch(int cur, int dir, int[][] map) {

        Cctv curr = cctvList.get(cur);

        int[][] nmap = new int[n][m];
        for (int i = 0; i < n; ++i) {
            nmap[i] = map[i].clone();
        }

        // 지금 차례에 맞는 거 감시 체크하고..
        if (curr.type == 1) {
            int tmp = 1;
            while (true) {
                int nx = curr.x + (dx[dir] * tmp);
                int ny = curr.y + (dy[dir] * tmp);
                tmp++;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 범위 넘었으면 탈출..
                if (nmap[nx][ny] == 6) break; // 벽이여도 탈출..
                if (nmap[nx][ny] == 0) nmap[nx][ny] = -1; // 범위 안 넘고 빈칸인 경우에만 체크..
                // cctv는 어차피 통과하니까 패스..
            }
        } else if (curr.type == 2) { // 상하 or 좌우
            for (int i = 0; i < 2; ++i) {
                int tmp = 1;
                while (true) {
                    int nx = curr.x + (dx[dir + i] * tmp);
                    int ny = curr.y + (dy[dir + i] * tmp);
                    tmp++;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 범위 넘었으면 탈출..
                    if (nmap[nx][ny] == 6) break; // 벽이여도 탈출..
                    if (nmap[nx][ny] == 0) nmap[nx][ny] = -1; // 범위 안 넘고 빈칸인 경우에만 체크..
                    // cctv는 어차피 통과하니까 패스..
                }
            }
        } else if (curr.type == 3) { // 좌상, 상우, 우하, 하좌
            if (dir == 0) { // 좌상 - 0, 2
                for (int i = 0; i < 4; ++i) {
                    if (i == 0 || i == 2) continue;

                    int tmp = 1;
                    while (true) {
                        int nx = curr.x + (dx[i] * tmp);
                        int ny = curr.y + (dy[i] * tmp);
                        tmp++;

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 범위 넘었으면 탈출..
                        if (nmap[nx][ny] == 6) break; // 벽이여도 탈출..
                        if (nmap[nx][ny] == 0) nmap[nx][ny] = -1; // 범위 안 넘고 빈칸인 경우에만 체크..
                        // cctv는 어차피 통과하니까 패스..
                    }
                }
            } else if (dir == 1) { // 상우 - 0, 3
                for (int i = 0; i < 4; ++i) {
                    if (i == 0 || i == 3) continue;

                    int tmp = 1;
                    while (true) {
                        int nx = curr.x + (dx[i] * tmp);
                        int ny = curr.y + (dy[i] * tmp);
                        tmp++;

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 범위 넘었으면 탈출..
                        if (nmap[nx][ny] == 6) break; // 벽이여도 탈출..
                        if (nmap[nx][ny] == 0) nmap[nx][ny] = -1; // 범위 안 넘고 빈칸인 경우에만 체크..
                        // cctv는 어차피 통과하니까 패스..
                    }
                }
            } else if (dir == 2) { // 우하 - 1, 3
                for (int i = 0; i < 4; ++i) {
                    if (i == 1 || i == 3) continue;

                    int tmp = 1;
                    while (true) {
                        int nx = curr.x + (dx[i] * tmp);
                        int ny = curr.y + (dy[i] * tmp);
                        tmp++;

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 범위 넘었으면 탈출..
                        if (nmap[nx][ny] == 6) break; // 벽이여도 탈출..
                        if (nmap[nx][ny] == 0) nmap[nx][ny] = -1; // 범위 안 넘고 빈칸인 경우에만 체크..
                        // cctv는 어차피 통과하니까 패스..
                    }
                }
            } else if (dir == 3) { // 하좌 - 1, 2
                for (int i = 0; i < 4; ++i) {
                    if (i == 1 || i == 2) continue;

                    int tmp = 1;
                    while (true) {
                        int nx = curr.x + (dx[i] * tmp);
                        int ny = curr.y + (dy[i] * tmp);
                        tmp++;

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 범위 넘었으면 탈출..
                        if (nmap[nx][ny] == 6) break; // 벽이여도 탈출..
                        if (nmap[nx][ny] == 0) nmap[nx][ny] = -1; // 범위 안 넘고 빈칸인 경우에만 체크..
                        // cctv는 어차피 통과하니까 패스..
                    }
                }
            }
        } else if (curr.type == 4) { // 좌상우, 상우하, 우하좌, 하좌상
            for (int i = 0; i < 4; ++i) {
                if (i == dir) continue;

                int tmp = 1;
                while (true) {
                    int nx = curr.x + (dx[i] * tmp);
                    int ny = curr.y + (dy[i] * tmp);
                    tmp++;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 범위 넘었으면 탈출..
                    if (nmap[nx][ny] == 6) break; // 벽이여도 탈출..
                    if (nmap[nx][ny] == 0) nmap[nx][ny] = -1; // 범위 안 넘고 빈칸인 경우에만 체크..
                    // cctv는 어차피 통과하니까 패스..
                }
            }
        } else if (curr.type == 5) { // 무조건 4방향...
            for (int i = 0; i < 4; ++i) { // dir 상관 없이 4방향
                int tmp = 1;
                while (true) {
                    int nx = curr.x + (dx[i] * tmp);
                    int ny = curr.y + (dy[i] * tmp);
                    tmp++;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break; // 범위 넘었으면 탈출..
                    if (nmap[nx][ny] == 6) break; // 벽이여도 탈출..
                    if (nmap[nx][ny] == 0) nmap[nx][ny] = -1; // 범위 안 넘고 빈칸인 경우에만 체크..
                    // cctv는 어차피 통과하니까 패스..
                }
            }
        }

        // next cctv의 타입에 맞게 재귀 호출..
        if (cur < cctvList.size() - 1) {
            Cctv next = cctvList.get(cur + 1);

            if (next.type == 1 || next.type == 3 || next.type == 4) {
                for (int i = 0; i < 4; ++i) {
                    watch(cur + 1, i, nmap);
                }
            } else if (next.type == 2) {
                watch(cur + 1, 0, nmap);
                watch(cur + 1, 2, nmap);
            } else if (next.type == 5) {
                watch(cur + 1, -1, nmap);
            }
        } else if (cur == cctvList.size() - 1) { // 재귀 종료 시점! minnum 체크!
            int cnt = 0;
            // 0의 개수(사각지대 수) 세기
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (nmap[i][j] == 0) {
                        cnt++;
//                        if (cnt > minnum) return;
                    }
                }
            }
            if (minnum > cnt) minnum = cnt;
            return;
        }
    }


    static class Cctv {
        int type;
        int x, y;

        Cctv(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
}
