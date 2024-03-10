import java.util.*;
import java.io.*;

/*
240308
BOJ17985 Maaaaaaaaaaaaaaaaaaaaaaaaaaaze 골드 2

2트 ㅡㅡ
아오 ㅡㅡ
 */

public class Main {

    /*
    5*5 보드판 5개
    각 보드판 회전하기
    54321으로 가면 되는 거 아님 ??
     */

    static int[][][] maze;
    static int[] dx = {0, 0, -1, 1, 0, 0}; //북 남 서 동 상 하
    static int[] dy = {-1, 1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int[][] start = {{0, 0}, {4, 0}, {0, 4}, {4, 4}};
    static int[][] finish = {{4, 4}, {0, 4}, {4, 0}, {0, 0}};

    static List<int[][]> boardList;

    static int minMove;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        maze = new int[5][5][5];
        minMove = Integer.MAX_VALUE;
        boardList = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            int[][] board = new int[5][5];
            for (int j = 0; j < 5; ++j) {
                st = new StringTokenizer(in.readLine());
                for (int k = 0; k < 5; ++k) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            boardList.add(board);
        }

        int[] p = {0, 1, 2, 3, 4};
        do {
            // 현재 상태에서의 maze 만들기~
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 5; ++j) {
                    for (int k = 0; k < 5; ++k) {
                        maze[j][k][i] = boardList.get(p[i])[j][k];
                    }
                }
            }

            // 각 보드 회전시키기
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    for (int k = 0; k < 4; ++k) {
                        for (int l = 0; l < 4; ++l) {
                            // maze에서 출발점 지정하기 (출발점, 도착점 중 0 있으면 continue;)

                            // 출발점 후보 => maze[0][0][0] / maze[4][0][0] / maze[0][4][0] / maze[4][4][0]
                            // 도착점 후보 => maze[4][4][4] / maze[0][4][4] / maze[4][0][4] / maze[0][0][4]


                            for (int a = 0; a < 4; ++a) {
                                int sx, sy, sz, fx, fy, fz;
                                sx = start[a][0];
                                sy = start[a][1];
                                sz = 0;
                                fx = finish[a][0];
                                fy = finish[a][1];
                                fz = 4;

                                // 출발점, 도착점 중 이동 불가한 칸 있으면 continue;
                                if (maze[sx][sy][sz] == 0 || maze[fx][fy][fz] == 0) continue;

                                int tmp = simulate(sx, sy, sz, fx, fy, fz);

                                if (tmp < minMove) {
                                    minMove = tmp;
                                }
                            }
                            rotate90(0);
                        }
                        rotate90(1);
                    }
                    rotate90(2);
                }
                rotate90(3);
            }
        } while (np(p));

        if (minMove == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minMove);

    }

    static class Status {
        int x;
        int y;
        int z;
        int prevDir; // 0~5
        int moveCnt;

        Status(int x, int y, int z, int prevDir, int moveCnt) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.prevDir = prevDir;
            this.moveCnt = moveCnt;
        }
    }

    static boolean visited[][][];
//    static int tmpMinMove;

    static int simulate(int sx, int sy, int sz, int fx, int fy, int fz) {
        // maze[sx][sy][sz] 에서 maze[fx][fy][fz] 로 이동하는 경로 존재하면 해당 경로 길이
        // 존재하지 않으면 Integer.MAX_VALUE;

        visited = new boolean[5][5][5];
//        tmpMinMove = Integer.MAX_VALUE;
//        visited[sx][sy][sz] = true;
//        dfs(sx, sy, sz, fx, fy, fz, 0);

        Queue<Status> queue = new ArrayDeque<>();

        queue.offer(new Status(sx, sy, sz, -1, 0));
        visited[sx][sy][sz] = true;

        while (!queue.isEmpty()) {
            Status cur = queue.poll();

            if (cur.moveCnt > minMove) break;
            if (cur.moveCnt > 126) break;

            for (int i = 0; i < 6; ++i) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];

                // 범위 벗어나는 경우
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || nz < 0 || nz >= 5) continue;

                // 끝점 도착한 경우 !!!!!!
                if (nx == fx && ny == fy && nz == fz) {
                    return cur.moveCnt + 1;
                }

                // 이동 불가한 칸인 경우
                if (maze[nx][ny][nz] == 0) continue;

                //  북0 남1 서2 동3 상4 하5
                if (cur.prevDir == -1) {
                    // do nothing
                } else if (i % 2 == 0) {
                    if (cur.prevDir - 1 == i) continue;
                } else {
                    if (cur.prevDir + 1 == i) continue;
                }

                // 다음 탐색 해야 하는 경우...
                if (!visited[nx][ny][nz]) {
                    queue.offer(new Status(nx, ny, nz, i, cur.moveCnt + 1));
                    visited[nx][ny][nz] = true;
                }
            }
        }

        return Integer.MAX_VALUE;
    }

//    static void dfs(int x, int y, int z, int fx, int fy, int fz, int moveCnt) {
//        if (x == fx && y == fy && z == fz) {
//            if (tmpMinMove > moveCnt) {
//                tmpMinMove = moveCnt;
//            }
//            return;
//        }
//
//        if (moveCnt > minMove) return;
//
//        for (int i = 0; i < 6; ++i) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//            int nz = z + dz[i];
//
//            // 범위 벗어나는 경우
//            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || nz < 0 || nz >= 5) continue;
//
//            if (visited[nx][ny][nz]) continue;
//            // 이동 불가한 칸인 경우
//            if (maze[nx][ny][nz] == 0) continue;
//
//            visited[nx][ny][nz] = true;
//            dfs(nx, ny, nz, fx, fy, fz, moveCnt + 1);
//            visited[nx][ny][nz] = false;
//        }
//    }

    static void rotate90(int z) { // board를 시계방향 90도 회전
        /*
        1   2   3   4   5
        6   7   8   9   10
        11  12  13  14  15
        16  17  18  19  20
        21  22  23  24  25

        21 16 11 6 1
        22 17 12 7 2
        23 18 13 8 3
        ...
         */

        int[][] after = new int[5][5];

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                after[j][4 - i] = maze[i][j][z];
            }
        }

        // after의 내용을 maze[i][j][z]에 옮기기
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                maze[i][j][z] = after[i][j];
            }
        }
    }

    static boolean np(int[] p) {
        int n = 5;

        int i = n - 1;
        while (i > 0 && p[i - 1] >= p[i]) --i;

        if (i == 0) return false;

        int j = n - 1;
        while (p[i - 1] >= p[j]) --j;
        swap(p, i - 1, j);

        int k = n - 1;
        while (i < k) swap(p, i++, k--);

        return true;
    }

    static void swap(int[] p, int a, int b) {
        int tmp = p[a];
        p[a] = p[b];
        p[b] = tmp;
    }

}