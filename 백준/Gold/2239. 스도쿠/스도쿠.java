
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static final int M = 9;
    private static int[][] prob = new int[M][M];

    public static void main(String[] args) throws IOException {
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
        int tmp;
        int i, j;

        // 문제를 prob 배열에 읽어오기
        for (i = 0; i < M; ++i) {
        	String str = in.readLine();
            for (j = 0; j < M; ++j) {
                // 입력 받기
            	tmp = str.charAt(j)-'0';
                prob[i][j] = tmp;
            }
        }

        // 문제 풀기
        sudoku(0, 0);
    }

    private static void printsol() {
        for (int[] row : prob) {
            for (int num : row) {
                System.out.print(num);
            }
            System.out.println();
        }
    }

   private static boolean promising(int i, int j, int n) {
        // 같은 행에 동일 숫자 있으면 안 됨
        for (int a = 0; a < M; ++a) {
            if (n == prob[i][a])
                return false;
        }
        // 같은 열에 동일 숫자 있으면 안 됨
        for (int a = 0; a < M; ++a) {
            if (n == prob[a][j])
                return false;
        }
        // 3x3 박스에 같은 숫자 있으면 안 됨
        int x = (i / 3) * 3;
        int y = (j / 3) * 3;
        for (int a = 0; a < 3; ++a) {
            for (int b = 0; b < 3; ++b) {
                if (n == prob[a + x][b + y])
                    return false;
            }
        }

        // 전부 통과 시(promising할 시)
        return true;
    }

    private static void sudoku(int i, int j) {
        if (i == M && j == 0) {
            // solved++;
            printsol();
            System.exit(0);
        }
        if (prob[i][j] == 0) {
            for (int n = 1; n <= M; ++n) {
                if (promising(i, j, n)) {
                    prob[i][j] = n;
                    if (j == M - 1)
                        sudoku(i + 1, 0);
                    else
                        sudoku(i, j + 1);
                    prob[i][j] = 0;
                }
            }
        } else {
            if (j == M - 1)
                sudoku(i + 1, 0);
            else
                sudoku(i, j + 1);
        }
    }
}
