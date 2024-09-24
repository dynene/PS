/*
240924
2:30
BJ17144 미세먼지 안녕! - 골4
 */

import java.util.*;
import java.io.*;

public class Main {

    /*
    1초 동안 일어나는 일
    1. 미세먼지 확산(미세먼지 존재하는 모든 칸에서 동시에)
        - 인접 상하좌우로 확산
        - 인접 방향에 공기청정기가 있거나, 칸이 없으면 확산 x
        - 확산되는 양 = arr[i][j]/5 (소수점 버림)
        - 남은 미세먼지 양 = arr[i][j] - (arr[i][j]/5 * 확산된 방향의 개수)
    2. 공기청정기 작동
        - 위쪽: 바람 반시계반향 순환 / 아래쪽: 바람 시계방향 순환
        - 바람 붊 -> 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
        - 공기청정기로 들어간 미세먼지는 모두 정화
     */

    static int r, c, t;
    static int[][] arr;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; // 상 하 좌 우

    static int p1 = -1;
    static int p2 = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = new int[r][c];
        for (int i = 0; i < r; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < c; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == -1) { // 공기청정기
                    if (p1 == -1) p1 = i; // 무조건 j는 0일 것...
                    else p2 = i;
                }
            }
        }

        for (int a = 0; a < t; ++a) { // t초동안 반복
            // 1. 미세먼지 확산

            boolean[][] check = new boolean[r][c];
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j) {
                    // 미세먼지 있는 곳 체크
                    if (arr[i][j] > 0) check[i][j] = true;
                }
            }

            int[][] spread = new int[r][c];
            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j) {
                    if (check[i][j]) { // 미세먼지가 있는 칸이라면
                        // 확산
                        int spreadCnt = 0;
                        for (int k = 0; k < 4; ++k) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (nx >= 0 && nx < r && ny >= 0 && ny < c && arr[nx][ny] != -1) { // 범위 내 칸이고, 공청기 없다면
                                spreadCnt++;
                                spread[nx][ny] += arr[i][j] / 5;
                            }
                        }
                        // 확산 끝 => arr[i][j]에 남은 양 계산
                        arr[i][j] -= arr[i][j] / 5 * spreadCnt;
                    }
                }
            }

            for (int i = 0; i < r; ++i) {
                for (int j = 0; j < c; ++j) {
                    arr[i][j] += spread[i][j];
                }
            }

            // 2. 공기청정기 작동

            // 2-1. 위쪽 반시계방향 순환
            /*
            r: 0~purifier1까지 / c: 0~c까지
            하나씩 옮기려면 [purifier1-1][0]부터 하나씩...
             */
            for (int i = p1 - 1; i >= 0; --i) {
                if (i == p1 - 1) continue;
                arr[i + 1][0] = arr[i][0];
            }
            for (int i = 1; i <= c - 1; ++i) {
                arr[0][i - 1] = arr[0][i];
            }
            for (int i = 1; i <= p1; ++i) {
                arr[i - 1][c - 1] = arr[i][c - 1];
            }
            for (int i = c - 2; i >= 0; --i) {
                if (i == 0) {
                    arr[p1][i + 1] = 0;
                } else {
                    arr[p1][i + 1] = arr[p1][i];
                }
            }

            // 2-2. 아래쪽 시계방향 순환
            /*
            r: purifier2~r-1까지 / c: 0~c까지
             */
            for (int i = p2 + 1; i < r; ++i) {
                if (i == p2 + 1) continue;
                arr[i - 1][0] = arr[i][0];

            }
            for (int i = 1; i < c; ++i) {
                arr[r - 1][i - 1] = arr[r - 1][i];
            }

            for (int i = r - 2; i >= p2; --i) {
                arr[i + 1][c - 1] = arr[i][c - 1];
            }

            for (int i = c - 2; i >= 0; --i) {
                if (i == 0) {
                    arr[p2][i + 1] = 0;
                } else {
                    arr[p2][i + 1] = arr[p2][i];
                }
            }
        }

        // 남은 미세먼지 합 구하기
        int result = 0;
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                result += arr[i][j];
            }
        }

        System.out.println(result + 2); // 공기청정기 때문에 빠진 -2 더해줌
    }
}
