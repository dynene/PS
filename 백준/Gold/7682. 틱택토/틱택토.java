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

    XXX
    OO.
    XXX // invalid > X와 O의 개수 차이가 2 이상임. 불가능

    XOX
    OXO
    XOX // valid > 발생 가능 O 최종 O.

    OXO
    XOX
    OXO // invalid >> 발생 가능 X 최종 O. O가 X보다 많을 수 없음

    XXO
    OOX
    XOX // valid >> 발생 가능 O 최종 O

    XO,
    OX,
    ,,X // valid > 발생 가능 O 최종 O. X와 O 개수 차이 1이고, 완성된 거 있음

    .XX
    X.X
    OOO // invalid > 발생 가능 X 최종 O. X와 O 개수 조건은 맞는데, O가 완성됐

    X.O
    O..
    X.. // invalid > 발생 가능 O 최종 X. X와 O 개수는 조건에 맞는데, 아직 완성된 게 없음

    OOX
    XXO
    OXO // invalid > 빌생 가능 X 최종 O. X보다 O 개수가 많음

    XOX
    XOX
    XOO
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
            int completedX = 0;
            int completedO = 0;
            // 가로 확인
            for (int i = 0; i < 3; ++i) {
                if (board[i][0] == 'X' && board[i][1] == 'X' && board[i][2] == 'X') {
                    completedX++;
                }
                if (board[i][0] == 'O' && board[i][1] == 'O' && board[i][2] == 'O') {
                    completedO++;
                }
            }
            // 세로 확인
            for (int i = 0; i < 3; ++i) {
                if (board[0][i] == 'X' && board[1][i] == 'X' && board[2][i] == 'X') {
                    completedX++;
                }
                if (board[0][i] == 'O' && board[1][i] == 'O' && board[2][i] == 'O') {
                    completedO++;
                }
            }
            // 대각선 확인 - X
            if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
                completedX++;
            }
            if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
                completedX++;
            }
            // 대각선 확인 - O
            if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
                completedO++;
            }
            if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
                completedO++;
            }


            // 둘 다 완성 안 된 경우
            // cntOfEmpty == 0 이면 valid, 아니면 최종이 아니므로 invalid
            if (completedX == 0 && completedO == 0) {
                if (cntOfEmpty == 0) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }

            // X만 완성된 경우
            // cntOfX-1 == cntOfO 이면 valid, 아니면 invalid
            if (completedX != 0 && completedO == 0) {
                if (cntOfX - 1 == cntOfO) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }

            // O만 완성된 경우
            // cntOfX==cntOfO 이면 valid, 아니면 invalid
            if (completedX == 0 && completedO != 0) {
                if (cntOfX == cntOfO) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }

            if (completedX != 0 && completedO != 0) {
                // 이런 경우는 불가능... 하지 않나? ;;
                sb.append("invalid").append("\n");
            }
        }

    }
}
