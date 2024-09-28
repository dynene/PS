/*
240928
18:25
BJ1726 로봇 - 골3
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    0: 이동 가능 1: 이동 불가한 칸

    로봇 명령 1) 바라보는 방향으로 1/2/3칸 이동
    로봇 명령 2) 왼/오로 90도 회전

    시작 위치+방향 => 도착 위치+방향으로 이동하기 위한 최소 명령 횟수 => bfs
     */
    static int m, n; // 세로 m 가로 n
    static int[][] arr;

    // 우 1 좌 2 하 3 상 4
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Node {
        int x;
        int y; // 로봇 위치 x,y
        int dir; // 로봇 방향 dir : 우 1 좌 2 하 3 상 4
        int cnt; // 명령 횟수

        Node(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[m][n];
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int sx, sy, sdir, fx, fy, fdir;
        // 로봇 출발지점 위치, 방향
        st = new StringTokenizer(in.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;
        sdir = Integer.parseInt(st.nextToken());
        // 로봇 도착지점 위치, 방향
        st = new StringTokenizer(in.readLine());
        fx = Integer.parseInt(st.nextToken()) - 1;
        fy = Integer.parseInt(st.nextToken()) - 1;
        fdir = Integer.parseInt(st.nextToken());


        if(sx==fx && sy==fy && sdir==fdir) {
            System.out.println(0);
            return;
        }

        ///////

        boolean[][][] visited = new boolean[m][n][5]; // 상, 하, 좌, 우 방향으로 위치한 경우 고려

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(sx, sy, sdir, 0)); // 시작 위치에 놓기
        visited[sx][sy][sdir] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
//            System.out.println("x:" + cur.x + " y:" + cur.y + " dir:" + cur.dir + " cnt:" + cur.cnt);

            // 1. 1/2/3칸 이동 명령
            for (int i = 1; i <= 3; ++i) {
                int nx = cur.x + (dx[cur.dir - 1] * i);
                int ny = cur.y + (dy[cur.dir - 1] * i);
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) { // 범위 내에 있고
                    // 경로 도달 가능한지 확인
                    boolean cango = true;
                    for (int a = 1; a <= i; ++a) {
                        if (arr[cur.x + (dx[cur.dir - 1] * a)][cur.y + (dy[cur.dir - 1] * a)] == 0) continue;
                        else {
                            cango = false;
                            break;
                        }
                    }
                    if (!cango) continue;

                    if (visited[nx][ny][cur.dir]) continue; // 이미 방문한 적 있으면 패스

                    // 만약 이동한 곳이 도착점+방향이라면
                    if (nx == fx && ny == fy && cur.dir == fdir) {
                        // 추가 회전 필요 X. 이대로 끝
//                        System.out.println(i + "칸 이동 후 끝");
                        System.out.println(cur.cnt + 1);
                        return;
                    }
                    queue.add(new Node(nx, ny, cur.dir, cur.cnt + 1));
                    visited[nx][ny][cur.dir] = true;
                }
            }

            // 2. 좌/우 회전 명령
            // 동 1 서 2 남 3 북 4
            // 좌 회전: 동1>북4, 서2>남3, 남3>동1, 북4>서2
            int ndir = rotateLeft(cur.dir);
            if (!visited[cur.x][cur.y][ndir]) {
                if (fx == cur.x && fy == cur.y && fdir == ndir) {
//                    System.out.println("좌회전 후 끝");
                    System.out.println(cur.cnt + 1);
                    return;
                } else {
                    queue.add(new Node(cur.x, cur.y, ndir, cur.cnt + 1));
                    visited[cur.x][cur.y][ndir] = true;
                }
            }

            // 우 회전: 동>남3, 서>북4, 남>서2, 북>동1
            ndir = rotateRight(cur.dir);
            if (!visited[cur.x][cur.y][ndir]) {
                if (fx == cur.x && fy == cur.y && fdir == ndir) {
//                    System.out.println("우회전 후 끝");
                    System.out.println(cur.cnt + 1);
                    return;
                } else {
                    queue.add(new Node(cur.x, cur.y, ndir, cur.cnt + 1));
                    visited[cur.x][cur.y][ndir] = true;
                }
            }

        }


    }

    static int rotateLeft(int dir) {
        if (dir == 1) return 4;
        else if (dir == 2) return 3;
        else if (dir == 3) return 1;
        else return 2;
    }

    static int rotateRight(int dir) {
        if (dir == 1) return 3;
        else if (dir == 2) return 4;
        else if (dir == 3) return 2;
        else return 1;
    }
}
