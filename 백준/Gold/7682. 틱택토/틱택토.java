import java.util.*;
import java.io.*;

/*
240717
BJ7682 틱택토 - 골5
 */

public class Main {

    /*
    주어진 상태가 발생할 수 있는 최종 상태인지 확인하기

    X > O 순서로 놓음
    한 사람이 가로/세로/대각선 3칸 잇거나, 게임판 가득 차면 끝
    */

    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        board = new char[3][3];

        while (true) {
            String str = in.readLine();
            if ("end".equals(str)) {
                System.out.print(sb);
                return;
            }

            int tmp = 0;

            int cntOfX = 0;
            int cntOfO = 0;
            int cntOfEmpty = 0;
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    board[i][j] = str.charAt(tmp++);

                    if (board[i][j] == 'X') cntOfX++;
                    else if (board[i][j] == 'O') cntOfO++;
                    else cntOfEmpty++;
                }
            }

            // X와 O 개수가 동일하거나, X가 1 더 많은지 확인
            if (cntOfX != cntOfO && cntOfX - 1 != cntOfO) {
                // 이 경우 무조건 invalid
                sb.append("invalid").append("\n");
                continue;
            }

            // X, O 조건 확인
            boolean completedX = check('X');
            boolean completedO = check('O');

            // 둘 다 완성 안 된 경우
            // cntOfEmpty == 0 이면 valid, 아니면 최종이 아니므로 invalid
            if (!completedX && !completedO) {
                if (cntOfEmpty == 0) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }

            // X만 완성된 경우
            // cntOfX-1 == cntOfO 이면 valid, 아니면 invalid
            else if (completedX && !completedO) {
                if (cntOfX - 1 == cntOfO) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }

            // O만 완성된 경우
            // cntOfX==cntOfO 이면 valid, 아니면 invalid
            else if (!completedX && completedO) {
                if (cntOfX == cntOfO) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }

            else if (completedX && completedO) {
                // 이런 경우는 불가능... 하지 않나? ;;
                sb.append("invalid").append("\n");
            }
        }

    }

    static boolean check(char c) {

        // 가로 확인
        for (int i = 0; i < 3; ++i) {
            if (board[i][0] == c && board[i][1] == c && board[i][2] == c) {
                return true;
            }
        }
        // 세로 확인
        for (int i = 0; i < 3; ++i) {
            if (board[0][i] == c && board[1][i] == c && board[2][i] == c) {
                return true;
            }
        }
        // 대각선 확인 - X
        if ((board[0][0] == c && board[1][1] == c && board[2][2] == c) || (board[0][2] == c && board[1][1] == c && board[2][0] == c)) {
            return true;
        }

        return false;
    }
}
