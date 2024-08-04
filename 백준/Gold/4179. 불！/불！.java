import java.util.*;
import java.io.*;

/*
240801
BJ4179 불! - 골3
1초, 256MB
 */

public class Main {

    static int r, c;

    static char[][] maze;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int result;

    static class Status {
        int time;
        int jx;
        int jy;

        Status(int time, int jx, int jy) {
            this.time = time;
            this.jx = jx;
            this.jy = jy;
        }
    }

    static class Fire {
        int x;
        int y;
        int time;

        Fire(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int sx = 0;
        int sy = 0;

        /*
        # : 벽
        . : 빈칸
        J : 초기위치 (빈칸)
        F : 불타는중..
         */

        maze = new char[r][c]; // 1000*1000

        Queue<Fire> queue2 = new ArrayDeque<>();
        for (int i = 0; i < r; ++i) {
            String str = in.readLine();
            for (int j = 0; j < c; ++j) {
                maze[i][j] = str.charAt(j);
                if (maze[i][j] == 'J') {
                    sx = i;
                    sy = j;
                    maze[i][j] = '.';
                } else if (maze[i][j] == 'F') {
                    queue2.add(new Fire(i, j, 0));
                }
            }
        }

        /*
        매 분마다...
        지훈, 불: 상하좌우 중 한 칸 이동 / 벽 못 넘음
        불: 상하좌우 네 칸으로 확산

        탈출 조건: 지훈이가 미로 가장자리에 도착
        가장 빠른 탈출시간 구하기
         */

        boolean[][] visited = new boolean[r][c];
        visited[sx][sy] = true;

        Queue<Status> queue = new ArrayDeque<>();
        queue.add(new Status(1, sx, sy));

        int time = 0;
        boolean found = false;
        while (!queue.isEmpty() && !found) {
            // 시간 증가 & 불 확산시키기
            time++;
            while (!queue2.isEmpty() && queue2.peek().time == time - 1) {
                Fire f = queue2.poll();
                for (int i = 0; i < 4; ++i) {
                    int nx = f.x + dx[i];
                    int ny = f.y + dy[i];
                    if (nx >= 0 && nx < r && ny >= 0 & ny < c) {
                        if (maze[nx][ny] == '.') {
                            maze[nx][ny] = 'F';
                            queue2.add(new Fire(nx, ny, time));
                        }
                    }
                }
            }

            while (!queue.isEmpty() && !found && queue.peek().time == time) {
                Status cur = queue.poll();

                // 이동한 위치가 미로 가장자리인 경우 다음 차례에 탈출시키고 종료
                if ((cur.jx == 0 || cur.jx == r - 1) || (cur.jy == 0 || cur.jy == c - 1)) {
                    result = time;
                    found = true;
                    break;
                }

                for (int i = 0; i < 4; ++i) {
                    int nx = cur.jx + dx[i];
                    int ny = cur.jy + dy[i];
                    if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                        if (!visited[nx][ny] && maze[nx][ny] == '.') { // 이동 가능한 칸인 경우
                            // 가장자리 아닌 경우
                            visited[nx][ny] = true;
                            queue.add(new Status(time + 1, nx, ny));

                        } else {
                            // 이동 불가능한 칸.
                        }
                    }
                }
            }
        }

        if (result == 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }
    }
}
