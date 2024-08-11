import java.util.*;
import java.io.*;

/*
240809
BJ15685 드래곤 커브 - 골3
 */

public class Main {

    /*
    드래곤 커브 1. 시작점 2. 시작방향 3. 세대
    0세대 커브 = 길이가 1인 선분.
    K(K > 1)세대 드래곤 커브 = K-1세대 드래곤 커브를 끝점 기준 90도 시계 방향 회전시켜서 끝점에 붙인 것
    "끝점" = 시작점에서 가장 먼 거리에 있는 점

    100x100 격자에 n개의 드래곤 커브 존재.
    크기가 1x1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수 구하기

    커브 개수 <= 20
    방향 0 우 1 상 2 좌 3 하
     */

    /*
    개수 카운트 조건 = 네 "꼭짓점" 전부 드래곤 커브의 일부인 경우. 네 테두리 모두가 커브로 둘러싸여야 한다는 소리가 아님...
    >> 선분은 신경쓸 필요 없이 드래곤 커브에 포함되는 좌표만 알면 됨.
     */

    /*
    (x.y)를 (0.0) 기준으로 시계방향 90도 회전한 좌표 = (y.-x)
     */

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static boolean[][] arr;

    static int[] dx = {1, 0, -1, 0}; // 우, 상, 좌, 하
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(in.readLine());
        arr = new boolean[101][101];

        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            // 시작 좌표 (x.y)
            arr[y][x] = true;

            // 시작 방향 d > 0세대 표시
            arr[y + dy[d]][x + dx[d]] = true;

            // g 세대... 만큼 반복
            List<Point> list = new ArrayList<>();
            list.add(new Point(x, y));
            list.add(new Point(x + dx[d], y + dy[d]));

            // 회전 기준점 (px.py)
            int nextPx = x + dx[d];
            int nextPy = y + dy[d];

            for (int j = 0; j < g; ++j) {
                int px = nextPx;
                int py = nextPy;

                List<Point> tmpList = new ArrayList<>();
                for (int k = 0; k < list.size(); ++k) {
                    Point p = list.get(k);
                    if (p.x == px && p.y == py) continue; // 끝점과 동일한 경우 패스

                    // 좌표 p를 (px.py) 기준으로 시계방향 90도 회전
                    int xDif = px - p.x;
                    int yDif = py - p.y;
                    int nx = yDif + px;
                    int ny = -xDif + py;

                    arr[ny][nx] = true;
                    tmpList.add(new Point(nx, ny));
                    if (k == 0) {
                        nextPx = nx;
                        nextPy = ny;
                    }
                }
                list.addAll(tmpList);
            }
        }

        int result = 0;
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                if (arr[i][j] && arr[i][j + 1] && arr[i + 1][j] && arr[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        System.out.println(result);

    }
}
