import java.util.*;
import java.io.*;

/*
240303
BJ 18808 스티커 붙이기 - 골드 3
 */

public class Main {

    /*
    k개의 스티커 각각에 대해...
    1. 다른 스티커와 안 겹치고 노트북 범위 벗어나지 않으면서 스티커 붙일 수 있는 위치 찾기.
        (왼쪽 위부터 우선으로 붙임)
    2. 붙일 곳 없는 경우, 시계방향 90도 회전시킨 후 붙일 위치 다시 탐색
    3. 한 바퀴 돌리는 내내 붙일 곳 못 찾은 경우, 해당 스티커 버리기

    모든 스티커에 대해 위 과정 진행한 뒤, 노트북에서 스티커가 붙은 칸 수 구하기
     */

    static int n, m, k;
    static boolean[][] notebook;
    static int r, c;
    static boolean[][] sticker;

    static int result;

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        result = 0;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        notebook = new boolean[n][m];

        for (int i = 0; i < k; ++i) {
            // i번째 스티커 입력 받기
            st = new StringTokenizer(in.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            sticker = new boolean[r][c];
            for (int a = 0; a < r; ++a) {
                st = new StringTokenizer(in.readLine());
                for (int b = 0; b < c; ++b) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        sticker[a][b] = true;
                    } else {
                        sticker[a][b] = false;
                    }
                }
            }

            // 회전으로 나올 수 있는 네 가지 모양에 대해
            for (int j = 0; j < 4; ++j) {
                // 1. 붙일 곳 찾기
                int sx = -1;
                int sy = -1;
                boolean canStick = false;

                // 각 칸이 시작점일 때 이 스티커 붙일 수 있는지 확인
                for (int tx = 0; tx < n && !canStick; ++tx) {
                    for (int ty = 0; ty < m && !canStick; ++ty) {
                        // tx, ty가 시작 칸일 때의 부착 가능 여부 확인
                        boolean failed = false;

                        for (int a = tx; a < tx + r && !failed; ++a) {
                            if(a<0 || a>=n) {
                                failed=true;
                                break;
                            }
                            for (int b = ty; b < ty + c && !failed; ++b) {
                                if(b<0 || b>=m) {
                                    failed=true;
                                    break;
                                }
                                
                                if (notebook[a][b]) { // 스티커 부착된 칸인 경우
                                    if (sticker[a - tx][b - ty]) { // 여기에 붙여야 하는 경우
                                        failed = true;
                                    }
                                } else if (!notebook[a][b]) { // 스티커 부착 안 된 칸인 경우
                                    // 여기에 붙여야 하든 말든 상관 없음
                                }
                            }
                        }

                        if (!failed) { // 이 자리에서 부착 가능!
                            sx = tx;
                            sy = ty;
                            canStick = true;
                        }
                    }
                }

                // 찾았으면 그 위치에 붙이고 break;
                if (canStick) {
                    for (int a = sx; a < sx + r; ++a) {
                        for (int b = sy; b < sy + c; ++b) {
                            notebook[a][b] |= sticker[a - sx][b - sy];
                        }
                    }
                    break;
                } else {
                    // 못 찾았으면 스티커 90도 회전시키고 다음 방향에 대한 탐색 ㄱㄱ
                    rotate90();
                }
            }
        }

        // 스티커 붙은 칸 수 세기
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (notebook[i][j]) result++;
            }
        }

        System.out.println(result);
    }

    static void rotate90() { // sticker 배열을 시계 방향으로 90도 회전시키기
        /*
        1   2   3   4
        5   6   7   8
        9   10  11  12
        돌리면
        9   5   1
        10  6   2
        11  7   3
        12  8   4
         */

        // 배열 회전
        boolean[][] tmp = new boolean[c][r];
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                tmp[j][r - 1 - i] = sticker[i][j];
            }
        }
        sticker = tmp;

        // 행, 열 숫자 바꾸기
        int tmp2 = r;
        r = c;
        c = tmp2;
    }

}
