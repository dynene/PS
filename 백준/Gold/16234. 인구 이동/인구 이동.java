import java.util.*;
import java.io.*;

/*
240727
BJ16234 인구 이동 - 골4
 */

public class Main {

    /*
    국경선 공유하는 두 나라 인구차이 L~R 사이라면 > 둘 사이 국경선 하루동안 엶

    국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다
    연합을 이루고 있는 각 칸의 인구수 = (int) (연합의 인구수) / (연합을 이루고 있는 칸의 개수)

    연합을 해체하고, 모든 국경선을 닫는다.
     */

    static int n, l, r;
    static int[][] map;
    static boolean[][] visited;
    static int result;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Country {
        int x;
        int y;

        Country(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }

    static List<Country> unions;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        unions = new ArrayList<>();

        boolean check;
        while (true) {
            // 모든 칸 dfs 탐색
            visited = new boolean[n][n];
            check = false;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (!visited[i][j]) {
                        unions.clear();
                        // 연합 국가 구하고
                        visited[i][j] = true;
                        unions.add(new Country(i, j));
                        dfs(i, j);
                        if (unions.size() >= 2) { // 연합국 2개 이상이면
                            // 연합에 속한 국가끼리 인구 1/n
                            int sum = 0;
                            for (Country c : unions) {
                                sum += map[c.x][c.y];
                            }
                            for (Country c : unions) {
                                map[c.x][c.y] = sum / unions.size();
                            }
                            check = true;
                        } else { // 연합인 나라가 없음
                            // do nothing
                        }
                    }
                }
            }

            if (check) {
                result++;
            } else {
                break;
            }
        }

        System.out.print(result);

    }

    static void dfs(int x, int y) {
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                // 국경선 열린 국가인지 확인
                int dif = Math.abs(map[x][y] - map[nx][ny]);
                if (l <= dif && dif <= r) {
                    visited[nx][ny] = true;
                    unions.add(new Country(nx, ny));
                    dfs(nx, ny);
                }
            }
        }
    }
}
