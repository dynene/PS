import java.util.*;
import java.io.*;

/*
240226
BJ 16918 봄버맨 - 실버 1
 */

public class Main {

    /*
    폭탄 칸: 3초 뒤 폭발, 상하좌우 함께 파괴
    함께 파괴되는 칸에 폭탄 있던 경우에도 연쇄 반응 없이 그냥 파괴

    봄버맨
    - 0초: 봄버맨이 일부 칸에 폭탄 설치
    - 1초; do nothing
    - 2초: 폭탄 설치되지 않은 모든 칸에 폭탄 설치(모든 칸에 폭탄)
    - 3초: 0초에 설치된 폭탄 폭발

    0초의 상태 주어질 때 n초 후 격자판 상태 구하기
     */

    static int r, c, n; // 각각 최대 200
    static char[][] arr; // 폭탄 o 빈칸 .
    static int[][] arr2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        arr2 = new int[r][c];

        for (int i = 0; i < r; ++i) {
            String str = in.readLine();
            for (int j = 0; j < c; ++j) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'O') {
                    // 폭탄. 기본 1초로 세팅
                    arr2[i][j] = 1;
                } else if (arr[i][j] == '.') {
                    // 빈칸
                    arr2[i][j] = 0;
                }
            }
        }

        for (int i = 0; i <= n; ++i) {

            if (i == 0) continue;

            for (int a = 0; a < r; ++a) {
                for (int b = 0; b < c; ++b) { // 모든 칸 탐색

                    if (arr[a][b] == '.') {
                        continue;
                    }

                    if (arr[a][b] == 'O') {
                        if (arr2[a][b] == 3) {
                            // 폭발
                            explode(a, b);
                        } else if (arr2[a][b] == 1 || arr2[a][b] == 2) {
                            arr2[a][b]++;
                        }
                    }
                }
            }

            // 0초를 제외한 모든 짝수 초에, 매번 비어있는 모든 칸에 폭탄 설치
            if (i % 2 == 0) {
                for (int a = 0; a < r; ++a) {
                    for (int b = 0; b < c; ++b) {
                        if (arr[a][b] == '.') { // 빈 칸인 경우
                            arr[a][b] = 'O'; // 폭탄 놓고
                            arr2[a][b] = 1; // 1초로 세팅
                        }
                    }
                }
            }

        }
        // 최종 결과 출력
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);


    }

    static void explode(int a, int b) {
        // 일단 이번 칸 터트리고
        arr[a][b] = '.';
        arr2[a][b] = 0;
        // 상하좌우 터트리기
        for (int i = 0; i < 4; ++i) {
            int nx = a + dx[i];
            int ny = b + dy[i];
            if (nx >= 0 && ny >= 0 && nx < r && ny < c) { // 범위 안에 있는 경우
                if (arr[nx][ny] == 'O') { // 그리고 그 칸이 폭탄인 경우
                    if (arr2[nx][ny] == 3) {
                        explode(nx, ny);
                    }
                    arr[nx][ny] = '.';
                    arr2[nx][ny] = 0;
                } else if (arr[nx][ny] == '.') {
                    arr2[nx][ny] = 0;
                }
            }
        }


    }
}